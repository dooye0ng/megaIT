-- MariaDB dump 10.17  Distrib 10.5.6-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: myapp
-- ------------------------------------------------------
-- Server version	10.5.6-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_products`
--

DROP TABLE IF EXISTS `app_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_products` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `content` text NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `img_name` text DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `app_products_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_products`
--

LOCK TABLES `app_products` WRITE;
/*!40000 ALTER TABLE `app_products` DISABLE KEYS */;
INSERT INTO `app_products` VALUES (70,'root','root',343,'roooooot','2020-11-11 12:41:51','루트'),(72,'human01','밥그릇 팝니다',3000,'밥그릇 그냥 사용할만한거\n3000원에 그냥 팝니다~','2020-11-11 13:24:49','밥그릇'),(73,'human01','볼링공도 팝니다',30000,'볼링공도 싸게 팔아요','2020-11-11 13:25:15','볼링공'),(74,'lotte','자전거 팔아요~~',3000000,'자전거 싸게 팝니다\n\n\n\n\n\n원래 엄청 비싼거에요\n\n\n\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!','2020-11-11 13:28:46','자전거');
/*!40000 ALTER TABLE `app_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_users`
--

DROP TABLE IF EXISTS `app_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_users` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `pw` varchar(45) NOT NULL,
  `rating` varchar(45) NOT NULL DEFAULT 'Baby',
  `products` int(11) DEFAULT 0,
  `sold` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`no`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users`
--

LOCK TABLES `app_users` WRITE;
/*!40000 ALTER TABLE `app_users` DISABLE KEYS */;
INSERT INTO `app_users` VALUES (35,'root','root','Baby',1,0),(36,'lotte','pw#','Baby',1,1),(37,'human01','pw#','Baby',2,0);
/*!40000 ALTER TABLE `app_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-11 13:29:33
