CREATE TABLE `employe` (
  `id_employee` int(255) NOT NULL AUTO_INCREMENT,
  `nom` varchar(42) DEFAULT NULL,
  `prenom` varchar(42) DEFAULT NULL,
  `mail` varchar(42) DEFAULT NULL,
  `password` varchar(42) DEFAULT NULL,
  `date_arrivee` DATE,
  `date_depart` DATE,
  `habilitation` varchar(42) DEFAULT NULL,
  `id_ligue` int(255) DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ligue` (
   `id_ligue` int(255) NOT NULL AUTO_INCREMENT,
   `nom_ligue` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`id_ligue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `employe` ADD FOREIGN KEY (`id_ligue`) REFERENCES `LIGUE` (`id_ligue`);