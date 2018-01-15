-- Can drop DB first, IF no other db users are using your DB
-- DROP DATABASE IF EXISTS prs;
-- CREATE DATABASE prs;
-- USE prs;
-- or 
-- individually drop tables in reverse order of FK constraints
--
DROP TABLE IF EXISTS purchaserequestlineitem;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS purchaserequest;
DROP TABLE IF EXISTS vendor;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id 				INT 			PRIMARY KEY AUTO_INCREMENT,
  username			NVARCHAR(20) 	NOT NULL,
  password			NVARCHAR(10) 	NOT NULL,
  firstname			NVARCHAR(20) 	NOT NULL,
  lastname			NVARCHAR(20) 	NOT NULL,
  phone				NVARCHAR(12) 	NOT NULL,
  email				NVARCHAR(75) 	NOT NULL,
  isreviewer		TINYINT(1) 		NOT NULL,
  isadmin			TINYINT(1) 		NOT NULL,
  isactive			TINYINT(1) 		DEFAULT 1 NOT NULL,
  datecreated		DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL,
  dateupdated		DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		INT 			DEFAULT 1 NOT NULL,
  CONSTRAINT uq_name UNIQUE (username)
);
  
CREATE TABLE vendor
(
  id				INT 			PRIMARY KEY AUTO_INCREMENT,
  code				NVARCHAR(10) 	NOT NULL,
  name				NVARCHAR(255) 	NOT NULL,
  address			NVARCHAR(255) 	NOT NULL,
  city				NVARCHAR(255) 	NOT NULL,
  state				NVARCHAR(2) 	NOT NULL,
  zip				NVARCHAR(5) 	NOT NULL,
  phone				NVARCHAR(12) 	NOT NULL,
  email				NVARCHAR(100) 	NOT NULL,
  ispreapproved		TINYINT(1) 		NOT NULL,
  isactive			TINYINT(1) 		DEFAULT 1 NOT NULL,
  datecreated		DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL,
  dateupdated		DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		INT 			DEFAULT 1 NOT NULL,
  CONSTRAINT uq_vcode UNIQUE (code)
);

CREATE TABLE status
(
  id				INT 			PRIMARY KEY AUTO_INCREMENT,
  description		NVARCHAR(20) 	NOT NULL,
  isactive			TINYINT(1) 		DEFAULT 1 NOT NULL,
  datecreated		DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL,
  dateupdated		DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		INT 			DEFAULT 1 NOT NULL,
  CONSTRAINT uq_desc UNIQUE (description)
);

CREATE TABLE purchaserequest
(
  id				 INT 			PRIMARY KEY AUTO_INCREMENT,
  userid			 INT 			NOT NULL,
  description		 NVARCHAR(100) 	NOT NULL,
  justification		 NVARCHAR(255) 	NOT NULL,
  dateneeded		 DATETIME	    NOT NULL, 
  deliverymode		 NVARCHAR(25) 	NOT NULL, 
  statusid			 INT 			NOT NULL,
  total				 DECIMAL(10,2) 	NOT NULL,
--   submitteddate		 DATETIME		NOT NULL, 
  submitteddate		 DATETIME, 
  reasonforrejection NVARCHAR(100),
  isactive			 TINYINT(1) 	DEFAULT 1 NOT NULL,
  datecreated		 DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL, 
  dateupdated		 DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		 INT 			DEFAULT 1 NOT NULL,
  FOREIGN KEY (userid) REFERENCES user (id),
  FOREIGN KEY (statusid) REFERENCES status (id)  
);

CREATE TABLE product
(
  id				INT 			PRIMARY KEY AUTO_INCREMENT,
  vendorid			INT 			NOT NULL,
  partnumber		NVARCHAR(50) 	NOT NULL,
  name				NVARCHAR(150) 	NOT NULL,
  price				DECIMAL(10,2) 	NOT NULL,
  unit				NVARCHAR(255),
  photopath			NVARCHAR(255),
  isactive			TINYINT(1) 		DEFAULT 1 NOT NULL,
  datecreated		DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL,
  dateupdated		DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		INT 			DEFAULT 1 NOT NULL,
  FOREIGN KEY (vendorid) REFERENCES vendor (id),
  CONSTRAINT uq_vendor_part UNIQUE (vendorid, partnumber)  
);

