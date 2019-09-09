
CREATE TABLE `card_amount` (
  `amount_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `sort` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`amount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员卡面值列表';


INSERT INTO `card_amount` VALUES 
('1', '50', '0', '2019-06-19 17:30:39', null), 
('2', '100', '0', '2019-06-19 17:31:09', null), ('3', '200', '0', '2019-06-19 17:31:18', null), 
('4', '300', '0', '2019-06-19 17:31:25', null), ('5', '500', '0', '2019-06-19 17:31:35', null), 
('6', '2000', '0', '2019-06-19 17:31:40', null), ('7', '5000', '0', '2019-06-19 17:31:58', null), 
('8', '10000', '0', '2019-06-19 17:32:04', null);

CREATE TABLE `card_batch_recorder` (
  `batch_id` char(7) NOT NULL,
  `card_type` int(11) NOT NULL COMMENT '卡片类型（1A类卡，2B类卡，3C类卡）',
  `total_count` int(11) NOT NULL COMMENT '总数',
  `total_amount` int(11) NOT NULL COMMENT '面值总额',
  `status` int(11) DEFAULT NULL COMMENT '批次卡状态（0未制卡，1制卡中，2已入库）',
  `create_user` varchar(255) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡生产批次信息';


CREATE TABLE `card_storage` (
  `card_number` varchar(255) NOT NULL COMMENT '卡号',
  `batch_id` char(7) NOT NULL COMMENT '卡批次号',
  `card_type` int(11) NOT NULL COMMENT '卡片类型（1A类卡，2B类卡，3C类卡）',
  `amount` int(11) NOT NULL COMMENT '面值',
  `verify_number` char(12) NOT NULL COMMENT '校验码',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '卡状态（0初始化,1入库,2作废,3已激活,4已使用）',
  `store_user` varchar(50) DEFAULT NULL COMMENT '入库操作人',
  `store_time` datetime DEFAULT NULL COMMENT '入库时间',
  `check_user` varchar(50) DEFAULT NULL COMMENT '抽检人',
  `invalid_time` datetime DEFAULT NULL COMMENT '作废时间',
  `enterpr_id` int(11) DEFAULT NULL COMMENT '绑定的企业ID',
  `customer_id` int(11) DEFAULT NULL COMMENT '购买客户ID',
  `active_user` varchar(50) DEFAULT NULL COMMENT '激活人 (销售人)',
  `active_time` datetime DEFAULT NULL COMMENT '激活时间（销售时间）',
  `employee_id` int(11) DEFAULT NULL COMMENT '绑定的用户ID（已使用）',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`card_number`),
  UNIQUE KEY `unique_verifyNumber` (`verify_number`),
  KEY `index_batch` (`batch_id`) USING BTREE,
  KEY `index_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡仓库';


CREATE TABLE `employee_card_credit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL COMMENT '员工号',
  `card_credit` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '会员卡积分余额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_employeeId` (`employee_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工会员卡积分帐户';

CREATE TABLE `card_employee_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` char(16) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0未激活1已激活(激活 后才可使用)',
  `active_time` datetime DEFAULT NULL COMMENT '激活时间(通过看广告触发激活)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(11,2) NOT NULL COMMENT '卡总额',
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_card` (`card_number`) USING BTREE,
  KEY `index_employee` (`employee_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工会员卡使用记录';

ALTER TABLE `wx_user_open_id` 
DROP INDEX `idx_user_id__unique`, 
ADD INDEX `idx_user_id` USING BTREE (`user_id`, `is_delete`) comment '用户id索引', 
ADD INDEX `index_open_id` (`open_id`, `is_delete`) comment '微信openId索引';

ALTER TABLE `employee_user_info` 
CHANGE COLUMN `birthday` `birthday` varchar(20) NOT NULL DEFAULT '', 
CHANGE COLUMN `position` `position` varchar(50) NOT NULL DEFAULT '' COMMENT '员工职级';

ALTER TABLE `order` 
ADD COLUMN `card_amount` bigint(20) DEFAULT 0 COMMENT '会员卡积分抵扣' 
AFTER `promotion_amount`;


CREATE TABLE `card_sale_verify` (
  `verify_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员卡销售审核ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡片类型（1A类卡，2B类卡，3C类卡）',
  `total_count` int(11) NOT NULL COMMENT '总数',
  `total_amount` int(11) NOT NULL COMMENT '面值总额',
  `discount_on_sale` decimal(11,2) DEFAULT NULL COMMENT '销售折扣',
  `sum_payable` decimal(11,2) DEFAULT NULL COMMENT '应付金额',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '(付款方式）0:现款现结，1：周期结款',
  `returned_money` decimal(11,2) DEFAULT NULL COMMENT '已回款',
  `no_refund_money` decimal(11,2) DEFAULT NULL COMMENT '未回款',
  `create_user` varchar(128) DEFAULT NULL COMMENT '提交人',
  `verify_user` varchar(128) DEFAULT NULL COMMENT '审核人',
  `is_custom_card` tinyint(4) DEFAULT NULL COMMENT '（是否定制）0:未定制，1：已定制',
  `status` tinyint(4) DEFAULT NULL COMMENT '(当前状态）0：审核中，1：审核失败，2：等待回款，3：等待发卡，4：已发卡',
  `bind_enterpr_id` int(11) DEFAULT NULL COMMENT '绑定的企业ID',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '(是否删除）0：未删除，1：已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `verify_info` varchar(256) DEFAULT NULL COMMENT '审核信息',
  PRIMARY KEY (`verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员卡销售审核表';
CREATE TABLE `card_sale_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员卡面值ID',
  `amount` int(11) DEFAULT NULL COMMENT '面值',
  `num` int(11) DEFAULT NULL COMMENT '各面值数量',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `verify_id` bigint(20) DEFAULT NULL COMMENT '会员表审核ID',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '(是否删除)0：未删除，1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员卡销售面值细节表';
CREATE TABLE `card_return_money_recorder` (
  `return_money_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '回款记录ID',
  `return_money_value` decimal(11,2) DEFAULT NULL COMMENT '回款金额',
  `return_ticket` varchar(255) DEFAULT NULL COMMENT '回款凭证',
  `return_time` datetime DEFAULT NULL COMMENT '回款时间',
  `verify_id` bigint(20) DEFAULT NULL COMMENT '会员审核表ID',
  `operate_user` varchar(255) DEFAULT NULL COMMENT '操作人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`return_money_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员卡回款信息表';
CREATE TABLE `card_customer_info` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `customer_name` varchar(256) DEFAULT NULL COMMENT '客户姓名',
  `customer_company` varchar(256) DEFAULT NULL COMMENT '所属公司',
  `contact_phone` varchar(25) DEFAULT NULL COMMENT '联系电话',
  `invoice_title` varchar(128) DEFAULT NULL COMMENT '发票抬头',
  `tax_number` varchar(128) DEFAULT NULL COMMENT '税号',
  `company_address` varchar(128) DEFAULT NULL COMMENT '单位地址',
  `company_phone` varchar(25) DEFAULT NULL COMMENT '单位电话',
  `deposit_bank` varchar(256) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(128) DEFAULT NULL COMMENT '银行账号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `udpate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员卡客户信息表';

INSERT INTO `enterpr_user_info` VALUES ('test123456', '', '', '杭州西狗国际有限公司', 1, 0, '中国', '', '', '', NULL, '', NULL, NULL, NULL, 0, '', '', '', 0, NULL, '2019-06-24 15:59:16', '2019-06-24 16:03:09', NULL);


alter table card_storage add column verify_id bigint comment "会员卡审核ID";
