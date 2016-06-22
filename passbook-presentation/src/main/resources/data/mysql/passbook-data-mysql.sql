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
-- Dumping data for table `GOLF`
--

LOCK TABLES `GOLF` WRITE;
/*!40000 ALTER TABLE `GOLF` DISABLE KEYS */;
/*!40000 ALTER TABLE `GOLF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_COURSE`
--

LOCK TABLES `GOLF_COURSE` WRITE;
/*!40000 ALTER TABLE `GOLF_COURSE` DISABLE KEYS */;
INSERT INTO `GOLF_COURSE` VALUES (1,'2016-06-22 10:08:35','2016-06-22 10:08:35',0,'Golf Course 1');
/*!40000 ALTER TABLE `GOLF_COURSE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_HOLES`
--

LOCK TABLES `GOLF_HOLES` WRITE;
/*!40000 ALTER TABLE `GOLF_HOLES` DISABLE KEYS */;
INSERT INTO `GOLF_HOLES` VALUES (1,'2016-06-22 10:08:30','2016-06-22 10:08:30',0,9);
/*!40000 ALTER TABLE `GOLF_HOLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_SCORE`
--

LOCK TABLES `GOLF_SCORE` WRITE;
/*!40000 ALTER TABLE `GOLF_SCORE` DISABLE KEYS */;
/*!40000 ALTER TABLE `GOLF_SCORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_TEE`
--

LOCK TABLES `GOLF_TEE` WRITE;
/*!40000 ALTER TABLE `GOLF_TEE` DISABLE KEYS */;
INSERT INTO `GOLF_TEE` VALUES (1,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,'Red'),(2,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,'Blue'),(3,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,'Black');
/*!40000 ALTER TABLE `GOLF_TEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_TEE_DETAILS`
--

LOCK TABLES `GOLF_TEE_DETAILS` WRITE;
/*!40000 ALTER TABLE `GOLF_TEE_DETAILS` DISABLE KEYS */;
INSERT INTO `GOLF_TEE_DETAILS` VALUES (1,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,1,3,9,107,1),(2,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,2,3,3,132,1),(3,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,3,3,5,130,1),(4,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,4,3,1,118,1),(5,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,5,3,15,122,1),(6,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,6,3,17,90,1),(7,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,7,3,7,82,1),(8,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,8,3,11,105,1),(9,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,9,3,13,104,1),(10,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,1,3,9,107,2),(11,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,2,3,3,132,2),(12,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,3,3,5,150,2),(13,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,4,3,1,160,2),(14,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,5,3,15,122,2),(15,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,6,3,17,90,2),(16,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,7,3,7,104,2),(17,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,8,3,11,105,2),(18,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,9,3,13,104,2),(19,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,1,3,9,118,3),(20,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,2,3,3,147,3),(21,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,3,3,5,165,3),(22,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,4,3,1,169,3),(23,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,5,3,15,131,3),(24,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,6,3,17,100,3),(25,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,7,3,7,110,3),(26,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,8,3,11,112,3),(27,'2016-06-22 10:08:33','2016-06-22 10:08:33',0,9,3,13,119,3);
/*!40000 ALTER TABLE `GOLF_TEE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GOLF_USER`
--

LOCK TABLES `GOLF_USER` WRITE;
/*!40000 ALTER TABLE `GOLF_USER` DISABLE KEYS */;
INSERT INTO `GOLF_USER` VALUES (1,'2016-06-22 09:53:58','2016-06-22 09:53:58',0,0,NULL,0,'Ad Mds',NULL);
/*!40000 ALTER TABLE `GOLF_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PASS_REGISTRATION`
--

LOCK TABLES `PASS_REGISTRATION` WRITE;
/*!40000 ALTER TABLE `PASS_REGISTRATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `PASS_REGISTRATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `USER_PASS`
--

LOCK TABLES `USER_PASS` WRITE;
/*!40000 ALTER TABLE `USER_PASS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_PASS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `USER_PROFILE`
--

LOCK TABLES `USER_PROFILE` WRITE;
/*!40000 ALTER TABLE `USER_PROFILE` DISABLE KEYS */;
INSERT INTO `USER_PROFILE` VALUES (1,'2016-06-22 09:53:58','2016-06-22 09:53:58',0,'aditya','Ad Mds','Ad Mds','','ROLE_USER',NULL,1);
/*!40000 ALTER TABLE `USER_PROFILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserConnection`
--

LOCK TABLES `UserConnection` WRITE;
/*!40000 ALTER TABLE `UserConnection` DISABLE KEYS */;
INSERT INTO `UserConnection` VALUES ('facebook','106238913135311','aditya','EAAKPF8mFZCrkBAAy6GDzZCTEgj3ouddDgkEuqyA0qfZBOamCnovrZB5sopMUZAyfQzJNKDZASLewZCdkEfmxHWez00UtU4WTk0ZCYBZB6ulQykYbZBxLvZCh9cidYgRWC3T4UogXmxATT2aSOjHFSZA6f60cq8reyxaEnMB0yOaNrfmZAMgZDZD','Ad Mds',1471773224633,'https://graph.facebook.com/v2.5/106238913135311/picture','https://www.facebook.com/app_scoped_user_id/106238913135311/',1,NULL,NULL);
/*!40000 ALTER TABLE `UserConnection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-22 15:38:42
