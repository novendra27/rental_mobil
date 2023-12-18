-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2023 at 03:49 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalmobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_auth`
--

CREATE TABLE `tb_auth` (
  `id_user` varchar(12) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `no_ktp` varchar(20) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `no_hp` varchar(50) DEFAULT NULL,
  `role` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_auth`
--

INSERT INTO `tb_auth` (`id_user`, `username`, `password`, `no_ktp`, `alamat`, `no_hp`, `role`) VALUES
('US001', 'admin', 'admin', '0', '', '0', 2),
('US002', 'adi', 'adi', '220605110102', 'Sidoarjo', '081992354887', 1),
('US003', 'johar', 'johar', '220605110095', 'Bojonegoro', '081992354894', 1),
('US004', 'izzan', 'izzan', '220605110082', 'Madiun', '081334927394', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_mobil`
--

CREATE TABLE `tb_mobil` (
  `id_mobil` varchar(12) NOT NULL,
  `nama_mobil` varchar(50) NOT NULL,
  `status_tersedia` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_mobil`
--

INSERT INTO `tb_mobil` (`id_mobil`, `nama_mobil`, `status_tersedia`) VALUES
('MO001', 'Supra', 0),
('MO002', 'Kijang', 0),
('MO003', 'Chery', 1),
('MO004', 'Tesla', 0),
('MO005', 'Alphard', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_sewa`
--

CREATE TABLE `tb_sewa` (
  `id_sewa` varchar(12) NOT NULL,
  `tgl_sewa` datetime NOT NULL,
  `tgl_pengembalian` datetime NOT NULL,
  `tgl_dikembalikan` datetime DEFAULT NULL,
  `status_pengembalian` tinyint(1) NOT NULL,
  `id_mobil` varchar(12) NOT NULL,
  `id_user` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_sewa`
--

INSERT INTO `tb_sewa` (`id_sewa`, `tgl_sewa`, `tgl_pengembalian`, `tgl_dikembalikan`, `status_pengembalian`, `id_mobil`, `id_user`) VALUES
('SE001', '2023-12-18 09:32:58', '2023-12-19 09:32:58', NULL, 1, 'MO001', 'US002'),
('SE02', '2023-12-18 09:35:23', '2023-12-20 09:35:23', NULL, 0, 'MO001', 'US002'),
('SE03', '2023-12-18 09:35:28', '2023-12-19 09:35:28', NULL, 1, 'MO002', 'US002'),
('SE04', '2023-12-18 09:35:30', '2023-12-21 09:35:30', NULL, 1, 'MO003', 'US002'),
('SE05', '2023-12-18 09:36:24', '2023-12-23 09:36:24', NULL, 1, 'MO002', 'US002'),
('SE06', '2023-12-18 09:36:40', '2023-12-19 09:36:40', NULL, 0, 'MO004', 'US002'),
('SE07', '2023-12-18 09:36:43', '2023-12-22 09:36:43', NULL, 1, 'MO005', 'US002'),
('SE08', '2023-12-18 09:41:02', '2023-12-20 09:41:02', NULL, 0, 'MO002', 'US002');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_auth`
--
ALTER TABLE `tb_auth`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `tb_mobil`
--
ALTER TABLE `tb_mobil`
  ADD PRIMARY KEY (`id_mobil`);

--
-- Indexes for table `tb_sewa`
--
ALTER TABLE `tb_sewa`
  ADD PRIMARY KEY (`id_sewa`),
  ADD KEY `fk_id_mobil` (`id_mobil`),
  ADD KEY `fk_id_user` (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_sewa`
--
ALTER TABLE `tb_sewa`
  ADD CONSTRAINT `fk_id_mobil` FOREIGN KEY (`id_mobil`) REFERENCES `tb_mobil` (`id_mobil`),
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `tb_auth` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
