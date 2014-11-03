package edu.softwaresecurity.group5.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.softwaresecurity.group5.dao.CustomerDAO;
import edu.softwaresecurity.group5.dto.BillPayDTO;
import edu.softwaresecurity.group5.dto.CustomerInformationDTO;
import edu.softwaresecurity.group5.dto.DuplicateValidationCheckerDTO;
import edu.softwaresecurity.group5.jdbc.BillPayMapper;
import edu.softwaresecurity.group5.jdbc.DuplicateValidationCheckerMapper;
import edu.softwaresecurity.group5.jdbc.UserRowMapper;
import edu.softwaresecurity.group5.model.AccountAttempts;
import edu.softwaresecurity.group5.model.AddUserInformation;
import edu.softwaresecurity.group5.model.ChangePassword;
import edu.softwaresecurity.group5.model.CustomerInformation;

/*Using Spring JDBC Template
 Reasons: Better connection management, no writing XML files
 Cleans up resources by releasing DB connection
 Better error detection 
 */

public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	DataSource dataSource;
	
	public String addUser(AddUserInformation addInfo) throws NoSuchAlgorithmException {
		addInfo.setEnabled(1);
		addInfo.setUserLocked(1);
		addInfo.setUserExpired(1);
		addInfo.setUserDetailsExpired(1);
		
		String addUserQuery = "INSERT into users"
				+ "(username, password, confirmpassword,"
				+ "firstname,"
				+ "lastname, sex, MerchantorIndividual, phonenumber,"
				+ "email, SSN, address, enabled, "
				+ "userExpired, userLocked, userDetailsExpired) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		String addUserIntoUserRolesTable = "INSERT into user_roles (username, role) "
				+ "VALUES (?,?)";
		String addUserIntoAccountsTable = "INSERT into account (username,"
				+ "accountnumber, accountbalance, debit, credit)"
				+ "VALUES (?,?,?,?,?)";
		
		JdbcTemplate jdbcTemplateForRegisterCustomer = new JdbcTemplate(
				dataSource);
		JdbcTemplate jdbcTemplateForUserRoles = new JdbcTemplate(dataSource);
		JdbcTemplate jdbcTemplateForAccounts = new JdbcTemplate(dataSource);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hash = passwordEncoder.encode(addInfo.getPassword());

		// CALL THE DTO FUNCTION---
		List<DuplicateValidationCheckerDTO> list1 = new ArrayList<DuplicateValidationCheckerDTO>();
		list1 = checkDuplicateDetails(addInfo.getUserName(), addInfo.getEmailAddress_addUser(), addInfo.getSocialSecurityNumber());
		
		if(list1.size()!=0) {
			return "UserName, Email and SSN must be unique!";
		}
		else {
		
			jdbcTemplateForRegisterCustomer.update(
					addUserQuery,
					new Object[] { addInfo.getUserName(), hash, hash,
							addInfo.getFirstName(), addInfo.getLastName(),
							addInfo.getSex(),
							addInfo.getSelection(), addInfo.getContactNumber(),
							addInfo.getEmailAddress_addUser(),
							addInfo.getSocialSecurityNumber(),
							addInfo.getAddress(), addInfo.getEnabled(),
							addInfo.getUserExpired(), addInfo.getUserLocked(),
							addInfo.getUserDetailsExpired() });
			
			jdbcTemplateForUserRoles.update(addUserIntoUserRolesTable, new Object[] {
					addInfo.getUserName(), "ROLE_USER" });
			
			// Generating random account numbers
			SecureRandom secure;
			secure = SecureRandom.getInstance("SHA1PRNG");
			String accountNumber = new Integer(secure.nextInt()).toString();
			
			jdbcTemplateForAccounts.update(addUserIntoAccountsTable,
					new Object[] {addInfo.getUserName(), accountNumber, "1000", "0",
					"0"});
			return "User Added Successfully!!";
		}
	}


	public String registerCustomer(CustomerInformation custInfo) throws NoSuchAlgorithmException {
		
		custInfo.setEnabled(1);
		custInfo.setUserLocked(1);
		custInfo.setUserExpired(1);
		custInfo.setUserDetailsExpired(1);
		String registerCustomerQuery = "INSERT into users"
				+ "(username, password, confirmpassword,"
				+ "firstname,"
				+ "lastname, sex, MerchantorIndividual, phonenumber,"
				+ "email, SSN, address, enabled, "
				+ "userExpired, userLocked, userDetailsExpired) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String insertIntoUserRolesTable = "INSERT into user_roles (username, role) "
				+ "VALUES (?,?)";
		String insertIntoAccountsTable = "INSERT into account (username,"
				+ "accountnumber, accountbalance, debit, credit)"
				+ "VALUES (?,?,?,?,?)";

		JdbcTemplate jdbcTemplateForRegisterCustomer = new JdbcTemplate(
				dataSource);
		JdbcTemplate jdbcTemplateForUserRoles = new JdbcTemplate(dataSource);
		JdbcTemplate jdbcTemplateForAccounts = new JdbcTemplate(dataSource);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hash = passwordEncoder.encode(custInfo.getPassword());
		
		// CALL THE DTO FUNCTION---
		List<DuplicateValidationCheckerDTO> list1 = new ArrayList<DuplicateValidationCheckerDTO>();
		list1 = checkDuplicateDetails(custInfo.getUsername(), custInfo.getEmail(), custInfo.getSocialSecurityNumber());
		
		if(list1.size()!=0) {
			return "UserName, Email and SSN must be unique!";
		}
		else {
			jdbcTemplateForRegisterCustomer.update(
					registerCustomerQuery,
					new Object[] { custInfo.getUsername(), hash, hash,
							custInfo.getFirstname(), custInfo.getLastname(),
							custInfo.getSex(),
							custInfo.getSelection(), custInfo.getPhonenumber(),
							custInfo.getEmail(),
							custInfo.getSocialSecurityNumber(),
							custInfo.getAddress(), custInfo.getEnabled(),
							custInfo.getUserExpired(), custInfo.getUserLocked(),
							custInfo.getUserDetailsExpired() });
	
			jdbcTemplateForUserRoles.update(insertIntoUserRolesTable, new Object[] {
					custInfo.getUsername(), "ROLE_USER" });
			
			// Generating random account numbers
			SecureRandom secure;
	
			secure = SecureRandom.getInstance("SHA1PRNG");
			String accountNumber = new Integer(secure.nextInt()).toString();			
		
			jdbcTemplateForAccounts.update(insertIntoAccountsTable,
					new Object[] {custInfo.getUsername(), accountNumber, "1000", "0",
					"0"});
		
			return "Registration Successful!!";
		}
	}

	// Privileges - Add
	public List<CustomerInformationDTO> retrieveUserDetails(String username) {
		List<CustomerInformationDTO> customerInformationToDisplay = new ArrayList<CustomerInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, users.sex, "
				+ "users.MerchantorIndividual, users.phonenumber, users.email, "
				+ "users.address, account.accountnumber, account.accountbalance from users inner join account on users.username = account.username where users.enabled = true and users.username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery,
				new Object[] { username }, new UserRowMapper());
		return customerInformationToDisplay;

	}

	public List<CustomerInformationDTO> getUserList() {
		List<CustomerInformationDTO> userList = new ArrayList<CustomerInformationDTO>();

		String sql = "SELECT users.username, users.firstname, users.lastname, users.sex, "
				+ "users.MerchantorIndividual, users.phonenumber, users.email, "
				+ "users.address, account.accountnumber from users inner join account on users.username = account.username where users.enabled = true";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList;
	}

	public CustomerInformationDTO getUserFromAccount(String accountNumber) {
		List<CustomerInformationDTO> customerInformationToDisplay = new ArrayList<CustomerInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, users.sex, "
				+ "users.MerchantorIndividual, users.phonenumber, users.email, "
				+ "users.address, account.accountnumber from users inner join account on users.username = account.username where enabled = true and accountnumber=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery,
				new Object[] { accountNumber }, new UserRowMapper());
		return customerInformationToDisplay.get(0);

	}

	public String updateAccount(CustomerInformationDTO custInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE users set firstname = ?,lastname = ?, phonenumber = ?, email = ?, address=?"
				+ " where enabled = true  and username = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int status = jdbcTemplate.update(sql,
				new Object[] { custInfo.getFirstname(), custInfo.getLastname(),
						custInfo.getPhonenumber(), custInfo.getEmail(),
						custInfo.getAddress(), custInfo.getUsername() });
		if (status == 1) {
			return "Updated account details Succesfully";
		}

		return "Databse not updated, please contact Branch Representative";
	}

	public String changeAccountPassword(ChangePassword custInfo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hash = passwordEncoder.encode(custInfo.getPassword());
		String sql = "UPDATE users set password = ?,confirmpassword = ?"
				+ " where enabled = true  and username = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { hash, hash,
				custInfo.getUsername() });
		if (status == 1) {
			return "Updated account details Succesfully";
		}
		return "Databse not updated, please contact Branch Representative";
	}

	public String unlockAccount(CustomerInformationDTO custInfo) {
		if (verifyAccountForLock(custInfo)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			// this otp is hardcoded for now and will be changed later. or else
			// everyone will have same otp. not secure.
			String otp = "Hardcodedfornow";
			String hash = passwordEncoder.encode(otp);
			String sql = "UPDATE users set password = ?,confirmpassword = ?, userLocked= false"
					+ " where enabled = true  and username = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int status = jdbcTemplate.update(sql, new Object[] { hash, hash,
					custInfo.getUsername() });
			if (status == 1) {
				return "your new password is " + hash;
			}
			return "Database please contact Branch Representative";
		}
		return "User account for "
				+ custInfo.getUsername()
				+ " is not locked, please contact adminstrator if you have login issues.";
	}

	public boolean verifyAccountForLock(CustomerInformationDTO custInfo) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String username = custInfo.getUsername();
		try {

			AccountAttempts userAttempts = jdbcTemplate
					.queryForObject(
							"SELECT username,userLocked  FROM users WHERE username = ?",
							new Object[] { username },
							new RowMapper<AccountAttempts>() {
								public AccountAttempts mapRow(ResultSet rs,
										int rowNum) throws SQLException {

									AccountAttempts user = new AccountAttempts();
									user.setUsername(rs.getString("username"));
									user.setLocked(rs.getInt("userLocked"));

									return user;
								}

							});
			if (userAttempts.isLocked() == 1) {
				return false;
			}
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	// BillPayment 
	// Merchant initiates the request
	public boolean billPayment(String generatedFromUsername, String account, String amount) {
		float amountToTransfer = Float.parseFloat(amount);
		
		
		String getAccountDetailsFromUsername = "SELECT account.accountnumber from account"
				+ " inner join users on "
				+ "account.username=users.username "
				+ "where account.username=?";
		
		String insertIntoPendingTransactions = "INSERT INTO "
				+ "pendingtransactions(username, amount, pending, accountnumberfrom,"
				+ "accountnumberto, billpay) "
				+ "VALUES (?,?,?,?,?,?)";
		
		JdbcTemplate jdbcTemplateForAccountNumber = new JdbcTemplate(dataSource);
		JdbcTemplate jdbcTemplateForPendingTransactions = new JdbcTemplate(dataSource);
		
		String getUsernameAccount = jdbcTemplateForAccountNumber.queryForObject(getAccountDetailsFromUsername,
				new Object[] {generatedFromUsername},
				String.class);
		
		int accountNumber = Integer.parseInt(getUsernameAccount);
		int accountNumberFrom = Integer.parseInt(account);
		
		jdbcTemplateForPendingTransactions.update(insertIntoPendingTransactions,
				new Object[]{generatedFromUsername, amountToTransfer, false, accountNumber,
				accountNumberFrom, true});
		return true;
	}

	// Customer gets the request
	public List<BillPayDTO> getBillPayRequestForCustomer(String customerUsername) {
		List<BillPayDTO> billPayDetails = new ArrayList<BillPayDTO>();
		
		if (customerUsername != null) {
			String getBillPayDetailsQuery = "SELECT pendingtransactions.id,"
					+ "pendingtransactions.username, pendingtransactions.amount,"
					+ "pendingtransactions.accountnumberfrom from pendingtransactions "
					+ "inner join account on pendingtransactions.accountnumberto=account.accountnumber"
					+ " where account.username=?";
			JdbcTemplate jdbcTemplateForBillPayDetails = new JdbcTemplate(dataSource);
			billPayDetails = jdbcTemplateForBillPayDetails.query(getBillPayDetailsQuery, new Object[]{customerUsername}, new BillPayMapper());
			
			return billPayDetails;
		} else {
			return null;
		}	
	}
	
	// Debit funds from user account
	public String debitFromUserAccount(String usernameOfCustomer, float debitAmount) {
		String getDebitDetailsForCustomer = "SELECT accountbalance from account where"
				+ " username=?";
		JdbcTemplate jdbcTemplateForGettingDebitDetails = new JdbcTemplate(dataSource);
		float accountBalanceBeforeDebit = jdbcTemplateForGettingDebitDetails.queryForObject(getDebitDetailsForCustomer, 
				new Object[]{usernameOfCustomer}, Float.class);
		
		if (accountBalanceBeforeDebit >= debitAmount) {
			float accountBalanceAfterDebit = accountBalanceBeforeDebit - debitAmount;
			
			String updateAfterDebit = "UPDATE account SET accountBalance=?, debit=?"
					+ "WHERE username=?";
			
			jdbcTemplateForGettingDebitDetails.update(updateAfterDebit,
					new Object[]{accountBalanceAfterDebit, debitAmount, usernameOfCustomer});
			
			return "Account debited with: "+debitAmount+". New balance: "+accountBalanceAfterDebit;
		} else {
			return "You do not have sufficient funds to debit. Please check your balance and try again.";

		}
	}

	// Method for checking duplicate details
	public List<DuplicateValidationCheckerDTO> checkDuplicateDetails(String username, String email, String SSN) {
		List<DuplicateValidationCheckerDTO> duplicateCheckDetails = new ArrayList<DuplicateValidationCheckerDTO>();
		String getDuplicateDetailsQuery = "SELECT users.username, users.email, users.SSN from users where users.username=? OR users.email=? OR users.SSN=?";
		JdbcTemplate jdbcTemplateForDupicateCheck = new JdbcTemplate(dataSource);
		duplicateCheckDetails = jdbcTemplateForDupicateCheck.query(getDuplicateDetailsQuery, new Object[]{username, email, SSN}, new DuplicateValidationCheckerMapper());
		
		return duplicateCheckDetails;
	}
	
	public String creditToUserAccount(String usernameLoggedIn,
			Float creditAmountFloat) {
		String getCreditDetailsForCustomer = "SELECT accountbalance from account where"
				+ " username=?";
		
		JdbcTemplate jdbcTemplateForGettingCreditDetails = new JdbcTemplate(dataSource);
		float accountBalanceBeforeCredit = jdbcTemplateForGettingCreditDetails.queryForObject(getCreditDetailsForCustomer, 
				new Object[]{usernameLoggedIn}, Float.class);
		float accountBalanceAfterCredit = accountBalanceBeforeCredit + creditAmountFloat; 
		
		String updateAfterCredit = "UPDATE account SET accountBalance=?, credit=?"
				+ "WHERE username=?";
		
		jdbcTemplateForGettingCreditDetails.update(updateAfterCredit,
				new Object[]{accountBalanceAfterCredit, creditAmountFloat, usernameLoggedIn});
		return "Amount credited with: "+creditAmountFloat+". New balance is: "+accountBalanceAfterCredit;
	}	
}