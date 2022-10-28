CREATE DATABASE IF NOT EXISTS mianyang;
USE mianyang;

DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
                               `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                               `type` VARCHAR ( 30 ) NOT NULL,
                               `location` VARCHAR ( 50 ) DEFAULT NULL,
                               `ip` VARCHAR ( 45 ) NOT NULL,
                               `port` VARCHAR ( 45 ) NOT NULL,
                               `scan_time` INT ( 11 ) DEFAULT NULL,
                               `transfer_length` INT ( 11 ) NOT NULL
);

-- ----------------------------
-- Records of deviceinfo
-- ----------------------------
INSERT INTO `device_info`(id, type, location, ip, port, scan_time, transfer_length)
VALUES
    ( 1, 'RSS131', '运输部亭子', '127.0.0.1', '4003', 30, 5),
    ( 2, 'JL900', '运输部亭子', '127.0.0.1', '4001', 5, 3),
    ( 3, 'H3R7000', '运输部亭子', '127.0.0.1', '4002', 20, 7);

DROP TABLE IF EXISTS `RSS131`;
CREATE TABLE `RSS131` (
                          `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          `location` VARCHAR ( 50 ) NOT NULL,
                          `dev_id` BIGINT NOT NULL,
                          `val1` DOUBLE DEFAULT NULL,
                          `val2` DOUBLE DEFAULT NULL,
                          `val3` DOUBLE DEFAULT NULL,
                          `gmt_create` datetime DEFAULT NULL,
                          `gmt_modified` datetime DEFAULT NULL

);

DROP TABLE IF EXISTS `JL900`;
CREATE TABLE `JL900` (
                         `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `location` VARCHAR ( 50 ) NOT NULL,
                         `dev_id` BIGINT NOT NULL,
                         `val1` DOUBLE DEFAULT NULL,
                         `val2` DOUBLE DEFAULT NULL,
                         `gmt_create` datetime DEFAULT NULL,
                         `gmt_modified` datetime DEFAULT NULL
);

DROP TABLE IF EXISTS `H3R7000`;
CREATE TABLE `H3R7000` (
                           `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `location` VARCHAR ( 50 ) NOT NULL,
                           `dev_id` BIGINT NOT NULL,
                           `val1` DOUBLE DEFAULT NULL,
                           `val2` DOUBLE DEFAULT NULL,
                           `val3` DOUBLE DEFAULT NULL,
                           `var4` DOUBLE DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL
);

