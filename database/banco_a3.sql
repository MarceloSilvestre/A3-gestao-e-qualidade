CREATE DATABASE  IF NOT EXISTS `banco_a3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `banco_a3`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banco_a3
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `professores`
--

DROP TABLE IF EXISTS `professores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professores` (
  `prof_numid` int NOT NULL AUTO_INCREMENT,
  `prof_id` varchar(36) NOT NULL,
  `prof_nome` varchar(200) NOT NULL,
  `prof_tpid` int NOT NULL,
  `prof_dtinsercao` datetime NOT NULL,
  `prof_dtatualizacao` datetime NOT NULL,
  PRIMARY KEY (`prof_numid`),
  UNIQUE KEY `prof_numid` (`prof_numid`),
  UNIQUE KEY `prof_id` (`prof_id`),
  KEY `Professores_fk3` (`prof_tpid`),
  CONSTRAINT `Professores_fk3` FOREIGN KEY (`prof_tpid`) REFERENCES `tiposprofessores` (`tp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professores`
--

LOCK TABLES `professores` WRITE;
/*!40000 ALTER TABLE `professores` DISABLE KEYS */;
INSERT INTO `professores` VALUES (1,'451bcf0c-35cd-11f0-8d72-9c2dcd42fcf7','Jose L. Ferreira',1,'2025-05-20 23:53:39','2025-05-21 18:48:53'),(2,'451c1479-35cd-11f0-8d72-9c2dcd42fcf7','Hugo F. Araújo',1,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(3,'451c167d-35cd-11f0-8d72-9c2dcd42fcf7','Sônia A. Vinhedo',2,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(4,'451c17a2-35cd-11f0-8d72-9c2dcd42fcf7','Jorge Avelar',2,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(5,'451c18c4-35cd-11f0-8d72-9c2dcd42fcf7','Luiz Marcos M. Silva',3,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(6,'451c1951-35cd-11f0-8d72-9c2dcd42fcf7','Maria Antonieta Bittencourt',3,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(7,'451c19dc-35cd-11f0-8d72-9c2dcd42fcf7','Fernanda Amaro',1,'2025-05-20 23:53:39','2025-05-20 23:53:39'),(10,'894fa73e-3661-11f0-a6e2-9c2dcd42fcf7','Helena F. Fiero',2,'2025-05-21 17:34:59','2025-05-21 17:34:59'),(11,'9f93e63a-3661-11f0-a6e2-9c2dcd42fcf7','Sabrina A. Munhoz',3,'2025-05-21 17:35:36','2025-05-21 17:35:36');
/*!40000 ALTER TABLE `professores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salarios`
--

DROP TABLE IF EXISTS `salarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salarios` (
  `sal_profid` varchar(36) NOT NULL,
  `sal_hora` double DEFAULT NULL,
  `sal_valorhora` double DEFAULT NULL,
  `sal_horacalc` decimal(10,2) DEFAULT NULL,
  `sal_valormensal` decimal(10,2) DEFAULT NULL,
  `sal_valorcontrato` decimal(10,2) DEFAULT NULL,
  `sal_dtinsercao` datetime NOT NULL,
  `sal_dtatualizacao` datetime NOT NULL,
  KEY `Salarios_fk0` (`sal_profid`),
  CONSTRAINT `Salarios_fk0` FOREIGN KEY (`sal_profid`) REFERENCES `professores` (`prof_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salarios`
--

LOCK TABLES `salarios` WRITE;
/*!40000 ALTER TABLE `salarios` DISABLE KEYS */;
INSERT INTO `salarios` VALUES ('451c167d-35cd-11f0-8d72-9c2dcd42fcf7',NULL,NULL,NULL,13500.00,NULL,'2025-05-21 01:31:17','2025-05-21 01:31:17'),('451c18c4-35cd-11f0-8d72-9c2dcd42fcf7',NULL,NULL,NULL,NULL,64589.00,'2025-05-21 01:37:28','2025-05-21 01:37:28'),('451c1479-35cd-11f0-8d72-9c2dcd42fcf7',150,7.5,1125.00,NULL,NULL,'2025-05-22 20:03:09','2025-05-22 20:03:09');
/*!40000 ALTER TABLE `salarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposprofessores`
--

DROP TABLE IF EXISTS `tiposprofessores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiposprofessores` (
  `tp_id` int NOT NULL AUTO_INCREMENT,
  `tp_descricao` varchar(300) NOT NULL,
  `tp_dtinsercao` datetime NOT NULL,
  PRIMARY KEY (`tp_id`),
  UNIQUE KEY `tp_id` (`tp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposprofessores`
--

LOCK TABLES `tiposprofessores` WRITE;
/*!40000 ALTER TABLE `tiposprofessores` DISABLE KEYS */;
INSERT INTO `tiposprofessores` VALUES (1,'Horista','2025-05-20 23:48:39'),(2,'CLT','2025-05-20 23:48:39'),(3,'PJ','2025-05-20 23:48:39');
/*!40000 ALTER TABLE `tiposprofessores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-22 20:42:02
