SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS vente_formation;
CREATE DATABASE vente_formation;
USE vente_formation;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `vente-formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `vf_formation`
--

DROP TABLE IF EXISTS `vf_formation`;
CREATE TABLE IF NOT EXISTS `vf_formation` (
  `fo_id_formation` int NOT NULL AUTO_INCREMENT,
  `fo_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fo_description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fo_duree_jour` int NOT NULL,
  `fo_type` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fo_prix` decimal(10,2) NOT NULL,
  PRIMARY KEY (`fo_id_formation`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Déchargement des données de la table `vf_formation`
--

INSERT INTO `vf_formation` (`fo_id_formation`, `fo_name`, `fo_description`, `fo_duree_jour`, `fo_type`, `fo_prix`) VALUES
(1, 'Java', 'Java SE 8 : Syntaxe & Poo', 20, 'Présentiel', 100.00),
(2, 'Java avancé', 'Exceptions, fichiers, Jdbc, thread', 20, 'Présentiel', 100.00),
(3, 'Spring', 'Spring Core/Mvc/Security', 20, 'Présentiel', 100.00),
(4, 'Php frameworks', 'Symphony', 15, 'Présentiel', 100.00),
(5, 'C#', 'DotNet Core', 20, 'Présentiel', 100.00);

-- --------------------------------------------------------

--
-- Structure de la table `vf_commande`
--

DROP TABLE IF EXISTS `vf_commande`;
CREATE TABLE IF NOT EXISTS `vf_commande` (
  `co_id_commande` int NOT NULL AUTO_INCREMENT,
  `co_date_commande` date NOT NULL,
  `co_montant` decimal(15,2) NOT NULL,
  `co_id_utilisateur` int NOT NULL,
  `co_id_client` int NOT NULL,
  PRIMARY KEY (`co_id_commande`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vf_commande_formation`
--

DROP TABLE IF EXISTS `vf_commande_formation`;
CREATE TABLE IF NOT EXISTS `vf_commande_formation` (
  `cf_id_commande` int NOT NULL,
  `cf_id_formation` int NOT NULL,
  PRIMARY KEY (`cf_id_commande`, `cf_id_formation`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vf_utilisateur`
--

DROP TABLE IF EXISTS `vf_utilisateur`;
CREATE TABLE IF NOT EXISTS `vf_utilisateur` (
  `ut_id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `ut_login` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ut_password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ut_id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vf_client`
--

DROP TABLE IF EXISTS `vf_client`;
CREATE TABLE IF NOT EXISTS `vf_client` (
  `cl_id_client` int NOT NULL AUTO_INCREMENT,
  `cl_nom` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cl_prenom` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cl_email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cl_adresse` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cl_tel` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cl_id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Contraintes pour la table `vf_commande_formation`
--
ALTER TABLE `vf_commande_formation`
  ADD CONSTRAINT `vf_commande_formation_ibfk_commande` FOREIGN KEY (`cf_id_commande`) REFERENCES `vf_commande` (`co_id_commande`),
  ADD CONSTRAINT `vf_commande_formation_ibfk_formation` FOREIGN KEY (`cf_id_formation`) REFERENCES `vf_formation` (`fo_id_formation`);

--
-- Contraintes pour la table `vf_commande`
--
ALTER TABLE `vf_commande`
  ADD CONSTRAINT `vf_commande_ibfk_utilisateur` FOREIGN KEY (`co_id_utilisateur`) REFERENCES `vf_utilisateur` (`ut_id_utilisateur`),
  ADD CONSTRAINT `vf_commande_ibfk_client` FOREIGN KEY (`co_id_client`) REFERENCES `vf_client` (`cl_id_client`);

