-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: tic_incidencias
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` char(9) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(80) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `localidad` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` char(5) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `tlf` char(9) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `activo` tinyint DEFAULT '1',
  `departamento_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  KEY `fk_personal_departamentos1_idx` (`departamento_id`),
  CONSTRAINT `fk_personal_departamentos1` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'1','er','we','qe','qwe','qew','qwe','qwe',1,1),(5,'34567812E','Carlos','Fernández',NULL,'Calle Recoletos 5','Madrid','28005','789123456',1,1),(6,'23456789F','Ana','Rodríguez',NULL,'Calle Serrano 6','Madrid','28006','321654987',1,1),(7,'45678901G','Javier','Sánchez',NULL,'Calle Princesa 7','Madrid','28007','987654321',1,1),(8,'56789012H','Elena','Gutiérrez',NULL,'Calle Granada 8','Madrid','28008','654321789',1,1);
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-09 20:09:21
