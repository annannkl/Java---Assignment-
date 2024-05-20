-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 20, 2024 at 01:02 AM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `score` int NOT NULL,
  `player_id` int NOT NULL,
  PRIMARY KEY (`game_id`),
  KEY `player_id` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`game_id`, `username`, `date`, `score`, `player_id`) VALUES
(11, 'Rose', '2024-05-20 01:28:53', 0, 1),
(12, 'Rose', '2024-05-20 01:29:17', 0, 1),
(14, 'Rose', '2024-05-20 01:40:09', 0, 1),
(15, 'Rose', '2024-05-20 01:50:48', 0, 1),
(16, 'Rose', '2024-05-20 01:53:55', 3, 1),
(17, 'Rose', '2024-05-20 02:29:17', 1, 1),
(18, 'Rose', '2024-05-20 02:31:18', 0, 1),
(19, 'Rose', '2024-05-20 02:43:21', 0, 1),
(20, 'Rose', '2024-05-20 03:53:19', 1, 1),
(21, 'Rose', '2024-05-20 03:53:32', 0, 1),
(22, 'Rose', '2024-05-20 03:53:54', 0, 1),
(23, 'Rose', '2024-05-20 03:54:05', 0, 1),
(24, 'Rose', '2024-05-20 03:54:58', 0, 1),
(25, 'Rose', '2024-05-20 03:55:27', 0, 1),
(26, 'Rose', '2024-05-20 03:55:37', 0, 1),
(27, 'Rose', '2024-05-20 03:56:10', 0, 1),
(28, 'Rose', '2024-05-20 03:56:20', 0, 1),
(29, 'Rose', '2024-05-20 03:56:49', 0, 1),
(30, 'Rose', '2024-05-20 03:59:59', 0, 1),
(31, 'Rose', '2024-05-20 04:00:10', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `player_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`player_id`, `username`, `password`, `score`) VALUES
(1, 'Rose', '$2a$12$7JzJEPCzEaqhn1Nx7QLv3OK.swwXqfUVa.AixBOuB32TSMlTqG7XG', 5),
(2, 'Lily', '$2a$12$1dT9o1wz5wTVpZdAM.dmauXO7nTGSpNMTKQgh3rVGERO4Zdpn2prS', 0),
(8, 'Test', '$2a$12$RHZH8QUMNzu9lYvRb9hrRubQCbZYwtZ09BtcuRdjZrXbv7xHtSnRy', 0);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_categories`
--

DROP TABLE IF EXISTS `quiz_categories`;
CREATE TABLE IF NOT EXISTS `quiz_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `api_trivia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `quiz_categories`
--

INSERT INTO `quiz_categories` (`category_id`, `category_name`, `api_trivia`) VALUES
(1, 'General knowledge', '&category=9'),
(2, 'Entertainment: Books', '&category=10'),
(3, 'Entertainment: Film', '&category=11'),
(4, 'Entertainment: Music', '&category=12'),
(5, 'Entertainment: Musicals & Theatres', '&category=13'),
(6, 'Entertainment: Television', '&category=14'),
(7, 'Entertainment: Video Games', '&category=15'),
(8, 'Entertainment: Board Games', '&category=16'),
(9, 'Entertainment: Comics', '&category=29'),
(10, 'Entertainment: Japanese Anime & Manga', '&category=31'),
(11, 'Entertainment: Cartoon & Animations', '&category=32'),
(12, 'Science & Nature', '&category=17'),
(13, 'Science: Computers', '&category=18'),
(14, 'Science: Mathematics', '&category=19'),
(15, 'Science: Gadgets', '&category=30'),
(16, 'Mythology', '&category=20'),
(17, 'Sports', '&category=21'),
(18, 'Geography', '&category=22'),
(19, 'History', '&category=23'),
(20, 'Politics', '&category=24'),
(21, 'Art', '&category=25'),
(22, 'Celebrities', '&category=26'),
(23, 'Animals', '&category=27'),
(24, 'Vehicles', '&category=28');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
