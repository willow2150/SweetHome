-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sweethome
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sweethome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sweethome` DEFAULT CHARACTER SET utf8mb3 ;
USE `sweethome` ;

-- -----------------------------------------------------
-- Table `sweethome`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweethome`.`user` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_pwd` VARCHAR(25) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_address` VARCHAR(50) NOT NULL,
  `user_email` VARCHAR(50) NOT NULL,
  `type` VARCHAR(15) NOT NULL DEFAULT 'general',
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  `origin_profile_img_name` VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `sweethome`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweethome`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL DEFAULT '',
  `hit` INT NULL DEFAULT '0',
  `reg_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `fk_board_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sweethome`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sweethome`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweethome`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `content` VARCHAR(500) NOT NULL DEFAULT '',
  `reg_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_board1_idx` (`article_no` ASC) VISIBLE,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_board1`
    FOREIGN KEY (`article_no`)
    REFERENCES `sweethome`.`board` (`article_no`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sweethome`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sweethome`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweethome`.`favorite` (
  `fav_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `sido` VARCHAR(30) NOT NULL,
  `gugun` VARCHAR(30) NOT NULL,
  `dong` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`fav_id`),
  INDEX `fk_favorite_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_favorite_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `sweethome`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sweethome`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweethome`.`notice` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL DEFAULT '',
  `hit` INT NULL DEFAULT '0',
  `reg_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `fk_notice_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_notice_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sweethome`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

##################################################################

DROP VIEW IF EXISTS house;

CREATE VIEW house AS
SELECT aptName, dealAmount, area, floor, buildYear, sido, gugun, dong, jibun, road, dealYear, dealMonth, dealDay, lng, lat
FROM
	(SELECT aptCode, dealAmount, area, floor, dealYear, dealMonth, dealDay 
     FROM housedeal 
     WHERE cancelDealType = ''
	 ) AS hd
		NATURAL JOIN
	(SELECT aptCode, apartmentName AS aptName, buildYear, sidoName AS sido, gugunName AS gugun, dongName AS dong, jibun, roadName AS road, lng, lat 
     FROM houseinfo NATURAL JOIN dongcode
     ) AS hi;

INSERT INTO user(`user_id`, `user_pwd`, `user_name`, `user_address`, `user_email`, `type`) VALUES('admin', '1234', '삼싸피', '서울특별시 강남구 역삼역 1번 출구', 'ssafy@ssafy.com', 'admin');
INSERT INTO notice(`user_id`, `subject`, `content`) VALUES('admin', '공지사항 테스트입니다.', '공지사항 테스트입니다.');
INSERT INTO notice(`user_id`, `subject`, `content`) VALUES('admin', '공지사항 테스트2입니다.', '공지사항 테스트2입니다.');
