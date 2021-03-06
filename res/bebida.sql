﻿CREATE DATABASE mobywine;
USE mobywine;
-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 16-Maio-2018 às 02:32
-- Versão do servidor: 5.6.20-log
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mobywine`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `bebida`
--

CREATE TABLE IF NOT EXISTS `bebida` (
`id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `preco` decimal(12,2) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `regiao` varchar(100) NOT NULL,
  `nome_produtor` varchar(100) NOT NULL,
  `ano_safra` varchar(4) NOT NULL,
  `descricao` text NOT NULL,
  `tipo_vinho` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bebida`
--
ALTER TABLE `bebida`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bebida`
--
ALTER TABLE `bebida`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


INSERT INTO `bebida`(`nome`, `preco`, `pais`, `regiao`, `nome_produtor`, `ano_safra`, `descricao`, `tipo_vinho`) 
VALUES 
("vinho1", 100.00, "Brasil", "SP", "Vinci Wines", "1980", "Good wine!", "1"),
("vinho2", 200.00, "Brasil", "SP", "Vinci Wines", "1990", "Great wine!", "2"),
("vinho3", 300.00, "Brasil", "SP", "Vinci Wines", "2000", "Awesome wine", "3");