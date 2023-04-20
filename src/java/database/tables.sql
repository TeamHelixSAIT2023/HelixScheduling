/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Eric
 * Created: 24-Jan-2023
 */

DROP SCHEMA IF EXISTS `helixschedulingdb`;
CREATE SCHEMA IF NOT EXISTS `helixschedulingdb` DEFAULT CHARACTER SET latin1;
USE `helixschedulingdb`;

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`user` (
    `userID` INT(10) NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(40) NOT NULL,
    `firstName` VARCHAR(20) NOT NULL,
    `lastName` VARCHAR(20) NOT NULL,
    `password` VARCHAR(40) NOT NULL,
    `salt` VARCHAR(32) NOT NULL,
    `phone` VARCHAR(10),
    `active` BOOLEAN NOT NULL DEFAULT 1,
    `public` BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY(`userID`),
    CONSTRAINT uk_user_email UNIQUE(`email`),
    CONSTRAINT uk_user_phone UNIQUE(`phone`),
    CONSTRAINT ck_user_email CHECK (`email` REGEXP '^[^@]+@[^@]+\.[^@]{2,}$'),
    CONSTRAINT ck_user_firstName CHECK (`firstName` NOT REGEXP '^[\w''\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$'),
    CONSTRAINT ck_user_lasName CHECK (`lastName` NOT REGEXP '^[\w''\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$'),
    CONSTRAINT ck_user_phone CHECK (`phone` REGEXP '[0-9]{10}')
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organization` (
    `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `public` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedAvailabilityChange` BOOLEAN NOT NULL DEFAULT 1,
    `managerApprovedShiftSwap` BOOLEAN NOT NULL DEFAULT 0,
    `managerApprovedTimeOff` BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY(`organizationID`)
);


CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`department` (
    `deptID` INT(10) NOT NULL AUTO_INCREMENT,
    `deptNo` INT(2) NOT NULL,
    `organization` INT(10) NOT NULL,
    `title` VARCHAR(40) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`deptID`),
    CONSTRAINT fk_department_organizationID
        FOREIGN KEY (`organization`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT uk_department_deptID_organization
        UNIQUE(`deptNo`, `organization`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`schedule` (
    `scheduleID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `organization` INTEGER(10) NOT NULL,
    `dept` INTEGER(10) NOT NULL,
    `startDate` DATE NOT NULL,
    `endDate` DATE NOT NULL,
    PRIMARY KEY (`scheduleID`),
    CONSTRAINT fk_schedule_organization
        FOREIGN KEY (`organization`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT fk_schedule_dept
        FOREIGN KEY (`dept`)
        REFERENCES `helixschedulingdb`.`department`(`deptID`),
    CONSTRAINT ck_schedule_startDate_less_than_endDate
        CHECK (`startDate` < `endDate`),
    CONSTRAINT uk_schedule_dept_startDate
        UNIQUE (`dept`, `startDate`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationUser` (
    `organizationUserID` INT(10) NOT NULL AUTO_INCREMENT,
    `organization` INT(10) NOT NULL,
    `user` INT(10) NOT NULL,
    `dept` INT(2),
    `managedBy` INT(10),
    `hourly` DOUBLE(5,2) DEFAULT 0,
    `admin` BOOLEAN DEFAULT FALSE,
    `owner` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`organizationUserID`),
    CONSTRAINT fk_organizationUser_organizationID
        FOREIGN KEY (`organization`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT fk_organizationUser_user
        FOREIGN KEY (`user`)
        REFERENCES `helixschedulingdb`.`user`(`userID`),
    CONSTRAINT fk_organizationUser_dept
        FOREIGN KEY (`dept`)
        REFERENCES `helixschedulingdb`.`department`(`deptID`),
    CONSTRAINT fk_organizationUser_mangedBy
        FOREIGN KEY (`managedBy`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT uk_organizationUser_organization_user
        UNIQUE (`organization`, `user`),
    CONSTRAINT ck_organizationUser_hourly_above_zero
        CHECK (`hourly` >= 0)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationUserSchedule` (
    `organizationUserScheduleID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `schedule` INT(10) NOT NULL,
    PRIMARY KEY(`organizationUserScheduleID`),
    CONSTRAINT fk_organizationUserSchedule_organizationUser
        FOREIGN KEY(`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_organizationUserSchedule_schedule
        FOREIGN KEY(`schedule`)
        REFERENCES `helixschedulingdb`.`schedule`(`scheduleID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`shift` (
    `shiftID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUserSchedule` INT(10) NOT NULL,
    `startDate` DATETIME NOT NULL,
    `endDate` DATETIME NOT NULL,
    `shiftType` VARCHAR(30),
    PRIMARY KEY (`shiftID`),
    CONSTRAINT fk_shift_organizationUserSchedule
        FOREIGN KEY (`organizationUserSchedule`)
        REFERENCES `helixschedulingdb`.`organizationUserSchedule`(`organizationUserScheduleID`),
    CONSTRAINT uk_shift_organizationUserSchedule_startDate
        UNIQUE (`organizationUserSchedule`, `startDate`)

);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`availability` (
    `availabilityID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `dayOfWeek` VARCHAR(9) NOT NULL,
    `startTime` TIME NOT NULL DEFAULT '00:00:00',
    `endTime` TIME NOT NULL DEFAULT '00:00:00',
    PRIMARY KEY (`availabilityID`),
    CONSTRAINT fk_availability_organizationUser
        FOREIGN KEY (`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT uk_availability_orgUser_dayOfWeek
        UNIQUE (organizationUser, dayOfWeek),
    CONSTRAINT ck_availability_dayOfWeek 
        CHECK (`dayOfWeek` IN ('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')),
    CONSTRAINT ck_availability_startTime_valid
        CHECK (`startTime` BETWEEN '00:00:00' AND '24:00:00'),
    CONSTRAINT ck_availability_endTime_valid
        CHECK (`endTime` BETWEEN '00:00:00' AND '24:00:00'),
    CONSTRAINT ck_availability_startTime_less_than_endTime_or_zeros
        CHECK ((`startTime` < `endTime`) OR ((`startTime` = '00:00:00') AND (`endTime` = '00:00:00')))
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`unavailable` (
    `unavailableID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `date` DATE NOT NULL,
    `reason` VARCHAR(100),
    PRIMARY KEY (`unavailableID`),
    CONSTRAINT fk_unavailable_organizationUser
        FOREIGN KEY (`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser` (`organizationUserID`),
    CONSTRAINT uk_unavailable_orgUser_date
        UNIQUE (`organizationUser`, `date`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`shiftSwapBoard` (
    `shiftSwapBoardID` INT(10) NOT NULL AUTO_INCREMENT,
    `organization` INT(10) NOT NULL,
    `dept` INT(10) NOT NULL,
    `shift` INT(10) NOT NULL,
    PRIMARY KEY (`shiftSwapBoardID`),
    CONSTRAINT fk_shiftSwapBoard_organization
        FOREIGN KEY (`organization`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT fk_shiftSwapBoard_dept
        FOREIGN KEY (`dept`)
        REFERENCES `helixschedulingdb`.`department` (`deptID`),
     CONSTRAINT fk_shiftSwapBoard_shift
        FOREIGN KEY (`shift`)
        REFERENCES `helixschedulingdb`.`shift`(`shiftID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`task` (
    `taskID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `startDate` DATE NOT NULL,
    `endDate` DATE,
    `title` VARCHAR(50) NOT NULL,
    `description` VARCHAR(100),
    `status` ENUM('Not Started', 'In-Progress', 'Completed') NOT NULL,
    `priority` ENUM ('High', 'Medium', 'Low'),
    PRIMARY KEY(`taskID`),
    CONSTRAINT fk_task_organizationUser
        FOREIGN KEY(`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationUserRequest` (
    `organizationUserRequestID` INT(10) NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(50) NOT NULL,
    `sender` INT(10) NOT NULL,
    `receiver` INT(10) NOT NULL,
    `approved` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`organizationUserRequestID`),
    CONSTRAINT fk_request_sender
        FOREIGN KEY (`sender`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_request_receiver
        FOREIGN KEY (`receiver`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`shiftSwapRequest` (
    `shiftSwapRequestID` INT(10) NOT NULL AUTO_INCREMENT,
    `sender` INT(10) NOT NULL,
    `receiver` INT(10) NOT NULL,
    `shift` INT(10) NOT NULL,
    `approved` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`shiftSwapRequestID`),
    CONSTRAINT fk_shiftSwapRequest_sender
        FOREIGN KEY (`sender`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_shiftSwapRequest_receiver
        FOREIGN KEY (`receiver`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_shiftSwapRequest_shift
        FOREIGN KEY (`shift`)
        REFERENCES `helixschedulingdb`.`shift`(`shiftID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`availabilityChangeRequest` (
    `availabilityChangeRequestID` INT(10) NOT NULL AUTO_INCREMENT,
    `sender` INT(10) NOT NULL,
    `receiver` INT(10) NOT NULL,
    `sunday` INT(10) NOT NULL,
    `monday` INT(10) NOT NULL,
    `tuesday` INT(10) NOT NULL,
    `wednesday` INT(10) NOT NULL,
    `thursday` INT(10) NOT NULL,
    `friday` INT(10) NOT NULL,
    `saturday` INT(10) NOT NULL,
    `approved` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`availabilityChangeRequestID`),
    CONSTRAINT fk_availabilityChangeRequest_sender
        FOREIGN KEY(`sender`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_availabilityChangeRequest_receiver
        FOREIGN KEY(`receiver`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT fk_availabilityChangeRequest_sunday
        FOREIGN KEY(`sunday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_monday
        FOREIGN KEY(`monday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_tuesday
        FOREIGN KEY(`tuesday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_wednesday
        FOREIGN KEY(`wednesday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_thursday
        FOREIGN KEY(`thursday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_friday
        FOREIGN KEY(`friday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`),
    CONSTRAINT fk_availabilityChangeRequest_saturday
        FOREIGN KEY(`saturday`)
        REFERENCES `helixschedulingdb`.`availability`(`availabilityID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationRequest` (
    `organizationRequestID` INT(10) NOT NULL AUTO_INCREMENT,
    `user` INT(10) NOT NULL,
    `organization` INT(10) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `approved` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`organizationRequestID`),
    CONSTRAINT fk_organizationRequest_user
        FOREIGN KEY(`user`)
        REFERENCES `helixschedulingdb`.`user`(`userID`),
    CONSTRAINT fk_organizationRequest_organization
        FOREIGN KEY(`organization`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`timeOffRequest` (
    `timeOffRequestID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `startDate` DATE NOT NULL,
    `endDate` DATE NOT NULL,
    `reason` VARCHAR(100),
    `approved` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`timeOffRequestID`),
    CONSTRAINT fk_timeOffRequest_organizationUser
        FOREIGN KEY(`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`notification` (
    `notificationID` INT(10) NOT NULL AUTO_INCREMENT,
    `user` INT(10) NOT NULL,
    `requestID` INT(10),
    `requestType` VARCHAR(20),
    `title` VARCHAR(50) NOT NULL,
    `description` VARCHAR(100),
    `dimissed` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`notificationID`),
    CONSTRAINT fk_notification_user
        FOREIGN KEY (`user`)
        REFERENCES `helixschedulingdb`.`user`(`userID`)
);
