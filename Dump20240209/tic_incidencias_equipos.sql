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
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_equipo` enum('altavoces','impresora','monitor','pantalla_interactiva','portátil_de_aula','portátil_consejería','proyector') COLLATE utf8_spanish_ci NOT NULL,
  `fecha_adquisicion` date DEFAULT NULL,
  `etiqueta` char(8) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `marca` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `modelo` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci,
  `baja` tinyint DEFAULT '0',
  `aula_num` int NOT NULL,
  `puesto` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipos_aulas1_idx` (`aula_num`),
  CONSTRAINT `fk_equipos_aulas1` FOREIGN KEY (`aula_num`) REFERENCES `aulas` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (1,'altavoces','2004-02-02','we','we','we','we',0,1,1),(11,'altavoces','2023-11-15','EQ011','Bose','SoundLink Revolve','Altavoz Bluetooth portátil',0,1,0),(12,'impresora','2023-12-28','EQ012','Brother','HL-L2350DW','Impresora láser monocromática',0,1,0),(13,'monitor','2024-01-10','EQ013','LG','27MK400H-B','Monitor LED de 27 pulgadas con alta definición',0,2,0),(14,'pantalla_interactiva','2024-02-05','EQ014','SMART','Board MX065-V2','Pantalla interactiva de 65 pulgadas',0,3,0),(15,'portátil_de_aula','2024-03-20','EQ015','Asus','VivoBook 14','Portátil ultradelgado para uso en aulas',0,1,0),(16,'portátil_consejería','2024-04-12','EQ016','HP','Elite Dragonfly','Portátil premium para uso en la consejería',0,1,0),(17,'proyector','2024-05-30','EQ017','BenQ','TH671ST','Proyector de corta distancia Full HD',0,4,0),(18,'altavoces','2024-06-18','EQ018','UE','Megaboom 3','Altavoz inalámbrico a prueba de agua y golpes',0,4,0),(19,'impresora','2024-07-25','EQ019','Epson','EcoTank L3110','Impresora multifunción de tanque de tinta',0,5,0),(20,'monitor','2024-08-03','EQ020','Dell','S2421H','Monitor LED de 24 pulgadas con tecnología ComfortView',0,5,0);
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
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
