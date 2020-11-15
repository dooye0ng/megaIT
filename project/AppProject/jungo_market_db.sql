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
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_products`
--

LOCK TABLES `app_products` WRITE;
/*!40000 ALTER TABLE `app_products` DISABLE KEYS */;
INSERT INTO `app_products` VALUES (84,'root','루트 팝니다',200000,'루트 팝니다~~~','2020-11-12 17:32:23','루트'),(85,'root','자전거',2000000,'자전거 팝니다~~~~','2020-11-12 17:32:33','자전거'),(86,'king','볼링공!',4000,'볼링공 팝니다~!!','2020-11-12 17:32:52','볼링공'),(88,'human','밥그릇',30000,'귀한 밥그릇하나 팝니다!!!','2020-11-12 17:34:01','밥그릇'),(94,'human','개 사료 팝니다~~',30000,'개 사료 팔아요~~~~~','2020-11-12 18:36:00','개사료');
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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users`
--

LOCK TABLES `app_users` WRITE;
/*!40000 ALTER TABLE `app_users` DISABLE KEYS */;
INSERT INTO `app_users` VALUES (44,'root','root','Baby',2,0),(45,'king','king','Newbie',1,6),(46,'user','user','Baby',0,0),(47,'human','human','Baby',2,0);
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

-- Dump completed on 2020-11-13 16:48:48
