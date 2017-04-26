-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: portifolio
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id_image` int(3) NOT NULL AUTO_INCREMENT,
  `Portifolio_id` int(3) NOT NULL,
  `alt` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `ordem` int(2) DEFAULT NULL,
  `lixo` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`id_image`),
  KEY `Image_FKIndex1` (`Portifolio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_sistem`
--

DROP TABLE IF EXISTS `log_sistem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_sistem` (
  `id_log` int(3) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) DEFAULT NULL,
  `data_log` date DEFAULT NULL,
  `item` int(3) DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_sistem`
--

LOCK TABLES `log_sistem` WRITE;
/*!40000 ALTER TABLE `log_sistem` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_sistem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portifolio`
--

DROP TABLE IF EXISTS `portifolio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portifolio` (
  `id_portifolio` int(3) NOT NULL AUTO_INCREMENT,
  `Usuario_id` int(3) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `servico` varchar(100) NOT NULL,
  `tecnologia` varchar(1000) DEFAULT NULL,
  `descricao` varchar(2000) DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_fim` date DEFAULT NULL,
  `link` varchar(100) DEFAULT NULL,
  `img_capa` varchar(100) DEFAULT NULL,
  `id_img_capa` varchar(20) NOT NULL,
  `lixo` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`id_portifolio`),
  KEY `Portifilio_FKIndex1` (`Usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portifolio`
--

LOCK TABLES `portifolio` WRITE;
/*!40000 ALTER TABLE `portifolio` DISABLE KEYS */;
/*!40000 ALTER TABLE `portifolio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `tipo_user` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'adm'),(2,'usr');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_user` int(3) NOT NULL AUTO_INCREMENT,
  `Tipo_id` int(3) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `senha` varchar(8) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `about` varchar(336) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `bloq` enum('true','false') DEFAULT NULL,
  `super` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `Usuario_FKIndex1` (`Tipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'frankutio','Frank Bezerra Simião','fb007032','frankutio@gmail.com',NULL,NULL,'1984-01-30','false','true'),(2,1,'juju.teste','juliana Aragão','12345678','juju@email.com',NULL,'<p>teste</p>','1984-01-30','false','false');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-26 12:45:12
