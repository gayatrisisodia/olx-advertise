-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: advertisement
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertisement` (
  `id` int NOT NULL,
  `cat_id` int DEFAULT NULL,
  `adv_category` varchar(255) DEFAULT NULL,
  `adv_created_date` varchar(255) DEFAULT NULL,
  `adv_desc` varchar(255) DEFAULT NULL,
  `adv_modified_date` varchar(255) DEFAULT NULL,
  `posted_by` varchar(255) DEFAULT NULL,
  `adv_price` int DEFAULT NULL,
  `adv_status` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `adv_title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (1,3,'Vehicle','2-12-2021','1 year old in good condition','2-12-2021','gayatrid',39000,'OPEN',1,'motor bike for sale','gayatrid'),(2,2,'Electronic Good','2-12-2021','intel core sony vaio','2-12-2021','ivanp',25000,'OPEN',1,'laptop sale','ivanp'),(3,1,'Furniture','2-12-2021','sofa 5 year old available for sale','2-12-2021','rakeshm',10000,'OPEN',1,'sofa for sale','rakeshm'),(4,3,'Vehicle','2-12-2021','scooty is available for sale. Its 1 year old with good condition','2-12-2021','jyotir',10000,'OPEN',1,'scooty for sale','jyotir'),(5,3,'Vehicle','2-12-2021','bike is available for sale. Its 1 year old with good condition','2-12-2021','sakshig',28000,'OPEN',1,'bike for sale','sakshig'),(6,1,'Furniture','2-12-2021','dining table is available for sale with three chairs','2-12-2021','shubhamk',15000,'OPEN',1,'dining table for sale','shubhamk');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-03 20:43:30
