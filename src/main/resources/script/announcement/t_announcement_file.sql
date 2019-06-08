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

 Date: 06/08/2019 00:18:45 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_announcement_file`
-- ----------------------------
DROP TABLE IF EXISTS `t_announcement_file`;
CREATE TABLE `t_announcement_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `announcement_id` int(11) DEFAULT NULL COMMENT '文档ID',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件全称',
  `file_storage_path` varchar(255) DEFAULT NULL COMMENT '文件保存地址',
  `active` varchar(255) DEFAULT NULL COMMENT '是否有效 0:无效 1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
