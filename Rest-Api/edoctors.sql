-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 09, 2021 at 08:52 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `edoctors`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `patient_id` varchar(255) NOT NULL,
  `doctor_id` varchar(255) NOT NULL,
  `payment_status` tinyint(1) NOT NULL DEFAULT 0,
  `link` varchar(255) NOT NULL,
  `serial` int(5) NOT NULL,
  `date` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`patient_id`, `doctor_id`, `payment_status`, `link`, `serial`, `date`) VALUES
('+8801708924486', '12345', 0, '+8801708924486123451631857791493', 1, '2021-8-17'),
('+8801708924486', '12345', 0, '+8801708924486123451631857798720', 2, '2021-8-17'),
('+8801708924486', '12345', 0, '+8801708924486123451631857804715', 3, '2021-8-17'),
('+8801708924486', '12456', 0, '+8801708924486124561631857824852', 1, '2021-8-17');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `name` varchar(255) NOT NULL,
  `bmdc` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `speciality` varchar(255) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `qualifications` varchar(255) NOT NULL,
  `bio` varchar(1024) NOT NULL,
  `fee` varchar(5) NOT NULL DEFAULT '0',
  `rating` float NOT NULL DEFAULT 0,
  `image_url` varchar(255) NOT NULL DEFAULT 'not set',
  `password` varchar(1024) NOT NULL DEFAULT '12345'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`name`, `bmdc`, `phone`, `speciality`, `designation`, `qualifications`, `bio`, `fee`, `rating`, `image_url`, `password`) VALUES
('Md.', '12345', '01708924486', 'Corona Specialist', 'Medical, Dhaka Medical College', 'MBBS,FCPS(DMC)', 'I am a vla bla bla', '500', 0, 'images/doctors/12345.jpeg', '12345'),
('Tanvir', '123456', '01708924486', 'Corona Specialist', 'Medical officer, Dhaka Medical College', 'MBBS,FCPS(DMC)', 'I am a vla bla bla', '800', 0, 'not set', '12345'),
('Ahmmad', '12456', '01708924486', 'Corona Specialist', 'Medical officer, Dhaka Medical College', 'MBBS,FCPS(DMC)', 'I am a vla bla bla', '1000', 0, 'not set', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `phone` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date_of_birth` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT 'not set'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`phone`, `first_name`, `last_name`, `email`, `date_of_birth`, `gender`, `password`, `image_url`) VALUES
('+8801521455494', 'Tanvir', 'Ahmmad', 'tanvirahmed1024@gmail.com', '19 May,2021', 'Male', '$2a$10$leCjRUhYGdt1lv4USU5AoexmaRDbGBYHysMfhXnYWxmhBsIx7c7Na', 'not set'),
('+8801708924486', 'Tanvir', 'Ahmmad', 'tanvirahmed1024@gmail.com', '23 Dec,1998', 'Male', '$2a$10$b7IQJ5D6q8/ABqoY0YEOT.0F1YPMsQZ3zBpwz8mPMqpy84/Pt6fTW', 'images/+8801708924486.jpeg'),
('+8801922370223', 'tanvir', 'ahmmad', 't@gmail.com', '22 march,2000', 'male', '$2b$10$pv9kj.tGq3PW/dyxG4p9AON4eBiTa8Dpreap9a0GgbNIAzPRvoJOe', 'not set');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`link`),
  ADD KEY `appointments_ibfk_1` (`patient_id`),
  ADD KEY `appointments_ibfk_2` (`doctor_id`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`bmdc`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`phone`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `users` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`bmdc`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
