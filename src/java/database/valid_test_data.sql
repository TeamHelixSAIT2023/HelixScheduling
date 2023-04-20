SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE `task`;
TRUNCATE `notification`;
TRUNCATE `timeOffRequest`;
TRUNCATE `organizationRequest`;
TRUNCATE `availabilityChangeRequest`;
TRUNCATE `shiftSwapRequest`;
TRUNCATE `organizationUserRequest`;
TRUNCATE `shiftSwapBoard`;
TRUNCATE `unavailable`;
TRUNCATE `availability`;
TRUNCATE `shift`;
TRUNCATE `organizationUserSchedule`;
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

INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('jessica.smith@example.com', 'Jessica', 'Smith', '4af4645709548643ae09201be72b09d18343120151fd3613f0ba21982239d0ca', 'YfCgIwFzkH+Wn5IYD/bEqnJI0s7vI7NzG035DC5Z7mE=', '4031234567', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('ryan.nguyen@example.com', 'Ryan', 'Nguyen', '0520f16a47bd3a9df17e49569df1d81ce9bfeb273cbe5ccd708c105deb24a65d', 'zsXqc+JKL8ekm5HaYvDQHHRoOLNz/ZzIcf6Hr2a9cHM=', '8259876543', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('emily.wilson@example.com', 'Emily', 'Wilson', '12b7b5cde0f58cef38a07d43b69a6a2572534fbe853ca57046de6ce9fca128dc', 'x3t10fWBjbmkCuDZ0v/4JauLk4cLZbV9VitIp2qOH48=', '5874567890', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('matthew.cook@example.com', 'Mathew', 'Cook', 'fdd1b8290d48a576ede066679dd83f99827b948cdaccb512000880fd347b0ab8', 'CRGQYWzF6Rcew6GTmgKPy+TMrMHVpRhc7GTD8NRiWiE=', '4157891234', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('sarah.watson@example.com', 'Sarah', 'Watson', '7d7e783e5cebe411ba42bc63d1e47ae840b6e414e65d87cd6416dfc605bd30e6', '4QVdRKg4SSlSUWFTQ/vflL/idXaLm5chzdrChb4OxJM=', '2052345678', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('david.nguyen@example.com', 'David', 'Nguyen', '30cb27a7cb4f02795f057b5997ab0c64130762d2a7db0f33faf43082aa8ba20d', 'T5K+7+oPQwtvr+n/NHCCoWsWJy8boQ8zP2aC3z+j3x8=', '8558765432', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('olivia.baker@example.com', 'Olivia', 'Baker', 'cd9c9d508c3a83b82f1cbf5843751b1d23c315700ee53ab803e370b391146313', 'OcJPyLkYyqeKHwD0gBted4hxdNQKDYpmVOMv3xLxo8A=', '8753456789', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('william.jackson@example.com', 'William', 'Jackson', '72968b961c4a06c2efd750022c9a2b578a77800a24669b99152205fa11166cd1', 'ou9D6+Gc4lvPoflFD6GAV5KeodLZmdqu0CDwZhXbYO4=', '2509876543', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('ashley.mitchell@example.com', 'Ashley', 'Mitchell', 'e7db066186ff3ba474ded9fc57363f56d92111827f605995dbc61f6aa6477077', 'YYcZT5uFDc+xtVBrCUCynEfAUqGRP+5XrrdtW9Zqfeg=', '7786789123', true, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('ethan.allen@example.com', 'Ethan', 'Allen', 'a8e776618aca95c25cae383ab8868cf40b8d590750aae56ddb394fde2fb7aece', 'RNpddFebOXRjElAcujvgm2UnlAEx71tBZttkaYuIf6Y=', '2043214567', false, true);
INSERT INTO helixschedulingdb.`user` (email, `firstName`, `lastName`, password, salt, phone, active, `public`) 
	VALUES ('eric.mclaughlin42@gmail.com', 'Eric', 'McLaughlin', '0c6f8d000e8071f0f77cbc60ef6bf2a21f412168f958694d3bd56d988e690425', '7PhLCI/OYjCqRWgudiGk7Nard49f83Z/v6OWSrqjCP4=', '4038526364', true, true);


-- organization Table --

 /* `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `public` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedAvailabilityChange` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedShiftSwap` BOOLEAN NOT NULL DEFAULT 0,
    `managerApprovedTimeOff` BOOLEAN NOT NULL DEFAULT 1,*/

INSERT INTO helixschedulingdb.organization (name, description, public, managerApprovedAvailabilityChange, managerApprovedShiftSwap, managerApprovedTimeOff) VALUES
('Acme Inc.', 'A company that makes widgets', 1, 0, 1, 1),
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
(1, 3, '2023-03-12', '2023-03-18'),
(3, 8, '2023-03-15', '2023-03-21'),
(5, 6, '2023-03-11', '2023-03-17'),
(4, 2, '2023-03-16', '2023-03-22'),
(2, 2, '2023-03-18', '2023-03-24'),
(10, 9, '2023-03-13', '2023-03-19'),
(8, 7, '2023-03-17', '2023-03-23'),
(6, 10, '2023-03-14', '2023-03-20'),
(9, 5, '2023-03-12', '2023-03-18'),
(1, 1, '2023-03-12', '2023-03-18');

-- organizationUser Table 

/*  `organizationUserID` INT(10) NOT NULL AUTO_INCREMENT,
    `organization` INT(10) NOT NULL,
    `user` INT(10) NOT NULL,
    `dept` INT(2),
    `schedule` INT(10) NOT NULL,
    `managedBy` INT(10),
    `hourly` DOUBLE(5,2), */

INSERT INTO helixschedulingdb.organizationUser (organization, user, dept, managedBy, hourly) VALUES
(1, 1, 1, NULL, 25.00),
(1, 2, 1, 1, 20.00),
(2, 3, 2, NULL, 30.00),
(2, 4, 2, 3, 35.00),
(3, 5, 3, NULL, 28.00),
(3, 6, 3, 5, 30.00),
(4, 7, 4, NULL, 22.00),
(4, 8, 4, 7, 25.00),
(5, 9, 5, NULL, 40.00),
(5, 10, 5, 9, 45.00);

INSERT INTO helixschedulingdb.organizationUser (organization, user, dept, managedBy, hourly, admin, owner) VALUES
(1, 11, 1, NULL, 20, true, true);

-- organizationUserSchedule Table
INSERT INTO helixschedulingdb.organizationUserSchedule (organizationUser, schedule) VALUES
(11, 11);

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

INSERT INTO helixschedulingdb.shift (organizationUserSchedule, startDate, endDate, shiftType)
VALUES
    (1, '2023-03-12 09:00:00', '2023-03-12 17:00:00', 'Day Shift'),
    (1, '2023-03-13 13:00:00', '2023-03-13 21:00:00', 'Evening Shift'),
    (1, '2023-03-14 08:00:00', '2023-03-14 16:00:00', 'Day Shift'),
    (1, '2023-03-15 07:00:00', '2023-03-15 15:00:00', 'Day Shift'),
    (1, '2023-03-16 22:00:00', '2023-03-17 06:00:00', 'Night Shift'),
    (1, '2023-03-17 12:00:00', '2023-03-18 20:00:00', 'Evening Shift'),
    (1, '2023-03-18 16:00:00', '2023-03-19 00:00:00', 'Evening Shift');

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

INSERT INTO helixschedulingdb.task (organizationUser, startDate, endDate, title, description, status, priority)
VALUES
(11, '2023-04-01', '2023-04-07', 'Organize shelf', null, 'Completed', 'Medium'),
(11, '2023-04-05', '2023-04-12', 'Rearrange display', null, 'In-Progress', 'High'),
(11, '2023-04-15', '2023-04-25', 'Mop floor', null, 'Not Started', 'Low'),
(11, '2023-04-24', '2023-05-01', 'Count stock', null, 'Not Started', 'Medium'),
(11, '2023-06-13', '2023-04-30', 'Check payroll', null, 'In-Progress', 'Medium'),
(11, '2023-05-19', '2023-04-25', 'Stock shelves', null, 'In-Progress', 'Medium'),
(11, '2023-05-03', '2023-05-15', 'Create new schedule', null, 'Not Started', 'Medium'),
(11, '2023-04-25', '2023-07-01', 'Twiddle your thumbs', null, 'In-Progress', 'Medium');
