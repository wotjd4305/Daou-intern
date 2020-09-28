-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema intern2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema intern2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `intern2` DEFAULT CHARACTER SET utf8mb4 ;
USE `intern2` ;

-- -----------------------------------------------------
-- Table `intern2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `num` BIGINT NULL,
  `name` CHAR(10) NULL,
  `password` CHAR(20) NULL,
  `department` CHAR(20) NULL,
  `image` CHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `title` CHAR(100) NULL,
  `price` INT NULL,
  `content` TEXT NULL,
  `category` CHAR(20) NULL,
  `status` CHAR(10) NULL,
  `delflag` TINYINT NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_item_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`search`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`search` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `keyword` CHAR(40) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_search_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`point` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `date` DATE NULL,
  `score` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_point_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`image` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT NOT NULL,
  `picture` CHAR(100) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_image_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `intern2`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`chatroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`chatroom` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT NOT NULL,
  `seller_id` BIGINT NOT NULL,
  `buyer_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chatroom_user2`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `intern2`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_user1`
    FOREIGN KEY (`seller_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `intern2`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intern2`.`message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `chatroom_id` BIGINT NOT NULL,
  `seller_id` BIGINT NOT NULL,
  `buyer_id` BIGINT NOT NULL,
  `content` TEXT NULL,
  `send_time` DATETIME NULL,
  `receive_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_message_chatroom1`
    FOREIGN KEY (`chatroom_id`)
    REFERENCES `intern2`.`chatroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`seller_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `intern2`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
