CREATE DATABASE  IF NOT EXISTS `sofengg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sofengg`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sofengg
-- ------------------------------------------------------
-- Server version	5.6.35-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hashedPass` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'$31$16$umRipuJiTbFWGJJcWwGeS4rRjd-cn0Ae9sqzM0pwwTs','vicedeanSA@dlsu.edu.ph');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `collegeId` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(45) NOT NULL,
  PRIMARY KEY (`collegeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'College of Computer Science'),(2,'College of Business'),(3,'College of Education'),(4,'College of Engineering'),(5,'College of Liberal Arts'),(6,'College of Science'),(7,'School of Economics');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `involvement`
--

DROP TABLE IF EXISTS `involvement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `involvement` (
  `involvementId` int(11) NOT NULL AUTO_INCREMENT,
  `iName` varchar(45) NOT NULL,
  `studentDBId` int(11) NOT NULL,
  `position` varchar(45) NOT NULL,
  `acadYear` varchar(4) NOT NULL,
  `internal` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`involvementId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `involvement`
--

LOCK TABLES `involvement` WRITE;
/*!40000 ALTER TABLE `involvement` DISABLE KEYS */;
INSERT INTO `involvement` VALUES (19,'Something',22,'Someone','2017',0),(20,'lol',22,'its working!','2000',0),(21,'lmao',22,'yes it is!','2002',0),(22,'BLAH',22,'Someone','1900',1),(23,'normal',22,'mode','1800',1),(24,'hard',22,'MODAL','2020',1),(25,'im new',22,'im added','1111',0),(26,'fuck my life bruh',22,'XD','2030',1),(27,'Future',22,'Robot','3000',1),(28,'',22,'','1234',1),(29,'',22,'','1234',0),(30,'',22,'','1234',0),(31,'',22,'','1234',0),(32,'Something',23,'Someone','2017',1),(33,'I confess my crush',23,'BestBoi','2018',1),(34,'HELP',23,'ME','2019',1);
/*!40000 ALTER TABLE `involvement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `involvementhandler`
--

DROP TABLE IF EXISTS `involvementhandler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `involvementhandler` (
  `iHId` int(11) NOT NULL AUTO_INCREMENT,
  `involvementID` int(11) NOT NULL,
  `handler` varchar(45) NOT NULL,
  PRIMARY KEY (`iHId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `involvementhandler`
--

LOCK TABLES `involvementhandler` WRITE;
/*!40000 ALTER TABLE `involvementhandler` DISABLE KEYS */;
INSERT INTO `involvementhandler` VALUES (2,6,'PCOA'),(3,6,'LSCS');
/*!40000 ALTER TABLE `involvementhandler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `body` varchar(1005) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`postId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relative`
--

DROP TABLE IF EXISTS `relative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relative` (
  `relativeId` int(11) NOT NULL AUTO_INCREMENT,
  `studentDBId` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`relativeId`,`studentDBId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relative`
--

LOCK TABLES `relative` WRITE;
/*!40000 ALTER TABLE `relative` DISABLE KEYS */;
INSERT INTO `relative` VALUES (20,22,'Who','Sibling','You','2000-12-01'),(21,22,'Paula Love','Sibling','Tobias','1997-06-09'),(22,22,'Alex  II','Sibling','Agno','1997-12-03'),(23,22,'Papa bear','Father','Bear','2000-12-01'),(24,22,'GALING KO NAMAN','Mother','Bear','2000-12-01'),(25,22,'Charlie','Sibling','The Unicorn','2000-12-10'),(26,23,'Ate bear','Sibling','Bear','2000-12-01'),(27,23,'asd','Sibling','asd','2000-12-01'),(28,23,'Papa bear','Father','Bear','2000-12-01'),(29,23,'Mama bear','Mother','Bear','2000-12-01');
/*!40000 ALTER TABLE `relative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `collegeId` int(11) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  `yearEnrolled` year(4) DEFAULT NULL COMMENT 'Only supports 1901 - 2155',
  `celNo` varchar(45) DEFAULT NULL,
  `telNo` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `hashedPass` varchar(100) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `civil` varchar(45) DEFAULT NULL,
  `citizen` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `verificationId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (4,11237461,'Fran','Cu','Cruz',NULL,NULL,NULL,'09158991234','9201842','fran@gmail.com','$31$16$OwKCkVlmVb_tOkyx-_KDzs2GRGIr2coo-jSXrdYwNw4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,11526690,'Francis','Dello','Nueva',NULL,NULL,NULL,'091428571','8294726','francis@gmail.com','$31$16$TDbXzUeTLOc4fCpRUVzYUnYNSOExZ26GO201eFXAKfY',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,11427591,'Francheska','Cu','Cruz',NULL,NULL,NULL,'092792713','9201842','francheska@gmail.com','$31$16$L4oqBRhd0NtbHbc4BOMZN4WTw-I2kupWM9qbIUtYJjk',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,11527401,'Fin','','Landerson',7,'',NULL,NULL,NULL,'user@gmail.com','$31$16$t980HWQkEkx3jd-M-sMnvDFmcFxSkMJXvz7D0cNf-ss',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,11433426,'Alexander II','Hernandez','Agno',1,'BSCS-ST',2015,'09178364730','7024242','alexander_agno@dlsu.edu.ph','$31$16$1sTcicgqELbxi55S8R7sUwhe5QOUPXYe4ZGCZtMC7lA','#100 Sct. Boy bata','1997-12-03','Single','Filipino','Male','Philippines','Batangas',1104,'Quezon Ciity',NULL),(11,1919191,'firstname','middlename','lastname',5,'course',NULL,'','','email@address.com','$31$16$JYPe33BLSSdzWs683L_JPUGV2sJsxsVM9GU29cDSsFQ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,1233123,'firstname','hernanDEZZZ','lastname',1,'bs-cs-st',NULL,'12123','11111','agno@alex.com','$31$16$umRipuJiTbFWGJJcWwGeS4rRjd-cn0Ae9sqzM0pwwTs','meowwthan','1990-01-01','wat','male','family','ezCountry','EzPzProvince',0,'Pochinki',NULL),(13,11111111,'Face','Inverted','Troll',1,'BS-CS-ST',NULL,'11111111','111111','hanzo@genji.com','$31$16$TMrNCOtlU6n_nrzNr0Fzy5GuOuIfxfVhLH-BTszZWPM','IDK','1993-03-03','MALE','WEAB','MALE','IDK COUNTRY','IDK PROVINCE',1111,'IDK CITY',NULL),(14,11433426,'Alexander II','Hernandez','Agno',3,'BS-BULLSHIT',NULL,'','','alex@alex.com','$31$16$h439BVKDJJrNYlHWG2uDNKMkIuRYOHjzxHt1l-3493c',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,11,'11','11','11',1,'11',NULL,'','','11@11.com','$31$16$S6hJTmHsUkBq0VfaFZ6ehghdBtuFuGrQLunurdQCF7s',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,90909090,NULL,NULL,NULL,1,'Software',NULL,'','',NULL,'$31$16$oHqG9P2q799DaLqqpTzbh0Ae7BDWAOANw8fwabGEyRQ','','1990-01-01','','','','','',0,'',NULL),(17,56789543,'testing','testing','testing',7,'testing',NULL,'','','test@testing.com','$31$16$gxo0TWZxs0UZvO72wiqcvP9mPGGRUWYt4ALflFphGlo',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,88888888,'eight','eight','eight',4,'eight',NULL,'','','eight@eight.com','$31$16$U4MGPBaeOXzqqaUYPFjrfVnOj6uDRCzysdIbM9sTzSc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,77777777,'firstname','middlename','lastname',6,'coursename',NULL,'','','name@this.com','$31$16$MjcjjkXZ0zFT8YuTJDeacuI68vjkM5FJuSOSXNJCHJs',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,10010011,'Alexander II','Hernandez','Agno',1,'Software Technology',NULL,'','','alex@pubg.com','$31$16$jOLGGODXz0nfT2FjbRRN8kcDAQcJ5ATmky8jYxpJbw8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,90881231,'Razer','Elegiggle','Mouse',3,'Software Technology',NULL,'','','gonna@test.com','$31$16$_S8xauM0fHKXdw4sH13_38MfpyLgqhVGiHpDhZpTaq0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,11000000,'Alexander II','','Agno',4,'asdadads',NULL,'11233333333','70911111','pubg@pubg.com','$31$16$02RmrEhMXbrN9NHzLUdnzyT6bXZxriZGuqIfhWWBoy4','123123976--a-sda-sda-sda-sd-asd','1990-01-01','SINGLE','filipino','male','asdasasd','HELPasdasdasd',111,'is my City',''),(25,90111223,'Alexander II','','Agno',1,'COMPUTER ENG',NULL,'','','3alex23@gmail.com','$31$16$y7zKUDldChZvKNHZ0e1sFnTlQE0-XGGUx7apH2bd9jI',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'$31$16$VxI4LutzP9UZBd5bRohWCgZh9g2910vxVLEuPLJMBS0');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-01 18:04:45
