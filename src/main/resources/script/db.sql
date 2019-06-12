DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL,
  `active` smallint(6) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `icon_class` varchar(255) COLLATE utf8_bin NOT NULL,
  `parent_id` int(11) NOT NULL,
  `resource_desc` varchar(255) COLLATE utf8_bin NOT NULL,
  `resource_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `resource_order` int(11) NOT NULL,
  `resource_type` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '1.菜单  2.Api',
  `resource_url` varchar(255) COLLATE utf8_bin NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` int(11) NOT NULL,
  `permission_prefix_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL,
  `active` smallint(6) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `role_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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