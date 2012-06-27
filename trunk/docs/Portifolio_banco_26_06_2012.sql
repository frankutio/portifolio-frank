-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.26-rc-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema portifolio
--

CREATE DATABASE IF NOT EXISTS portifolio;
USE portifolio;

--
-- Definition of table `image`
--

DROP TABLE IF EXISTS `image`;
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

--
-- Dumping data for table `image`
--

/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;


--
-- Definition of table `log_sistem`
--

DROP TABLE IF EXISTS `log_sistem`;
CREATE TABLE `log_sistem` (
  `id_log` int(3) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) DEFAULT NULL,
  `data_log` date DEFAULT NULL,
  `item` int(3) DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `log_sistem`
--

/*!40000 ALTER TABLE `log_sistem` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_sistem` ENABLE KEYS */;


--
-- Definition of table `portifolio`
--

DROP TABLE IF EXISTS `portifolio`;
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

--
-- Dumping data for table `portifolio`
--

/*!40000 ALTER TABLE `portifolio` DISABLE KEYS */;
/*!40000 ALTER TABLE `portifolio` ENABLE KEYS */;


--
-- Definition of table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
CREATE TABLE `tipo` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `tipo_user` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipo`
--

/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` (`id`,`tipo_user`) VALUES 
 (1,'adm'),
 (2,'usr');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
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
  `lixo` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `Usuario_FKIndex1` (`Tipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_user`,`Tipo_id`,`login`,`nome`,`senha`,`email`,`foto`,`about`,`data_nascimento`,`lixo`) VALUES 
 (1,1,'frankutio','Frank Bezerra Simião','fb007032','frankutio@gmail.com',NULL,NULL,'1984-01-30','false');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
