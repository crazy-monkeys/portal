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

 Date: 06/08/2019 00:18:38 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_announcement`
-- ----------------------------
DROP TABLE IF EXISTS `t_announcement`;
CREATE TABLE `t_announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `topmost` int(11) DEFAULT NULL COMMENT '是否置顶 0:否 1:是',
  `type` int(11) DEFAULT NULL COMMENT '公告分类',
  `status` int(11) DEFAULT NULL COMMENT '状态 0:未发布 1:已发布 -1:已撤销',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `release_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
