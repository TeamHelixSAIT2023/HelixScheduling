-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for helixschedulingdb
DROP DATABASE IF EXISTS `helixschedulingdb`;
CREATE DATABASE IF NOT EXISTS `helixschedulingdb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `helixschedulingdb`;

-- Dumping structure for table helixschedulingdb.organization
DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization` (
  `organizationID` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(100) NOT NULL DEFAULT '',
  `public` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`organizationID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table helixschedulingdb.organization: ~1 rows (approximately)
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`organizationID`, `name`, `description`, `public`) VALUES
	(1, 'name', 'desc', 0),
	(2, 'name1', 'desc1', 0);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;

-- Dumping structure for table helixschedulingdb.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `public` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table helixschedulingdb.user: ~7 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `email`, `firstName`, `lastName`, `password`, `phone`, `active`, `public`) VALUES
	(1, 'admin@admin.com', 'admun', 'lastnme', '123456', '1', 1, 1),
	(2, 'test@admin.com', 'first', 'last', 'password', '1', 1, 0),
	(3, 'Barsha@admin.com', 'Barsha', 'Niroula', 'abcd', '587', 1, 0),
	(4, 'Kaden@admin.com', 'Kaden', 'Ohman', 'efgh', '703', 1, 0),
	(5, 'eric@admin.com', 'Eric', 'McLaughlin', 'ijkl', '403', 1, 0),
	(6, 'Sukhpal@admin.com', 'Sukhpal', 'Bahga', 'mnop', '587', 1, 0),
	(7, 'Hoeng@admin.com', 'Hoeng', 'Ching', 'qrst', '984', 1, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
