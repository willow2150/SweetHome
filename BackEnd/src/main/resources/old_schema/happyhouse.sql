use happyhouse;


CREATE TABLE IF NOT EXISTS `happyhouse`.`user` (
  `user_id` VARCHAR(46) NOT NULL,
  `user_pwd` VARCHAR(100) NULL DEFAULT NULL,
  `user_name` VARCHAR(46) NULL DEFAULT NULL,
  `user_address` VARCHAR(60) NULL DEFAULT NULL,
  `user_email` VARCHAR(20) NULL DEFAULT NULL,
  `type` VARCHAR(20) NULL DEFAULT 'general',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `happyhouse`.`favorite` (
  `memberfav_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` VARCHAR(46) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,

    FOREIGN KEY (`user_id`)
    REFERENCES `happyhouse`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;