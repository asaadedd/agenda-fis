-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: agenda
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `alarma`
--

DROP TABLE IF EXISTS `alarma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarma` (
  `id` int NOT NULL AUTO_INCREMENT,
  `minutePornire` int DEFAULT NULL,
  `recurenta` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarma`
--

LOCK TABLES `alarma` WRITE;
/*!40000 ALTER TABLE `alarma` DISABLE KEYS */;
INSERT INTO `alarma` VALUES (1,10,1);
/*!40000 ALTER TABLE `alarma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eveniment`
--

DROP TABLE IF EXISTS `eveniment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eveniment` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `titlu` varchar(45) DEFAULT NULL,
  `descriere` varchar(45) DEFAULT NULL,
  `idInterval` int DEFAULT NULL,
  `idRecurenta` int DEFAULT NULL,
  `culoare` varchar(45) DEFAULT NULL,
  `idAlarma` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eveniment`
--

LOCK TABLES `eveniment` WRITE;
/*!40000 ALTER TABLE `eveniment` DISABLE KEYS */;
INSERT INTO `eveniment` VALUES (0000000001,'Lab FIS','LAboratul FIS B623',1,1,'-8323200',1),(0000000002,'Lab TP','',2,2,'-128',NULL),(0000000003,'Proiecte','',3,3,'-16760704',NULL),(0000000004,'Donare Sange','',4,4,'-65408',NULL);
/*!40000 ALTER TABLE `eveniment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval`
--

DROP TABLE IF EXISTS `interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataInceput` datetime DEFAULT NULL,
  `dataSfarsit` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval`
--

LOCK TABLES `interval` WRITE;
/*!40000 ALTER TABLE `interval` DISABLE KEYS */;
INSERT INTO `interval` VALUES (1,'2022-05-25 16:00:00','2022-05-25 18:00:00'),(2,'2022-05-25 12:00:00','2022-05-25 14:00:00'),(3,'2022-05-24 12:00:00','2022-05-24 13:00:00'),(4,'2022-05-28 08:00:03','2022-05-28 11:30:03');
/*!40000 ALTER TABLE `interval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurenta`
--

DROP TABLE IF EXISTS `recurenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recurenta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recurenta` int DEFAULT NULL,
  `repetare` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurenta`
--

LOCK TABLES `recurenta` WRITE;
/*!40000 ALTER TABLE `recurenta` DISABLE KEYS */;
INSERT INTO `recurenta` VALUES (1,10,2),(2,5,3),(3,3,1),(4,3,4);
/*!40000 ALTER TABLE `recurenta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24  1:25:41
