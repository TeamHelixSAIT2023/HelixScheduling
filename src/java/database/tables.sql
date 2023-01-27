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
    PRIMARY KEY(userID)
);

CREATE TABLE IF NOT EXISTS `helixschedulingdb`.`organization` (
    `organizationID` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `public` BOOLEAN NOT NULL,
    PRIMARY KEY(organizationID)
);