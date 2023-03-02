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
    CONSTRAINT ck_user_email CHECK (`email` LIKE '^[^@]+@[^@]+\.[^@]{2,}$'),
    CONSTRAINT ck_user_firstName CHECK (`firstName` LIKE '^[\w''\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$'),
    CONSTRAINT ck_user_lasName CHECK (`lastName` LIKE '^[\w''\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$'),
    CONSTRAINT ck_user_phone CHECK (`phone` LIKE '[0-9]{10}')
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organization` (
    `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
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
    CONSTRAINT uk_schedule_organization_dept
        UNIQUE (`organization`, `dept`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationUser` (
    `organizationUserID` INT(10) NOT NULL AUTO_INCREMENT,
    `organization` INT(10) NOT NULL,
    `user` INT(10) NOT NULL,
    `dept` INT(2),
    `schedule` INT(10) NOT NULL,
    `managedBy` INT(10),
    `hourly` DOUBLE(5,2),
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
    CONSTRAINT fk_organizationUser_sheduleID
        FOREIGN KEY (`schedule`)
        REFERENCES `helixschedulingdb`.`schedule` (`scheduleID`),
    CONSTRAINT uk_organizationUser_organization_user
        UNIQUE (`organization`, `user`),
    CONSTRAINT ck_organizationUser_hourly_above_zero
        CHECK (`hourly` > 0)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`availability` (
    `availabilityID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUser` INT(10) NOT NULL,
    `dayOfWeek` TINYINT NOT NULL,
    `startTime` TIME NOT NULL,
    `endTime` TIME NOT NULL,
    PRIMARY KEY (`availabilityID`),
    CONSTRAINT fk_availability_organizationUser
        FOREIGN KEY (`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT ck_availability_dayOfWeek 
        CHECK (`dayOfWeek` BETWEEN 1 AND 7),
    CONSTRAINT ck_availability_startTime_less_than_endTime
        CHECK (`startTime` < `endTime`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`shift` (
    `shiftID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `schedule` INTEGER(10) NOT NULL,
    `organizationUser` INTEGER(10) NOT NULL,
    `startDate` DATETIME NOT NULL,
    `endDate` DATETIME NOT NULL,
    `shiftType` VARCHAR(30),
    PRIMARY KEY (`shiftID`),
    CONSTRAINT fk_shift_schedule
        FOREIGN KEY (`schedule`)
        REFERENCES `helixschedulingdb`.`schedule`(`scheduleID`),
    CONSTRAINT fk_shift_organizationUser
        FOREIGN KEY (`organizationUser`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`shiftSwapBoard` (
    `shiftSwapBoardID` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `organization` INTEGER(10) NOT NULL,
    `dept` INTEGER(10) NOT NULL,
    `shift` INTEGER(10) NOT NULL,
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