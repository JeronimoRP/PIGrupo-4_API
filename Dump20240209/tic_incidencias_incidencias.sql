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
-- Table structure for table `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidencias` (
  `num` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('EQUIPOS','CUENTAS','WIFI','INTERNET','SOFTWARE') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `subtipo_id` int NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_cierre` datetime DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `estado` enum('abierta','asignada','en proceso','enviada a Infortec','resuelta','cerrada') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `adjunto_url` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci,
  `creador_id` int NOT NULL,
  `responsable_id` int DEFAULT NULL,
  `equipo_id` int DEFAULT NULL,
  `tiempo_dec` time DEFAULT NULL,
  PRIMARY KEY (`num`),
  KEY `fk_incidencias_incidencias_subtipos1_idx` (`subtipo_id`),
  KEY `fk_incidencias_personal1_idx` (`creador_id`),
  KEY `fk_incidencias_personal2_idx` (`responsable_id`),
  KEY `fk_incidencias_equipos1_idx` (`equipo_id`),
  CONSTRAINT `fk_incidencias_equipos1` FOREIGN KEY (`equipo_id`) REFERENCES `equipos` (`id`),
  CONSTRAINT `fk_incidencias_incidencias_subtipos1` FOREIGN KEY (`subtipo_id`) REFERENCES `incidencias_subtipos` (`id`),
  CONSTRAINT `fk_incidencias_personal1` FOREIGN KEY (`creador_id`) REFERENCES `personal` (`id`),
  CONSTRAINT `fk_incidencias_personal2` FOREIGN KEY (`responsable_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias`
--

LOCK TABLES `incidencias` WRITE;
/*!40000 ALTER TABLE `incidencias` DISABLE KEYS */;
INSERT INTO `incidencias` VALUES (1,'EQUIPOS',1,'2024-02-06 00:00:00','2024-04-05 00:00:00','er','abierta','wer',1,5,1,'00:00:00');
/*!40000 ALTER TABLE `incidencias` ENABLE KEYS */;
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
