SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE `shiftSwapBoard`;
TRUNCATE `shift`;
TRUNCATE `unavailable`;
TRUNCATE `availability`;
TRUNCATE `organizationUser`;
TRUNCATE `schedule`;
TRUNCATE `department`;
TRUNCATE `organization`;
TRUNCATE `user`;
SET FOREIGN_KEY_CHECKS = 1;


-- user Table --

 /* `userID` INT(10) NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(40) NOT NULL,
    `firstName` VARCHAR(20) NOT NULL,
    `lastName` VARCHAR(20) NOT NULL,
    `password` VARCHAR(40) NOT NULL,
    `salt` VARCHAR(32) NOT NULL,
    `phone` VARCHAR(10),
    `active` BOOLEAN NOT NULL DEFAULT 1,
    `public` BOOLEAN NOT NULL DEFAULT 1,*/

INSERT INTO helixschedulingdb.user (email, firstName, lastName, password, salt, phone, active, public) VALUES
('jessica.smith@example.com', 'Jessica', 'Smith', 'wDf#Kj1$23', 'f23RkLjD9#2', '4031234567', 1, 0),
('ryan.nguyen@example.com', 'Ryan', 'Nguyen', 'pGh$9jK1l2', 'dRf83Gh#2jL', '8259876543', 1, 1),
('emily.wilson@example.com', 'Emily', 'Wilson', 'gHjKl#4s8', 'sDf9Kk#4Lj2', '5874567890', 1, 1),
('matthew.cook@example.com', 'Matthew', 'Cook', 'fLk#23GhJ8', 'sDf3FjK#l28', '4157891234', 1, 1),
('sarah.watson@example.com', 'Sarah', 'Watson', 'dFk#2hJ8lK', 'kDf9Lj#2Gh8', '2052345678', 1, 0),
('david.nguyen@example.com', 'David', 'Nguyen', 'jLk#28GhJ6', 'dFk#2lDj3L', '8558765432', 1, 1),
('olivia.baker@example.com', 'Olivia', 'Baker', 'hJk#8lGhF2', 'dFk#2jLl3K', '8753456789', 1, 0),
('william.jackson@example.com', 'William', 'Jackson', 'k#jL3hG6f', 'dFk#lJ23D9', '2509876543', 1, 1),
('ashley.mitchell@example.com', 'Ashley', 'Mitchell', 'lKj#2GhF4', 'dFk#lj23D9', '7786789123', 1, 0),
('ethan.allen@example.com', 'Ethan', 'Allen', 'hGj#lK23D', 'dFk#2Lj9K8', '2043214567', 1, 1),
('eric@gmail.com', 'Eric', 'McLaughlin', 'password', 'fdasfs', '4038526364', 1, 1);

-- organization Table --

 /* `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `public` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedAvailabilityChange` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedShiftSwap` BOOLEAN NOT NULL DEFAULT 0,
    `managerApprovedTimeOff` BOOLEAN NOT NULL DEFAULT 1,*/

INSERT INTO helixschedulingdb.organization (name, description, public, managerApprovedAvailabilityChange, managerApprovedShiftSwap, managerApprovedTimeOff) VALUES
('AcmeInc.', 'A company that makes widgets', 1, 0, 1, 1),
('Apex Co.', 'A consulting firm', 1, 1, 1, 1),
('Bayside Industries', 'A manufacturer of chemicals', 0, 1, 0, 0),
('Cascade LLC', 'A marketing agency', 1, 0, 1, 1),
('Delta Systems', 'A software development company', 1, 1, 1, 0),
('Evergreen Enterprises', 'A landscaping service', 1, 1, 1, 1),
('Falcon Corporation', 'A logistics company', 0, 0, 0, 0),
('Glacier Industries', 'A construction company', 1, 1, 1, 1),
('Horizon Corporation', 'A telecommunications provider', 1, 0, 1, 0),
('Ivy Solutions', 'A healthcare consulting firm', 1, 0, 1, 0);

-- department Table --

 /* `deptID` INT(10) NOT NULL AUTO_INCREMENT,
    `deptNo` INT(2) NOT NULL,
    `organization` INT(10) NOT NULL,
    `title` VARCHAR(40) NOT NULL,
    `description` VARCHAR(100) NOT NULL,*/

INSERT INTO helixschedulingdb.department (deptNo, organization, title, description) VALUES
(1, 1, 'Sales', 'Responsible for generating revenue for the company.'),
(2, 1, 'Marketing', 'Responsible for promoting the company and its products.'),
(3, 2, 'Human Resources', 'Responsible for managing the company workforce.'),
(4, 3, 'Research and Development', 'Responsible for developing new products and technologies.'),
(5, 3, 'Production', 'Responsible for manufacturing products.'),
(6, 4, 'Accounting', 'Responsible for managing the company finances.'),
(7, 5, 'Engineering', 'Responsible for developing and maintaining software applications.'),
(8, 5, 'Quality Assurance', 'Responsible for ensuring that software applications meet the company quality standards.'),
(9, 6, 'Landscaping', 'Responsible for maintaining outdoor spaces.'),
(10, 7, 'Logistics', 'Responsible for managing the transportation and delivery of goods.');

