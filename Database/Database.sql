-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bookstore2
CREATE DATABASE IF NOT EXISTS `bookstore2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookstore2`;

-- Dumping structure for table bookstore2.hibernate_sequences
CREATE TABLE IF NOT EXISTS `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore2.hibernate_sequences: 1 rows
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
	('default', 0);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;

-- Dumping structure for table bookstore2.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKkp5k52qtiygd8jkag4hayd0qg` (`product_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore2.orders: 6 rows
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`order_id`, `product_id`, `user_id`) VALUES
	(1, 2, 1),
	(2, 6, 2),
	(3, 4, 3),
	(4, 2, 4),
	(5, 2, 5),
	(6, 2, 6);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table bookstore2.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_type` varchar(31) NOT NULL,
  `product_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `authors_name` varchar(255) DEFAULT NULL,
  `pieces` int(11) DEFAULT NULL,
  `maximum_number_of_players` int(11) DEFAULT NULL,
  `minimum_number_of_players` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore2.products: 6 rows
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`product_type`, `product_id`, `name`, `price`, `stock`, `authors_name`, `pieces`, `maximum_number_of_players`, `minimum_number_of_players`) VALUES
	('Book', 1, 'The Count of Monte Cristo', 23.55, 3, 'Alexandre Dumas', NULL, NULL, NULL),
	('Book', 2, 'Clean Code', 31.99, 0, 'Samuil Zahariev', NULL, NULL, NULL),
	('Puzzle', 3, 'Mona Liza', 14.99, 5, NULL, 3200, NULL, NULL),
	('Puzzle', 4, 'Titanic', 19.99, 5, NULL, 2000, NULL, NULL),
	('BoardGame', 5, 'Monopoly', 17.6, 5, NULL, NULL, 6, 2),
	('BoardGame', 6, 'Texas Holdem', 20, 5, NULL, NULL, 9, 2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table bookstore2.requests
CREATE TABLE IF NOT EXISTS `requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_type` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9b7l56r4k3xg2kicc29vf9exq` (`product_id`),
  KEY `FK8usbpx9csc6opbjg1d7kvtf8c` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore2.requests: 1 rows
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` (`id`, `request_type`, `product_id`, `user_id`) VALUES
	(3, 'EXISTENT', 2, 9);
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;

-- Dumping structure for table bookstore2.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore2.users: 9 rows
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `address`, `first_name`, `last_name`, `phone_number`) VALUES
	(1, 'Teteven', 'Samuil', 'Zahariev', '0876582825'),
	(2, 'Plovdiv', 'Ivan', 'Dimitrov', '089872543'),
	(3, 'Burgas', 'Petko', 'Petrov', '088345265'),
	(4, 'sofia', 'Georgi', 'Dimitrov', '087620912'),
	(5, 'Montana', 'Pesho', 'Peshov', '086442342'),
	(6, 'Pleven', 'Gosho', 'Goshov', '084565599'),
	(7, 'fdksdjfsd', 'skfjksdjfsd', 'fjkdsjfksdf', '02940234'),
	(8, 'Teteven', 'fdsfsd', 'fsdfs', '08544646'),
	(9, 'Dobrinishte', 'Ivan', 'Tihomirov', '0879542515');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
