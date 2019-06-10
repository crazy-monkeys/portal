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

 Date: 06/10/2019 16:40:24 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_archive`
-- ----------------------------
DROP TABLE IF EXISTS `t_archive`;
CREATE TABLE `t_archive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `third_file_name` varchar(255) DEFAULT NULL COMMENT '原始文件名',
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `status` int(11) DEFAULT NULL COMMENT '状态 0:未发布 1:已发布 -1:已撤销',
  `file_storage_path` varchar(255) DEFAULT NULL COMMENT '文件存储地址',
  `active` int(11) DEFAULT NULL COMMENT '是否有效 0:无效 1:有效',
  `file_source` varchar(255) DEFAULT NULL COMMENT '文件来源 third:第三方，portal:平台内部',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `release_user_id` int(11) DEFAULT NULL COMMENT '发布者',
  `release_file_name` varchar(255) DEFAULT NULL COMMENT '发布文件名',
  `release_introduction` varchar(1000) DEFAULT NULL COMMENT '简介',
  `operational_mode` int(11) DEFAULT NULL COMMENT '操作方式 0:可见 1:可下载',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
