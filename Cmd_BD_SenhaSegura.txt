-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13/06/2023 às 22:42
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `senhasegura`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `titulo`
--

CREATE TABLE `titulo` (
  `Qtd` int(11) NOT NULL,
  `Id_User` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Senha` varchar(30) NOT NULL,
  `descricao` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `titulo`
--

INSERT INTO `titulo` (`Qtd`, `Id_User`, `Email`, `Senha`, `descricao`) VALUES
(1, 1, 'Email1', '1111', 'gmail'),
(2, 1, 'Email2', '2222', 'Spotify'),
(3, 2, 'Email3', '3333', 'Gmail');

-- --------------------------------------------------------

--
-- Estrutura para tabela `users`
--

CREATE TABLE `users` (
  `IdUser` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Sobrenome` varchar(40) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Senha` varchar(40) NOT NULL,
  `Telefone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `users`
--

INSERT INTO `users` (`IdUser`, `Nome`, `Sobrenome`, `Email`, `Senha`, `Telefone`) VALUES
(1, 'Dinho', 'Souza', 'user1@gmail.com', '4321', '333-333'),
(2, 'Wanderson', 'Souza', 'user2@gmail.com', '1234', '123-456');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `titulo`
--
ALTER TABLE `titulo`
  ADD PRIMARY KEY (`Qtd`),
  ADD KEY `FKey` (`Id_User`);

--
-- Índices de tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`IdUser`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `titulo`
--
ALTER TABLE `titulo`
  MODIFY `Qtd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `IdUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `titulo`
--
ALTER TABLE `titulo`
  ADD CONSTRAINT `FKey` FOREIGN KEY (`Id_User`) REFERENCES `users` (`IdUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
