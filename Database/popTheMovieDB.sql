-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema popthemoviedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema popthemoviedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `popthemoviedb` DEFAULT CHARACTER SET utf8 ;
USE `popthemoviedb` ;

-- -----------------------------------------------------
-- Table `popthemoviedb`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`Utente` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `cognome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`Film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`Film` (
  `id` INT NOT NULL,
  `titolo` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`FilmVisti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`FilmVisti` (
  `Utente_id` INT NOT NULL,
  `Film_id` INT NOT NULL,
  INDEX `fk_filmVisti_Utente_idx` (`Utente_id` ASC) VISIBLE,
  INDEX `fk_FilmVisti_Film1_idx` (`Film_id` ASC) VISIBLE,
  CONSTRAINT `fk_filmVisti_Utente`
    FOREIGN KEY (`Utente_id`)
    REFERENCES `popthemoviedb`.`Utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FilmVisti_Film1`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`Film` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`FilmDaVedere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`FilmDaVedere` (
  `Utente_id` INT NOT NULL,
  `Film_id` INT NOT NULL,
  INDEX `fk_FilmDaVedere_Utente1_idx` (`Utente_id` ASC) VISIBLE,
  INDEX `fk_FilmDaVedere_Film1_idx` (`Film_id` ASC) VISIBLE,
  CONSTRAINT `fk_FilmDaVedere_Utente1`
    FOREIGN KEY (`Utente_id`)
    REFERENCES `popthemoviedb`.`Utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FilmDaVedere_Film1`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`Film` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
