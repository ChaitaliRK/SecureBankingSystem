create database if not exists `sbs`;

USE `sbs`;

DROP TABLE IF EXISTS `pendingtransactions`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `user_attempts`;


CREATE  TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(60) NOT NULL,
  confirmpassword VARCHAR(60) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  MerchantorIndividual VARCHAR(45) NOT NULL,
  phonenumber VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  SSN VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  userExpired TINYINT NOT NULL DEFAULT 1 ,
  userLocked TINYINT NOT NULL DEFAULT 1 ,
  userDetailsExpired TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username));
  
  INSERT INTO users 
  VALUES ('admin', '$2a$10$ocmyR6rP7eo1qRsV1rpvO.Mlh1QqtabUvD3sBhTlHobRKij/ZxZ1e', '$2a$10$ocmyR6rP7eo1qRsV1rpvO.Mlh1QqtabUvD3sBhTlHobRKij/ZxZ1e', 'admin', 'admin', 'Merchant', '4808452326',
  'skulkar9@asu.edu', 'ssn', 'address', true,true,true,true);
  INSERT INTO users 
  VALUES ('shivam', '$2a$10$hRzs1QvAQ.LYHRZLohvjJuCow9BKLQ0MXVuTv9YxpBvNVt87NbBDK', '$2a$10$hRzs1QvAQ.LYHRZLohvjJuCow9BKLQ0MXVuTv9YxpBvNVt87NbBDK', 'shivam', 'shivam', 'Merchant', '4804804801',
  'shivam@asu.edu', 'ssn', 'address', true,true,true,true);
  INSERT INTO users 
  VALUES ('skgarg', '$2a$10$hRzs1QvAQ.LYHRZLohvjJuCow9BKLQ0MXVuTv9YxpBvNVt87NbBDK', '$2a$10$hRzs1QvAQ.LYHRZLohvjJuCow9BKLQ0MXVuTv9YxpBvNVt87NbBDK', 'shivam', 'shivam', 'Merchant', '4804804801',
  'shivam@asu.edu', 'ssn', 'address', true,true,true,true);
  INSERT INTO users 
  VALUES ('employee', 'employee', 'employee', 'employee', 'employee', 'Merchant', '4804804802',
  'employee@asu.edu', 'ssn', 'address', true,true,true,true);

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('shivam', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('skgarg', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('employee', 'ROLE_EMPLOYEE');


CREATE TABLE account (
	username VARCHAR(45) NOT NULL,
	accountnumber int(11) NOT NULL AUTO_INCREMENT,
	accountbalance float(45) NOT NULL,
    debit float(45) NOT NULL,
    credit float(45) NOT NULL,
	PRIMARY KEY (accountnumber))
;

CREATE TABLE pendingtransactions (
id INT(11) NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
amount float(45) NOT NULL,
pending TINYINT NOT NULL DEFAULT 1,
accountnumberfrom int(11) NOT NULL,
accountnumberto int(11) NOT NULL,
PRIMARY KEY (id),
KEY fk_username_idx (username),
CONSTRAINT fk_username_pending FOREIGN KEY (username) REFERENCES users (username)
);

insert into account values ("skgarg","1234567890","500","0","0");
insert into account values ("shivam","124","500","0","0");

CREATE TABLE user_attempts (
  id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  attempts VARCHAR(45) NOT NULL,
  lastModified datetime NOT NULL,
  PRIMARY KEY (id)
);