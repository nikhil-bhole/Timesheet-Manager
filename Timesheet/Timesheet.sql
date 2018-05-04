-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 11, 2018 at 06:10 PM
-- Server version: 5.7.20-0ubuntu0.16.04.1
-- PHP Version: 7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Timesheet`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getWeeklyData` ()  BEGIN
SELECT 
   ( select SEC_TO_TIME( SUM( TIME_TO_SEC( `officehours` ) ) ) from view_dailyrecord 
     where YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1)  ) as sumOfficeHours,
    ( select SEC_TO_TIME( SUM( TIME_TO_SEC( `lunchhours` ) ) ) from view_dailyrecord 
     where YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1)  ) as sumLunchHours,
      ( select SEC_TO_TIME( SUM( TIME_TO_SEC( `breakhours` ) ) ) from view_dailyrecord 
     where YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1)  ) as sumBreakHours,
      ( select SEC_TO_TIME( SUM( TIME_TO_SEC( `workhours` ) ) ) from view_dailyrecord 
     where YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1)  ) as sumWorkHours;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registeruser` (IN `firstname` VARCHAR(50), IN `lastname` VARCHAR(50), IN `username` VARCHAR(50), IN `email` VARCHAR(50), IN `password` VARCHAR(50))  BEGIN
    insert into users (firstname,lastname,username,email,password) values (firstname ,lastname,username,email,password);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_setrecords` (IN `userid` VARCHAR(50), IN `daten` DATE, IN `p_daystart` TIME, IN `p_lunchstart` TIME, IN `p_lunchend` TIME, IN `p_breakstart` TIME, IN `p_breakend` TIME, IN `p_dayend` TIME, IN `p_casualleave` TIME, IN `p_sickleave` TIME, IN `p_holidays` TIME, IN `p_leavewithoutpay` TIME)  BEGIN
insert into dailyrecord (user_id,date,daystart,lunchstart,lunchend,breakstart,breakend,dayend,casualleave,sickleave,holidays,leavewithoutpay) values 			  (userid,daten,p_daystart,p_lunchstart,p_lunchend,p_breakstart,p_breakend,p_dayend,p_casualleave,p_sickleave,p_holidays,p_leavewithoutpay) 
 	ON DUPLICATE KEY UPDATE 
    	
		daystart = CASE WHEN p_daystart IS NOT NULL THEN p_daystart ELSE daystart END,
        lunchstart = CASE WHEN p_lunchstart IS NOT NULL THEN p_lunchstart ELSE lunchstart END,
    	lunchend = CASE WHEN p_lunchend IS NOT NULL THEN p_lunchend ELSE lunchend END,
    	breakstart = CASE WHEN p_breakstart IS NOT NULL THEN p_breakstart ELSE breakstart END,
    	breakend = CASE WHEN p_breakend IS NOT NULL THEN p_breakend ELSE breakend END,
    	dayend = CASE WHEN p_dayend IS NOT NULL THEN p_dayend ELSE dayend END,
    	casualleave = CASE WHEN p_casualleave IS NOT NULL THEN p_casualleave ELSE casualleave END,
    	sickleave = CASE WHEN p_sickleave IS NOT NULL THEN p_sickleave ELSE sickleave END,
    	holidays = CASE WHEN p_holidays IS NOT NULL THEN p_holidays ELSE holidays END,
    	leavewithoutpay = CASE WHEN p_leavewithoutpay IS NOT NULL THEN p_leavewithoutpay ELSE leavewithoutpay END;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dailyrecord`
--

CREATE TABLE `dailyrecord` (
  `id` int(100) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `daystart` time DEFAULT NULL,
  `lunchstart` time DEFAULT NULL,
  `lunchend` time DEFAULT NULL,
  `breakstart` time DEFAULT NULL,
  `breakend` time DEFAULT NULL,
  `dayend` time DEFAULT NULL,
  `casualleave` time DEFAULT NULL,
  `sickleave` time DEFAULT NULL,
  `holidays` time DEFAULT NULL,
  `leavewithoutpay` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dailyrecord`
--

INSERT INTO `dailyrecord` (`id`, `user_id`, `date`, `daystart`, `lunchstart`, `lunchend`, `breakstart`, `breakend`, `dayend`, `casualleave`, `sickleave`, `holidays`, `leavewithoutpay`) VALUES
(142, '1', '2018-01-08', '09:30:00', '13:29:00', '14:01:00', '16:49:00', '17:17:00', '18:30:00', NULL, NULL, NULL, NULL),
(148, '1', '2018-01-10', '09:30:00', '13:15:00', '13:44:00', '16:43:00', '17:14:00', '19:05:00', NULL, NULL, NULL, NULL),
(149, '1', '2018-01-11', '09:30:00', '13:23:00', '13:52:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(162, '1', '2018-01-12', '09:30:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(169, '4', '2018-01-13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `firstname`, `lastname`, `username`, `email`, `password`) VALUES
(1, 'Nikhil', 'bhole', 'nikhil_b', NULL, '123456'),
(4, 'Madhuri', 'Kadam', 'madhuri_k', 'madhuri@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_dailyrecord`
--
CREATE TABLE `view_dailyrecord` (
`date` date
,`daystart` time
,`lunchstart` time
,`lunchend` time
,`breakstart` time
,`breakend` time
,`dayend` time
,`casualleave` time
,`sickleave` time
,`holidays` time
,`leavewithoutpay` time
,`officehours` time
,`lunchhours` time
,`breakhours` time
,`workhours` varchar(10)
);

-- --------------------------------------------------------

--
-- Structure for view `view_dailyrecord`
--
DROP TABLE IF EXISTS `view_dailyrecord`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_dailyrecord`  AS  select `dailyrecord`.`date` AS `date`,`dailyrecord`.`daystart` AS `daystart`,`dailyrecord`.`lunchstart` AS `lunchstart`,`dailyrecord`.`lunchend` AS `lunchend`,`dailyrecord`.`breakstart` AS `breakstart`,`dailyrecord`.`breakend` AS `breakend`,`dailyrecord`.`dayend` AS `dayend`,`dailyrecord`.`casualleave` AS `casualleave`,`dailyrecord`.`sickleave` AS `sickleave`,`dailyrecord`.`holidays` AS `holidays`,`dailyrecord`.`leavewithoutpay` AS `leavewithoutpay`,timediff(`dailyrecord`.`dayend`,`dailyrecord`.`daystart`) AS `officehours`,timediff(`dailyrecord`.`lunchend`,`dailyrecord`.`lunchstart`) AS `lunchhours`,timediff(`dailyrecord`.`breakend`,`dailyrecord`.`breakstart`) AS `breakhours`,(case when ((`dailyrecord`.`casualleave` = '8:00:00') or (`dailyrecord`.`sickleave` = '8:00:00') or (`dailyrecord`.`holidays` = '8:00:00') or (`dailyrecord`.`leavewithoutpay` = '8:00:00')) then '08:00:00' else timediff(timediff(`dailyrecord`.`dayend`,`dailyrecord`.`daystart`),addtime(timediff(`dailyrecord`.`lunchend`,`dailyrecord`.`lunchstart`),timediff(`dailyrecord`.`breakend`,`dailyrecord`.`breakstart`))) end) AS `workhours` from `dailyrecord` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dailyrecord`
--
ALTER TABLE `dailyrecord`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `date` (`date`),
  ADD UNIQUE KEY `unique_index` (`user_id`,`date`),
  ADD UNIQUE KEY `tbl_user_ip` (`user_id`,`date`),
  ADD KEY `user_id` (`user_id`,`date`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dailyrecord`
--
ALTER TABLE `dailyrecord`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