-- schedule Table --

 /* `scheduleID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `organization` INTEGER(10) NOT NULL,
    `dept` INTEGER(10) NOT NULL,
    `startDate` DATE NOT NULL,
    `endDate` DATE NOT NULL,*/

INSERT INTO helixschedulingdb.schedule (organization, dept, startDate, endDate) VALUES
(2, 4, '2023-03-14', '2023-03-20'),
(1, 3, '2023-03-12', '2023-03-19'),
(3, 8, '2023-03-15', '2023-03-21'),
(5, 6, '2023-03-11', '2023-03-17'),
(4, 2, '2023-03-16', '2023-03-22'),
(2, 1, '2023-03-18', '2023-03-24'),
(10, 9, '2023-03-13', '2023-03-19'),
(8, 7, '2023-03-17', '2023-03-23'),
(6, 10, '2023-03-14', '2023-03-20'),
(9, 5, '2023-03-12', '2023-03-18');

-- organizationUser Table 

/*  `organizationUserID` INT(10) NOT NULL AUTO_INCREMENT,
    `organization` INT(10) NOT NULL,
    `user` INT(10) NOT NULL,
    `dept` INT(2),
    `schedule` INT(10) NOT NULL,
    `managedBy` INT(10),
    `hourly` DOUBLE(5,2), */

INSERT INTO helixschedulingdb.organizationUser (organization, user, dept, schedule, managedBy, hourly) VALUES
(1, 1, 1, 1, NULL, 25.00),
(1, 2, 1, 1, 1, 20.00),
(2, 3, 2, 2, NULL, 30.00),
(2, 4, 2, 2, 3, 35.00),
(3, 5, 3, 3, NULL, 28.00),
(3, 6, 3, 3, 5, 30.00),
(4, 7, 4, 4, NULL, 22.00),
(4, 8, 4, 4, 7, 25.00),
(5, 9, 5, 5, NULL, 40.00),
(5, 10, 5, 5, 9, 45.00),
(1, 11, 1, NULL, NULL, 20);

-- availability Table 

/*  `availabilityID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `dayOfWeek` TINYINT NOT NULL,
    `startTime` TIME NOT NULL,
    `endTime` TIME NOT NULL, */

INSERT INTO helixschedulingdb.availability (organizationUser, dayOfWeek, startTime, endTime)
VALUES
(11, 'Sunday', '00:00:00', '00:00:00'),
(11, 'Monday', '09:00:00', '16:00:00'),
(11, 'Tuesday', '10:00:00', '18:00:00'),
(11, 'Wednesday', '07:00:00', '14:00:00'),
(11, 'Thursday', '12:00:00', '20:00:00'),
(11, 'Friday', '13:00:00', '21:00:00'),
(11, 'Saturday', '11:00:00', '19:00:00'),
(8, 'Monday', '09:00:00', '18:00:00'),
(9, 'Tuesday', '08:00:00', '15:00:00'),
(10, 'Wednesday', '10:00:00', '17:00:00');

-- shift Table 

/*  `shiftID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `schedule` INTEGER(10) NOT NULL,
    `organizationUser` INTEGER(10) NOT NULL,
    `startDate` DATETIME NOT NULL,
    `endDate` DATETIME NOT NULL,
    `shiftType` VARCHAR(30),*/

INSERT INTO helixschedulingdb.shift (schedule, organizationUser, startDate, endDate, shiftType)
VALUES
    (1, 1, '2023-03-12 09:00:00', '2023-03-12 17:00:00', 'Day Shift'),
    (2, 2, '2023-03-13 13:00:00', '2023-03-13 21:00:00', 'Evening Shift'),
    (3, 3, '2023-03-14 08:00:00', '2023-03-14 16:00:00', 'Day Shift'),
    (4, 4, '2023-03-15 07:00:00', '2023-03-15 15:00:00', 'Day Shift'),
    (5, 5, '2023-03-16 22:00:00', '2023-03-17 06:00:00', 'Night Shift'),
    (6, 6, '2023-03-18 12:00:00', '2023-03-18 20:00:00', 'Evening Shift'),
    (7, 7, '2023-03-19 16:00:00', '2023-03-19 00:00:00', 'Evening Shift'),
    (8, 8, '2023-03-20 23:00:00', '2023-03-21 07:00:00', 'Night Shift'),
    (9, 9, '2023-03-22 06:00:00', '2023-03-22 14:00:00', 'Day Shift'),
    (10, 10, '2023-03-23 18:00:00', '2023-03-23 02:00:00', 'Evening Shift');

-- shiftSwapBoard Table 

/*  `shiftSwapBoardID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `organization` INTEGER(10) NOT NULL,
    `dept` INTEGER(10) NOT NULL,
    `shift` INTEGER(10) NOT NULL,*/

INSERT INTO helixschedulingdb.shiftSwapBoard (organization, dept, shift) 
VALUES 
(1, 3, 7), 
(2, 1, 3), 
(3, 4, 9), 
(4, 2, 5), 
(1, 2, 4), 
(2, 4, 8), 
(3, 1, 2), 
(4, 3, 6), 
(1, 4, 10), 
(2, 3, 1);