CREATE TABLE purchaserequestlineitem
(
  id				INT 			PRIMARY KEY AUTO_INCREMENT,
  purchaserequestid	INT 			NOT NULL,
  productid			INT 			NOT NULL,
  quantity			INT 			NOT NULL,
  isactive			TINYINT(1) 		DEFAULT 1 NOT NULL,
  datecreated		DATETIME 		DEFAULT CURRENT_TIMESTAMP NOT NULL,
  dateupdated		DATETIME 		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  updatedbyuser		INT 			DEFAULT 1 NOT NULL, 
  FOREIGN KEY (productid) REFERENCES product (id),
  FOREIGN KEY (purchaserequestid) REFERENCES purchaserequest (id),
  CONSTRAINT uq_req_prdt UNIQUE (purchaserequestid, productid)
);

-- Example of ALTER TABLE statement  
-- ALTER TABLE purchaserequest
-- ADD FOREIGN KEY fk_purchreq_user (userid) REFERENCES users (id);
	
-- Insert statements
-- Add 'SYSTEM' user
INSERT INTO user (ID, UserName, Password, FirstName, LastName, Phone, Email, IsReviewer, IsAdmin, updatedbyuser)
   VALUES (1, 'SYSTEM', 'xxxxx', 'System', 'System', '513-561-3717', 'system@test.com', 0, 0,1);

INSERT INTO user (ID, UserName, Password, FirstName, LastName, Phone, Email, IsReviewer, IsAdmin, updatedbyuser) VALUES 
  (2, 'ADMBONNIE', 'pwd', 'Bonnie', 'Raitt', '513-561-4363', 'bonnier@test.com', 0, 1, 1),
  (3, 'RVJAMES', 'pwd', 'James', 'Taylor', '513-271-1112', 'samt@test.com', 1, 0, 1),
  (4, 'RQTINA', 'pwd', 'Tina', 'Turner', '513-828-3216', 'tinat@test.com', 0, 0, 1),
  (5, 'RQKara', 'pwd', 'Kara','Danvers','513-271-2706', 'kara@test.com', 0, 0, 1),
  (6, 'RQClark', 'pwd', 'Clark','Kent','513-721-8888', 'clark@test.com', 0, 0, 1),
  (7, 'un', 'pwd', 'Cliff','Notes', '513-555-1212', 'cliff@notes.com', 0, 1, 1),
  (8, 'andrew', 'pwd', 'Andrew','Mojzer', '513-555-1212', 'am@admin.com', 0, 1, 1),
  (9, 'luke', 'pwd', 'Luke','Hinegardner', '513-555-1212', 'lh@requestor.com', 1, 0, 1),
  (10, 'bill', 'pwd', 'Bill','Mandella', '513-555-1212', 'bm@reviewer.com', 1, 0, 1);	

-- insert some rows into the Status table
INSERT INTO status (description) VALUES
('NEW'),
('REVIEW'),
('APPROVED'),
('REJECTED');

-- insert some rows into the Vendor table
INSERT INTO vendor (ID, code, name, address, city, state, zip, phone, email, ispreapproved) VALUES
(1, 'BB-1001', 'Best Buy', '100 Best Buy Street', 'Louisville', 'KY', '40207', '502-111-9099', 'geeksquad@bestbuy.com', 0),
(2, 'AP-1001', 'Apple Inc', '1 Infinite Loop', 'Cupertino', 'CA', '95014', '800-123-4567', 'genius@apple.com', 0),
(3, 'AM-1001', 'Amazon', '410 Terry Ave. North', 'Seattle', 'WA', '98109','206-266-1000', 'amazon@amazon.com', 1),
(4, 'ST-1001', 'Staples', '9550 Mason Montgomery Rd', 'Mason', 'OH', '45040', '513-754-0235', 'support@orders.staples.com', 1),
(5, 'MC-1001', 'Micro Center', '11755 Mosteller Rd', 'Sharonville', 'OH', '45241', '513-782-8500', 'support@microcenter.com', 1),
(6, 'AC-1001', 'Acme Manufacturing', '1 Looney Toons Way', 'WallaWalla', 'WA', '98341', '392-555-1212', 'support@acmecares.com', 1);

