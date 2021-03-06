package edu.softwaresecurity.group5.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.softwaresecurity.group5.dto.CustomerInformationDTO;

public class UserWithSSNRowMapper implements RowMapper<CustomerInformationDTO> {
	public CustomerInformationDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		  UserWithSSNExtractor userExtractor = new UserWithSSNExtractor();
		  return userExtractor.extractData(resultSet);
		 }
	}
