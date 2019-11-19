-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 15, 2019 at 04:07 AM
-- Server version: 5.7.27-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `UserDB`
--
CREATE DATABASE IF NOT EXISTS `UserDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `UserDB`;

-- --------------------------------------------------------

--
-- Table structure for table `moviedatabase`
--

CREATE TABLE `moviedatabase` (
  `id` int(11) NOT NULL,
  `title` varchar(80) DEFAULT NULL,
  `genere` varchar(20) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `zip_code` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `moviedatabase`
--

INSERT INTO `moviedatabase` (`id`, `title`, `genere`, `duration`, `director`, `zip_code`) VALUES
(11, 'Bahubali-2', 'epic, action', 150, 'S.S. Rajamouli', '001'),
(12, 'MS Dhoni', 'biopic', 190, 'Neeraj Jha', ''),
(13, 'Deadpool (A)', 'action, comedy', 110, 'Mark Stevenson', ''),
(14, 'Avengers', 'action', 115, 'marvel', '');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `shows`
--

CREATE TABLE `shows` (
  `id` int(11) NOT NULL,
  `MId` int(11) DEFAULT NULL,
  `screen` int(11) DEFAULT NULL,
  `slot` int(11) DEFAULT NULL,
  `booked` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shows`
--

INSERT INTO `shows` (`id`, `MId`, `screen`, `slot`, `booked`) VALUES
(6, 11, 1, 3, 100),
(7, 12, 2, 3, 17),
(8, 13, 3, 3, 101),
(9, 14, 1, 2, 10),
(10, 11, 1, 1, 8),
(11, 12, 2, 1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `show_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `class` varchar(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `date_booked` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `isAdmin` int(1) DEFAULT '0',
  `isActive` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `address`, `phone`, `isAdmin`, `isActive`) VALUES
(1, 'gbs', 'code', 'gbs@gmail.com', 'gbs', '9E73C689FC3E776C91ACA5CF0104365CD7679DFA4AA738FAE21A94F85FC2B444', 'ibdan', '0816763', 1, 0),
(4, 'john', 'wick', 'femisorinolu@gmail.com', 'john.wick', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 'No 12, Alagbagba Street, Ashi,bodija,ibadan.', '08167536', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `verification`
--

CREATE TABLE `verification` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `code` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `moviedatabase`
--
ALTER TABLE `moviedatabase`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shows`
--
ALTER TABLE `shows`
  ADD PRIMARY KEY (`id`),
  ADD KEY `MId` (`MId`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `verification`
--
ALTER TABLE `verification`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `moviedatabase`
--
ALTER TABLE `moviedatabase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `password_resets`
--
ALTER TABLE `password_resets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shows`
--
ALTER TABLE `shows`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `verification`
--
ALTER TABLE `verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `shows`
--
ALTER TABLE `shows`
  ADD CONSTRAINT `shows_ibfk_1` FOREIGN KEY (`MId`) REFERENCES `moviedatabase` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--Added user and admin login credentials

INSERT INTO `Users` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `address`, `phone`, `isAdmin`, `isActive`) VALUES
(3, 'lohith', 'kumar', 'kumar.lohithreddy@gmail.com', 'lohith123', '2912CCE860BA3082DB60BA446A2BB3492FF250A264E1E1E9379769D9640D7903', '12, w hickory street, denton texas ', '08167536', 1, 1);
