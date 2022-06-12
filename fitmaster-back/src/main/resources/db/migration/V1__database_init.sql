SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `fitmaster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fitmaster` ;


CREATE TABLE IF NOT EXISTS `fitmaster`.`club` (
                                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                                             `club_address` VARCHAR(255) NULL DEFAULT NULL,
    `club_name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`club_when_open` (
                                                       `club_id` BIGINT NOT NULL,
                                                       `open_hours_id` BIGINT NOT NULL,
                                                       `when_open_key` INT NOT NULL,
                                                       PRIMARY KEY (`club_id`, `when_open_key`),
    INDEX `FKt84pwqwqroqqg64apm82j8tyk` (`open_hours_id` ASC) VISIBLE)
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`coach` (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    `year_of_birth` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`event` (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `beginning_time` TIME NULL DEFAULT NULL,
                                              `event_day` INT NULL DEFAULT NULL,
                                              `event_duration` BIGINT NULL DEFAULT NULL,
                                              `participants_limit` INT NOT NULL,
                                              `title` VARCHAR(255) NULL DEFAULT NULL,
    `club_id` BIGINT NULL DEFAULT NULL,
    `coach_id` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKge5xi5nf69096gtcjwjtup8wm` (`club_id` ASC) VISIBLE,
    INDEX `FKilw6yax2j03mim52qihyh83ff` (`coach_id` ASC) VISIBLE)
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `fitmaster`.`open_hours` (
                                                   `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `time_from` TIME NULL DEFAULT NULL,
                                                   `time_to` TIME NULL DEFAULT NULL,
                                                   PRIMARY KEY (`id`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`participant` (
                                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `email` VARCHAR(255) NULL DEFAULT NULL,
    `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`scheduled_event` (
                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `original_date` DATETIME NULL DEFAULT NULL,
                                                        `rescheduled_date` DATETIME NULL DEFAULT NULL,
                                                        `event_id` BIGINT NOT NULL,
                                                        PRIMARY KEY (`id`),
    INDEX `FKcg7yv3hirq32os8ky7ciu1b8u` (`event_id` ASC) VISIBLE)
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `fitmaster`.`scheduled_event_participants` (
                                                                     `scheduled_event_id` BIGINT NOT NULL,
                                                                     `participants_id` BIGINT NOT NULL,
                                                                     PRIMARY KEY (`scheduled_event_id`, `participants_id`),
    INDEX `FKysc0klo3xps604bnsg1brb4h` (`participants_id` ASC) VISIBLE)
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
