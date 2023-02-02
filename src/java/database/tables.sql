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
    `phone` VARCHAR(10),
    `active` BOOLEAN,
    `public` BOOLEAN,
    PRIMARY KEY(`userID`),
    CONSTRAINT uk_user_email UNIQUE(`email`),
    CONSTRAINT uk_user_phone UNIQUE(`phone`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organization` (
    `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `public` BOOLEAN NOT NULL,
    `shiftSwapBoardID` INT(10),
    PRIMARY KEY(`organizationID`)
);


CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`department` (
    `deptID` INT(10) NOT NULL AUTO_INCREMENT,
    `deptNo` INT(2) NOT NULL,
    `organizationID` INT(10) NOT NULL,
    `title` VARCHAR(40) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`deptID`),
    CONSTRAINT fk_department_organizationID
        FOREIGN KEY (`organizationID`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT uk_department_deptNo_organizationID
        UNIQUE(`deptNo`, `organizationID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organizationUser` (
    `organizationUserID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationID` INT(10) NOT NULL,
    `userID` INT(10) NOT NULL,
    `deptID` INT(2),
    `scheduleID` INT(10) NOT NULL,
    `managedBy` INT(10),
    `hourly` DOUBLE(5,2),
    `availability` INT(10),
    PRIMARY KEY(`organizationUserID`),
    CONSTRAINT fk_organizationUser_organizationID
        FOREIGN KEY (`organizationID`)
        REFERENCES `helixschedulingdb`.`organization`(`organizationID`),
    CONSTRAINT fk_organizationUser_userID
        FOREIGN KEY (`userID`)
        REFERENCES `helixschedulingdb`.`user`(`userID`),
    CONSTRAINT fk_organizationUser_deptID
        FOREIGN KEY (`deptID`)
        REFERENCES `helixschedulingdb`.`department`(`deptID`),
    CONSTRAINT fk_organizationUser_mangedBy
        FOREIGN KEY (`managedBy`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT uk_organizationUser_organizationID_userID
        UNIQUE (`organizationID`, `userID`)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`availability` (
    `availabilityID` INT(10) NOT NULL AUTO_INCREMENT,
    `organizationUserID` INT(10) NOT NULL,
    `dayOfWeek` TINYINT NOT NULL,
    `startTime` TIME NOT NULL,
    `endTime` TIME NOT NULL,
    PRIMARY KEY (`availabilityID`),
    CONSTRAINT fk_availability_organizationUserID
        FOREIGN KEY (`organizationUserID`)
        REFERENCES `helixschedulingdb`.`organizationUser`(`organizationUserID`),
    CONSTRAINT ck_availability_dayOfWeek 
        CHECK (`dayOfWeek` BETWEEN 1 AND 7),
    CONSTRAINT ck_availability_startTime_less_than_endTime
        CHECK (`startTime` < `endTime`)
);
