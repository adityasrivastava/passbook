-- MySQL dump 10.13  Distrib 5.7.10, for osx10.9 (x86_64)
--
-- Host: localhost    Database: passbook
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `golf`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf` (
  `golf_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `golf_course` int(11) DEFAULT NULL,
  `golf_hole` int(11) DEFAULT NULL,
  `golf_tee` int(11) DEFAULT NULL,
  `golf_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`golf_id`),
  KEY `GOLF_COURSE_FK` (`golf_course`),
  KEY `GOLF_HOLE_FK` (`golf_hole`),
  KEY `FK_6muus9ec151nxh6mmk5kt83ek` (`golf_tee`),
  KEY `GOLF_USER_FK` (`golf_user`),
  CONSTRAINT `FK_6muus9ec151nxh6mmk5kt83ek` FOREIGN KEY (`golf_tee`) REFERENCES `golf_tee` (`tee_id`),
  CONSTRAINT `GOLF_COURSE_FK` FOREIGN KEY (`golf_course`) REFERENCES `golf_course` (`golf_course_id`),
  CONSTRAINT `GOLF_HOLE_FK` FOREIGN KEY (`golf_hole`) REFERENCES `golf_holes` (`holde_type_id`),
  CONSTRAINT `GOLF_USER_FK` FOREIGN KEY (`golf_user`) REFERENCES `golf_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf`
--

LOCK TABLES `golf` WRITE;
/*!40000 ALTER TABLE `golf` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_course`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_course` (
  `golf_course_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`golf_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_course`
--

LOCK TABLES `golf_course` WRITE;
/*!40000 ALTER TABLE `golf_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_holes`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_holes` (
  `holde_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `holes` int(11) DEFAULT NULL,
  PRIMARY KEY (`holde_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_holes`
--

LOCK TABLES `golf_holes` WRITE;
/*!40000 ALTER TABLE `golf_holes` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_holes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_pass`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_pass` (
  `pass_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `pass_added` bit(1) DEFAULT NULL,
  `push_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pass_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_pass`
--

LOCK TABLES `golf_pass` WRITE;
/*!40000 ALTER TABLE `golf_pass` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_score`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `hole_number` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `golf_id` int(11) DEFAULT NULL,
  `golf_tee_detail` int(11) DEFAULT NULL,
  PRIMARY KEY (`score_id`),
  KEY `GOLF_ID_FK` (`golf_id`),
  KEY `GOLF_TEE_DETAIL_FK` (`golf_tee_detail`),
  CONSTRAINT `GOLF_ID_FK` FOREIGN KEY (`golf_id`) REFERENCES `golf` (`golf_id`),
  CONSTRAINT `GOLF_TEE_DETAIL_FK` FOREIGN KEY (`golf_tee_detail`) REFERENCES `golf_tee_details` (`tee_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_score`
--

LOCK TABLES `golf_score` WRITE;
/*!40000 ALTER TABLE `golf_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_tee`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_tee` (
  `tee_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_tee`
--

LOCK TABLES `golf_tee` WRITE;
/*!40000 ALTER TABLE `golf_tee` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_tee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_tee_details`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_tee_details` (
  `tee_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `hole_number` int(11) DEFAULT NULL,
  `par` int(11) DEFAULT NULL,
  `stroke` int(11) DEFAULT NULL,
  `yards` int(11) DEFAULT NULL,
  `tee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tee_type_id`),
  KEY `GOLF_TEE_FK` (`tee_id`),
  CONSTRAINT `GOLF_TEE_FK` FOREIGN KEY (`tee_id`) REFERENCES `golf_tee` (`tee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_tee_details`
--

LOCK TABLES `golf_tee_details` WRITE;
/*!40000 ALTER TABLE `golf_tee_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_tee_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `golf_user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `golf_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `handicap` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `golf_pass` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_em740elskk1pf24le5bkdr3sa` (`golf_pass`),
  CONSTRAINT `FK_em740elskk1pf24le5bkdr3sa` FOREIGN KEY (`golf_pass`) REFERENCES `golf_pass` (`pass_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `golf_user`
--

LOCK TABLES `golf_user` WRITE;
/*!40000 ALTER TABLE `golf_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `golf_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass_registration`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pass_registration` (
  `register_pass_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  `pass_type_id` varchar(255) DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `registered_pass_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`register_pass_id`),
  KEY `REGISTERED_PASS_ID_FK` (`registered_pass_id`),
  CONSTRAINT `REGISTERED_PASS_ID_FK` FOREIGN KEY (`registered_pass_id`) REFERENCES `golf_pass` (`pass_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass_registration`
--

LOCK TABLES `pass_registration` WRITE;
/*!40000 ALTER TABLE `pass_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `pass_registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-03 10:27:43