-- insert some rows into the Product table
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (1,1,'ME280LL','iPad Mini 2',296.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (2,2,'ME280LL','iPad Mini 2',299.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (3,3,'105810','Hammermill Paper, Premium Multi-Purpose Paper Poly Wrap',8.99,'1 Ream / 500 Sheets',NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (4,4,'122374','HammerMill® Copy Plus Copy Paper, 8 1/2\" x 11\", Case',29.99,'1 Case, 10 Reams, 500 Sheets per ream',NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (5,4,'784551','Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ',14.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (6,4,'382955','Staples Mouse Pad, Black',2.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (7,4,'2122178','AOC 24-Inch Class LED Monitor',99.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (8,4,'2460649','Laptop HP Notebook 15-AY163NR',529.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (9,4,'2256788','Laptop Dell i3552-3240BLK 15.6\"',239.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (10,4,'IM12M9520','Laptop Acer Acer™ Aspire One Cloudbook 14\"',224.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (11,4,'940600','Canon imageCLASS Copier (D530)',99.99,NULL,NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (12,5,'228148','Acer Aspire ATC-780A-UR12 Desktop Computer',399.99,'','/images/AcerAspireDesktop.jpg');
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (13,5,'279364','Lenovo IdeaCentre All-In-One Desktop',349.99,'','/images/LenovoIdeaCenter.jpg');
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (14,4,'111005','Quartevendort® Basic Whiteboard, Silver Aluminum Frame', 199.99, NULL, NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (15,1,'5845332','Boost Mobile - Motorola Moto E4 4G LTE with 16GB Memory Prepaid Cell Phone', 69.99, NULL, NULL);
INSERT INTO `product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (16,6,'AC00RR1','Roadrunner Special Tacks', 19.99, NULL, NULL);

INSERT INTO `purchaserequest` (`ID`,`UserID`,`Description`,`Justification`,`DateNeeded`,`DeliveryMode`,`StatusID`, `Total`) VALUES (1,8,'Miscellaneous supplies','Replenishment', '2018-01-30', 'Mail', 1, 0);
INSERT INTO `purchaserequest` (`ID`,`UserID`,`Description`,`Justification`,`DateNeeded`,`DeliveryMode`,`StatusID`, `Total`) VALUES (2,5,'Workshop supplies','Need for workshop', '2018-01-30', 'Fly over', 1, 0);
INSERT INTO `purchaserequest` (`ID`,`UserID`,`Description`,`Justification`,`DateNeeded`,`DeliveryMode`,`StatusID`, `Total`) VALUES (3,9,'Office Supplies','Need for desk', '2018-01-30', 'Pickup', 1, 0);
INSERT INTO `purchaserequest` (`ID`,`UserID`,`Description`,`Justification`,`DateNeeded`,`DeliveryMode`,`StatusID`, `Total`) VALUES (4,10,'New Laptop','Computer is broken', '2018-02-01', 'Pickup', 1, 0);

INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (1,1,4,1);
INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (2,1,5,1);
INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (3,1,16,1);
INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (4,2,15,1);
INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (5,3,6,1);
INSERT INTO `purchaserequestlineitem` (`ID`,`PurchaseRequestID`,`ProductID`,`Quantity`) VALUES (6,4,9,1);

-- create a user and grant privileges to that user
-- specifically use this user to connect to the database; DBA can see which bootcamper is interacting w DB
-- only want to grant minimum functionality that this user needs 
GRANT SELECT, INSERT, DELETE, UPDATE
ON prs.*
TO prs_user@localhost
IDENTIFIED BY 'sesame'