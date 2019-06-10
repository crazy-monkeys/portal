/*
 Navicat Premium Data Transfer

 Source Server         : portal_dev
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 10.45.80.149
 Source Database       : portal

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 06/10/2019 16:40:31 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_archive_user_relation`
-- ----------------------------
DROP TABLE IF EXISTS `t_archive_user_relation`;
CREATE TABLE `t_archive_user_relation` (
  `archive_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`archive_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
