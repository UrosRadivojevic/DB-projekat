-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2021 at 01:35 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `camci`
--

-- --------------------------------------------------------

--
-- Table structure for table `camac`
--

DROP TABLE IF EXISTS `camac`;
CREATE TABLE `camac` (
  `camacId` bigint(20) NOT NULL,
  `nazivModela` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opisCamca` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `godinaGarancije` int(11) NOT NULL,
  `kolicinaNaStanju` int(11) NOT NULL,
  `cena` double NOT NULL,
  `markaId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `camac`
--

INSERT INTO `camac` (`camacId`, `nazivModela`, `opisCamca`, `godinaGarancije`, `kolicinaNaStanju`, `cena`, `markaId`) VALUES
(1, 'Model1', 'Najbolji sigurno', 3, 10, 3000, 1),
(2, 'Model2', 'Super', 4, 5, 10000, 2),
(3, 'Model33', 'Vrh', 5, 9, 5000, 3),
(5, 'ModelNovo22', 'ssss', 3, 0, 5000, 3),
(6, 'Model Dokumentacija', 'Najbolji model', 33, 3, 10000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kupac`
--

DROP TABLE IF EXISTS `kupac`;
CREATE TABLE `kupac` (
  `kupacId` bigint(20) NOT NULL,
  `ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `brojTelefona` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `napomena` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `kupac`
--

INSERT INTO `kupac` (`kupacId`, `ime`, `prezime`, `adresa`, `mail`, `brojTelefona`, `napomena`) VALUES
(1, 'Uros', 'Peric', 'Vojvode Stepe', 'uros@gmail.com', '0652525155', 'ss'),
(2, 'Biljana', 'Savic', 'Bulevar 10', 'bilja@gmail.com', '0612521515', ''),
(3, 'Marta', 'Djokovic', 'Zvezdara 10', 'marta@gmail.com', '0652515125', ''),
(4, 'ss', 'ss', 'ss', 'ss@gmail.com', 'ss', 'ss'),
(5, 'Marija', 'Stevic', 'Zvecanska 10', 'marijastevic@gmail.com', '065256152', 'Nista');

-- --------------------------------------------------------

--
-- Table structure for table `markacamca`
--

DROP TABLE IF EXISTS `markacamca`;
CREATE TABLE `markacamca` (
  `markaId` bigint(20) NOT NULL,
  `nazivMarke` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `markacamca`
--

INSERT INTO `markacamca` (`markaId`, `nazivMarke`) VALUES
(1, 'Marka1'),
(2, 'Marka2'),
(3, 'Marka3');

-- --------------------------------------------------------

--
-- Table structure for table `osobina`
--

DROP TABLE IF EXISTS `osobina`;
CREATE TABLE `osobina` (
  `rbrOsobine` bigint(20) NOT NULL,
  `camacId` bigint(20) NOT NULL,
  `imeOsobine` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opisOsobine` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `osobina`
--

INSERT INTO `osobina` (`rbrOsobine`, `camacId`, `imeOsobine`, `opisOsobine`) VALUES
(3, 2, 'Lepota', 'Najlepsi'),
(5, 5, 'Nova', 'ss'),
(6, 5, 'Nova2', 'ss'),
(7, 6, 'Brzina', 'Super'),
(8, 6, 'Izdzljivost', 'Onako'),
(13, 1, 'Brzina', 'Super'),
(14, 1, 'Boja', 'dd');

-- --------------------------------------------------------

--
-- Table structure for table `prodavac`
--

DROP TABLE IF EXISTS `prodavac`;
CREATE TABLE `prodavac` (
  `prodavacId` bigint(20) NOT NULL,
  `ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `datumRodjenja` date NOT NULL,
  `brojTelefona` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `prodavac`
--

INSERT INTO `prodavac` (`prodavacId`, `ime`, `prezime`, `datumRodjenja`, `brojTelefona`) VALUES
(1, 'Slavko', 'Markovic', '2021-08-27', '0623451252'),
(2, 'Lazar', 'Ilic', '2021-08-20', '0611111111'),
(3, 'ss', 'ss', '0029-01-18', 'ss'),
(4, 'Bogdan', 'Petrovic', '0034-01-19', '0645256255');

-- --------------------------------------------------------

--
-- Table structure for table `stavkaugovora`
--

DROP TABLE IF EXISTS `stavkaugovora`;
CREATE TABLE `stavkaugovora` (
  `rbrStavke` bigint(20) NOT NULL,
  `ugovorId` bigint(20) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `ukupnaNaknada` double NOT NULL,
  `camacId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stavkaugovora`
--

INSERT INTO `stavkaugovora` (`rbrStavke`, `ugovorId`, `kolicina`, `ukupnaNaknada`, `camacId`) VALUES
(1, 1, 1, 3000, 1),
(2, 1, 1, 7000, 2),
(3, 2, 1, 3000, 1),
(4, 2, 1, 5000, 3),
(5, 3, 3, 30000, 2),
(6, 3, 3, 15000, 5),
(7, 4, 3, 9000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ugovor`
--

DROP TABLE IF EXISTS `ugovor`;
CREATE TABLE `ugovor` (
  `ugovorId` bigint(20) NOT NULL,
  `opisUgovora` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `datumKreiranja` date NOT NULL,
  `ukupanDug` double NOT NULL,
  `kupacId` bigint(20) NOT NULL,
  `prodavacId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ugovor`
--

INSERT INTO `ugovor` (`ugovorId`, `opisUgovora`, `datumKreiranja`, `ukupanDug`, `kupacId`, `prodavacId`) VALUES
(1, 'Opis ugovora', '2021-08-24', 10000, 2, 2),
(2, 'Opis', '2021-08-23', 8000, 1, 1),
(3, 'Ugovor novi', '2021-08-23', 75000, 1, 1),
(4, 'ss', '2021-08-23', 9000, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `camac`
--
ALTER TABLE `camac`
  ADD PRIMARY KEY (`camacId`),
  ADD KEY `markaId` (`markaId`);

--
-- Indexes for table `kupac`
--
ALTER TABLE `kupac`
  ADD PRIMARY KEY (`kupacId`);

--
-- Indexes for table `markacamca`
--
ALTER TABLE `markacamca`
  ADD PRIMARY KEY (`markaId`);

--
-- Indexes for table `osobina`
--
ALTER TABLE `osobina`
  ADD PRIMARY KEY (`rbrOsobine`,`camacId`),
  ADD KEY `camacId` (`camacId`);

--
-- Indexes for table `prodavac`
--
ALTER TABLE `prodavac`
  ADD PRIMARY KEY (`prodavacId`);

--
-- Indexes for table `stavkaugovora`
--
ALTER TABLE `stavkaugovora`
  ADD PRIMARY KEY (`rbrStavke`,`ugovorId`),
  ADD KEY `ugovorId` (`ugovorId`),
  ADD KEY `camacId` (`camacId`);

--
-- Indexes for table `ugovor`
--
ALTER TABLE `ugovor`
  ADD PRIMARY KEY (`ugovorId`),
  ADD KEY `prodavacId` (`prodavacId`),
  ADD KEY `kupacId` (`kupacId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `camac`
--
ALTER TABLE `camac`
  MODIFY `camacId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `kupac`
--
ALTER TABLE `kupac`
  MODIFY `kupacId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `markacamca`
--
ALTER TABLE `markacamca`
  MODIFY `markaId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `osobina`
--
ALTER TABLE `osobina`
  MODIFY `rbrOsobine` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `prodavac`
--
ALTER TABLE `prodavac`
  MODIFY `prodavacId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `stavkaugovora`
--
ALTER TABLE `stavkaugovora`
  MODIFY `rbrStavke` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ugovor`
--
ALTER TABLE `ugovor`
  MODIFY `ugovorId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `camac`
--
ALTER TABLE `camac`
  ADD CONSTRAINT `camac_ibfk_1` FOREIGN KEY (`markaId`) REFERENCES `markacamca` (`markaId`);

--
-- Constraints for table `osobina`
--
ALTER TABLE `osobina`
  ADD CONSTRAINT `osobina_ibfk_1` FOREIGN KEY (`camacId`) REFERENCES `camac` (`camacId`);

--
-- Constraints for table `stavkaugovora`
--
ALTER TABLE `stavkaugovora`
  ADD CONSTRAINT `stavkaugovora_ibfk_1` FOREIGN KEY (`ugovorId`) REFERENCES `ugovor` (`ugovorId`),
  ADD CONSTRAINT `stavkaugovora_ibfk_2` FOREIGN KEY (`camacId`) REFERENCES `camac` (`camacId`);

--
-- Constraints for table `ugovor`
--
ALTER TABLE `ugovor`
  ADD CONSTRAINT `ugovor_ibfk_1` FOREIGN KEY (`prodavacId`) REFERENCES `prodavac` (`prodavacId`),
  ADD CONSTRAINT `ugovor_ibfk_2` FOREIGN KEY (`kupacId`) REFERENCES `kupac` (`kupacId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
