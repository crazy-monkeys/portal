DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` smallint(6) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `icon_class` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `resource_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `resource_order` int(11) DEFAULT NULL,
  `resource_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '1.菜单  2.Api',
  `resource_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `permission_prefix_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('1', '1', '2019-06-09 23:08:07', '1', '#', '0', '', '系统管理', '1', '1', '#', '2019-06-09 23:10:00', '1', '/');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('2', '1', '2019-06-11 00:34:56', '1', '#', '1', '用户管理', '用户管理', '1', '1', '/view/system/users.html', '2019-06-11 00:35:29', '1', '/user/**');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('3', '1', '2019-06-13 20:16:10', '1', '#', '1', '权限管理', '权限管理', '1', '1', '/view/system/authority.html', '2019-06-13 20:17:08', '1', '/permission/**');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('4', '1', '2019-06-13 20:18:18', '1', '#', '1', '公告管理', '公告管理', '6', '1', '/view/system/announcement.html', '2019-06-15 13:25:16', '1', '/announcement/**');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('13', '1', '2019-06-15 13:25:54', '1', '#', '12', '#', '客户查询', '6', '1', '/view/customer/customerQuery.html', '2019-06-15 13:25:54', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('14', '1', '2019-06-15 13:26:07', '1', '#', '12', '#', '报备查询', '6', '1', '/view/customer/reportQuery.html', '2019-06-15 13:26:07', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('12', '1', '2019-06-15 13:25:40', '1', '#', '0', '#', '客户管理', '5', '1', '#', '2019-06-15 13:25:40', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('15', '1', '2019-06-16 12:56:50', '1', 'icon1', '1', '', '文档管理', '99', '1', '/view/system/document.html', '2019-06-16 12:56:50', '1', '/archive/**');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('16', '1', '2019-06-22 13:52:13', '1', '#', '0', '#', '授信额度', '9', '1', '#', '2019-06-22 13:52:13', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('17', '1', '2019-06-22 13:52:44', '1', '#', '13', '#', '客户明细', '1', '1', '/view/customer/customerDetail.html', '2019-06-22 13:52:44', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('20', '1', '2019-06-22 14:39:09', '1', '#', '12', '#', '拜访记录上传', '99', '1', '/view/customer/visitRecordUpload.html', '2019-06-22 14:39:09', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('21', '1', '2019-06-22 14:47:17', '1', '#', '12', '#', 'Open客户', '99', '1', '/view/customer/openCustomer.html', '2019-06-22 14:47:17', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('22', '1', '2019-06-22 14:52:23', '1', '#', '12', '#', '维护客户信息', '99', '1', '/view/customer/maintainCustomer.html', '2019-06-22 14:52:23', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('23', '1', '2019-06-23 03:25:40', '1', '#', '16', '#', '授信额度申请（代理商）', '99', '1', '/view/credit/creditApply.html', '2019-06-23 03:25:40', '1', '#');
INSERT INTO `t_resource` (`id`, `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) VALUES ('24', '1', '2019-06-23 04:03:35', '1', '#', '16', '#', '授信额度申请（CS）', '99', '1', '/view/credit/creditApplyCs.html', '2019-06-23 04:03:35', '1', '#');

insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 13:38:39', '1', '#', '0', '#', '价格管理', '9', '1', '#', '2019-06-22 13:38:39', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 13:39:44', '1', '#', '16', '#', '价格查询(内部)', '5', '1', '/view/price/insidePriceQuery.html', '2019-06-22 13:39:44', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 13:40:13', '1', '#', '16', '#', '价格查询', '5', '1', '/view/price/externalPriceQuery.html', '2019-06-22 13:40:13', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:50:57', '1', '#', '0', '#', '订单管理', '15', '1', '#', '2019-06-22 14:50:57', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:51:21', '1', '#', '22', '#', '订单填报', '100', '1', '/view/order/orderReport.html', '2019-06-22 14:52:29', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:51:46', '1', '#', '22', '#', '订单查询', '99', '1', '/view/order/orderQuery.html', '2019-06-22 14:51:46', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:34:46', '1', '#', '0', '#', '样品管理', '12', '1', '#', '2019-06-22 14:34:46', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:35:28', '1', '#', '19', '#', '样品查询', '99', '1', '/view/sample/sampleQuery.html', '2019-06-22 14:35:28', '1', '#');
insert into `portal`.`t_resource` ( `active`, `create_time`, `create_user_id`, `icon_class`, `parent_id`, `resource_desc`, `resource_name`, `resource_order`, `resource_type`, `resource_url`, `update_time`, `update_user_id`, `permission_prefix_url`) values ( '1', '2019-06-22 14:35:58', '1', '#', '19', '#', '样品申请', '99', '1', '/view/sample/sampleApply.html', '2019-06-22 14:35:58', '1', '#');



-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` smallint(6) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `role_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `t_role` (`id`, `active`, `create_time`, `create_user_id`, `role_desc`, `role_name`, `update_time`, `update_user_id`) VALUES ('1', '1', '2019-04-20 05:11:45', '1', '', '系统管理员', '2019-06-16 12:56:53', '1');

-- ----------------------------
--  Table structure for `t_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('1', '1', '1', '2019-06-13 20:23:31', '1', '2019-06-15 16:44:20', NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('2', '1', '3', '2019-06-15 17:26:32', NULL, NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('20', '1', '12', '2019-06-15 14:21:26', '1', NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('21', '1', '13', '2019-06-15 14:21:26', '1', NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('23', '1', '14', '2019-06-15 14:25:33', '1', NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('24', '1', '2', '2019-06-15 14:25:37', '1', NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('29', '1', '4', '2019-06-16 12:55:49', '1', NULL, NULL);
INSERT INTO `t_role_resource` (`id`, `role_id`, `resource_id`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES ('30', '1', '15', '2019-06-16 12:56:53', '1', NULL, NULL);

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` smallint(6) NOT NULL,
  `country` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `login_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `login_pwd` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pwd_invalid_time` datetime NOT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reg_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_company_file`;
CREATE TABLE `t_company_file` (
  `file_id` int(11) NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `sub_type` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `oss_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `oss_path` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
--  Table structure for `t_company_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_info`;
CREATE TABLE `t_company_info` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `parent_company_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `business_license_address` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `business_email_address` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `fixed_line_telephone` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `country_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `state_number` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `extension` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `corporate_legal_person` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `postal_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `company_established_time` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `business_term` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `registered_funds` decimal(10,0) DEFAULT NULL,
  `currency` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `company_website` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `business_registration_number` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `tax_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `company_introduction` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_contact_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_contact_information`;
CREATE TABLE `t_contact_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `is_default` smallint(6) DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `job_title` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `office_phone` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `postal_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `country_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `state_number` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `extension` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `work_email` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



-- ----------------------------
--  Table structure for `t_customer_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_address`;
CREATE TABLE `t_customer_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `is_default` smallint(6) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_customer_contacts`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_contacts`;
CREATE TABLE `t_customer_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `emial` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `event_mobile` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_usr_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_customer_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `product_line` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `tax_code` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `parent_customer` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_customer_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_project`;
CREATE TABLE `t_customer_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL,
  `project_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `material_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `competitor` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `competitor_material_name` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `expected_volume` bigint(20) DEFAULT NULL,
  `estimated_production_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_customer_region`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_region`;
CREATE TABLE `t_customer_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  `regist_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_solution_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_solution_info`;
CREATE TABLE `t_solution_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `one_industry` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `two_industry` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `recommended_user` varchar(255) CHARACTER SET utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_user_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_company`;
CREATE TABLE `t_user_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `active` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;