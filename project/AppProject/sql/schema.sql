CREATE DATABASE IF NOT EXISTS cdydb;
USE cdydb;

CREATE TABLE `app_users` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `pw` varchar(45) NOT NULL,
  `rating` varchar(45) NOT NULL DEFAULT 'Baby',
  `products` int(11) DEFAULT 0,
  `sold` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`no`),
  UNIQUE KEY `id` (`id`)
)

CREATE TABLE `app_products` (
  `no` int(11) NOT NULL AUTO_INCREMENT,test
  `user_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `content` text NOT NULL,
  `regdate` datetime NOT NULL DEFAULT current_timestamp(),
  `img_name` text DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `app_products_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`id`) ON DELETE CASCADE
)