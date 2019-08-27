/*
 Navicat Premium Data Transfer

 Source Server         : portal-dev
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.100.106.186
 Source Database       : portal

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : utf-8

 Date: 08/25/2019 21:17:31 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_deliver_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_deliver_detail`;
CREATE TABLE `t_deliver_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `third_id` int(11) DEFAULT NULL,
  `customer_external_number` varchar(255) DEFAULT NULL,
  `customer_full_name` varchar(255) DEFAULT NULL,
  `sales` varchar(255) DEFAULT NULL,
  `category_one` varchar(255) DEFAULT NULL,
  `category_tow` varchar(255) DEFAULT NULL,
  `category_three` varchar(255) DEFAULT NULL,
  `product_model` varchar(255) DEFAULT NULL,
  `delivery_date` varchar(255) DEFAULT NULL,
  `deliver_number` int(11) DEFAULT NULL,
  `sale_price` decimal(18,6) DEFAULT NULL,
  `po_price` decimal(18,6) DEFAULT NULL,
  `margin` decimal(18,6) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `customer_order_number` varchar(255) DEFAULT NULL,
  `delivery_type` varchar(255) DEFAULT NULL,
  `order_month` varchar(255) DEFAULT NULL,
  `delivery_company` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `record_id` int(11) DEFAULT NULL,
  `error_msg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_deliver_receive_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_deliver_receive_record`;
CREATE TABLE `t_deliver_receive_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dealer_id` int(11) DEFAULT NULL COMMENT '代理商ID',
  `dealer_name` varchar(255) DEFAULT NULL COMMENT '代理商名称',
  `type` int(11) DEFAULT NULL COMMENT '数据类型 1:出货 2:收货',
  `upload_time` datetime DEFAULT NULL COMMENT '上传日期',
  `status` int(11) DEFAULT NULL COMMENT '状态 -1：待确认 0：初始化 1：已确认 2：已提交 3：已驳回',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `approval_user_id` int(11) DEFAULT NULL COMMENT '审核用户ID',
  `approval_time` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_receive_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_receive_detail`;
CREATE TABLE `t_receive_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `third_id` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `product_model` varchar(255) DEFAULT NULL COMMENT '产品型号',
  `inventory_category` varchar(255) DEFAULT NULL COMMENT '库存类别',
  `inventory_unit_price` decimal(10,2) DEFAULT NULL COMMENT '库存单价',
  `warehouse` varchar(255) DEFAULT NULL COMMENT '仓储地',
  `delivery_time` varchar(255) DEFAULT NULL COMMENT '提货时间',
  `invoice_number` varchar(255) DEFAULT NULL COMMENT '提货发票号',
  `delivery_num` int(11) DEFAULT NULL COMMENT '提货数量',
  `delivery_company` varchar(255) DEFAULT NULL COMMENT '发货公司',
  `purchase_number` varchar(255) DEFAULT NULL COMMENT '采购单号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `error_msg` varchar(255) DEFAULT NULL,
  `record_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
