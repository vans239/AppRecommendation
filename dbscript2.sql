drop database if exists MyDB;
create database MyDB default character set utf8 collate utf8_bin;
set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_server=utf8;

use MyDB;

CREATE  TABLE IF NOT EXISTS `myDB`.`developer` (
  `name` VARCHAR(60) NOT NULL ,
  `country` VARCHAR(45) NULL ,
  `place` VARCHAR(45) NULL ,
  `foundation` DATETIME NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `myDB`.`application` (
  `name` VARCHAR(60) NOT NULL ,
  `type` VARCHAR(45) NULL ,
  `OS` VARCHAR(45) NULL ,
  `developer` VARCHAR(45) NULL ,
  `language` VARCHAR(45) NULL ,
  `size` INT NULL ,
  `price` DOUBLE NULL ,
  PRIMARY KEY (`name`) ,
  INDEX `developer` (`developer` ASC) ,
  CONSTRAINT `developer`
    FOREIGN KEY (`developer` )
    REFERENCES `myDB`.`developer` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `myDB`.`features` (
  `id` INT NOT NULL ,
  `description` VARCHAR(500) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `myDB`.`types` (
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `myDB`.`extra_table` (
  `idextra_table` INT NOT NULL ,
  `type` VARCHAR(45) NULL ,
  `application` VARCHAR(60) NULL ,
  PRIMARY KEY (`idextra_table`) ,
  INDEX `type` (`type` ASC) ,
  INDEX `application` (`application` ASC) ,
  CONSTRAINT `type`
    FOREIGN KEY (`type` )
    REFERENCES `myDB`.`types` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `application`
    FOREIGN KEY (`application` )
    REFERENCES `myDB`.`application` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `myDB`.`version` (
  `version_no` VARCHAR(20) NOT NULL ,
  `app_name` VARCHAR(45) NULL ,
  `feature` INT NULL ,
  PRIMARY KEY (`version_no`) ,
  INDEX `features` (`feature` ASC) ,
  INDEX `app_name` (`app_name` ASC) ,
  CONSTRAINT `features`
    FOREIGN KEY (`feature` )
    REFERENCES `myDB`.`features` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `app_name`
    FOREIGN KEY (`app_name` )
    REFERENCES `myDB`.`application` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;