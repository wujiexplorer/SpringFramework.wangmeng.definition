DROP table card_return_money_recorder;
DROP table card_sale_order_item;	
DROP table card_sale_verify;
DROP table card_storage;
DROP table card_batch_recorder;
DROP table card_customer_info;

CREATE TABLE `card_pay_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_order_id` int(11) NOT NULL COMMENT '会员卡支付订单ID',
  `pay_amount` decimal(15,2) NOT NULL COMMENT '回款金额',
  `pay_voucher` varchar(100) NOT NULL COMMENT '回款凭证',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `pay_time` date NOT NULL COMMENT '回款时间',
  `create_user` varchar(255) NOT NULL COMMENT '提交人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡订单客户支付记录';

CREATE TABLE `card_sale_order` (
  `sale_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `customer_id` bigint(11) NOT NULL COMMENT '客户ID-对应于企业ID',
  `bind_enterpr_id` bigint(11) NOT NULL COMMENT '绑定的企业ID',
  `is_custom_card` tinyint(4) NOT NULL COMMENT '是否是定制卡(1是，0否)',
  `pay_type` int(11) NOT NULL COMMENT '付款方式（0:现款现结，1：周期结款）',
  `discount_on_sale` decimal(5,2) NOT NULL COMMENT '销售折扣',
  `sum_payable` decimal(15,2) NOT NULL COMMENT '应收账款',
  `paid_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '已回款金额',
  `create_user` varchar(255) NOT NULL COMMENT '提交人',
  `create_time` datetime NOT NULL COMMENT '开单时间',
  `status` int(11) NOT NULL COMMENT '订单状态(0审核中，1审核失败，2等待发卡，3已发卡)',
  `verify_user` varchar(255) DEFAULT NULL COMMENT '审批人',
  `verify_time` datetime DEFAULT NULL COMMENT '审批时间',
  `verify_info` varchar(255) DEFAULT NULL COMMENT '审核信息',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除状态，0未删除，1删除',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sale_order_id`),
  KEY `inde_deleted` (`deleted`) USING BTREE,
  KEY `index_customer` (`customer_id`,`deleted`) USING BTREE,
  KEY `index_createTime` (`create_time`,`deleted`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡客户销售订单';

CREATE TABLE `card_sale_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `amount` int(11) NOT NULL COMMENT '面值',
  `num` int(11) NOT NULL COMMENT '数量',
  `sale_order_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_saleOrder` (`sale_order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡客户销售订单条目';

CREATE TABLE `card_storage` (
  `card_number` char(20) NOT NULL COMMENT '卡号',
  `batch_id` int(11) NOT NULL COMMENT '卡批次号',
  `amount` int(11) NOT NULL COMMENT '面值',
  `verify_number` char(12) NOT NULL COMMENT '校验码',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '卡状态（0初始化,1入库,2作废,3已激活,4已使用）',
  `enterpr_id` bigint(11) NOT NULL COMMENT '绑定的企业ID',
  `mobile` varchar(20) DEFAULT NULL COMMENT '该会员卡绑定的手机号',
  `employee_id` bigint(11) DEFAULT NULL COMMENT '绑定的用户ID（已使用）',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`card_number`),
  UNIQUE KEY `unique_verifyNumber` (`verify_number`),
  KEY `index_batch` (`batch_id`),
  KEY `index_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会员卡仓库';

CREATE TABLE `card_batch_recorder` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '批次ID',
  `customer_batch_id` int(11) NOT NULL COMMENT '企业批次(生成次数)',
  `customer_id` bigint(11) NOT NULL COMMENT '客户ID（对应于企业ID）',
  `type` int(11) NOT NULL COMMENT '卡类型',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '批次卡状态（0未制卡，1制卡中，2已入库(待发卡)，3已发卡）',
  `sale_order_id` int(11) NOT NULL COMMENT '会员卡销售订单ID',
  `make_user` varchar(50) DEFAULT NULL COMMENT '制卡人',
  `make_time` datetime DEFAULT NULL COMMENT '制卡时间',
  `store_user` varchar(50) DEFAULT NULL COMMENT '入库人',
  `store_time` datetime DEFAULT NULL COMMENT '入库时间',
  `deliver_user` varchar(255) DEFAULT NULL COMMENT '交付人',
  `deliver_time` datetime DEFAULT NULL COMMENT '交付时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `index_enterpr_batch` (`customer_id`,`customer_batch_id`),
  UNIQUE KEY `index_order` (`sale_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会员卡生产批次信息';

ALTER TABLE `card_employee_recorder` 
	CHANGE COLUMN `card_number` `card_number` char(20) NOT NULL COMMENT '会员卡卡号';

ALTER TABLE `enterpr_festival_packet` 
  ADD COLUMN `isWhitelist` BIT DEFAULT b'0'  COMMENT '是否开启白名单' AFTER `campaignThemeIdList`;

ALTER TABLE `fuliserver`.`enterpr_custom_price`
 ADD UNIQUE `idx_enter_goods` USING BTREE (`goodsId`, `enterprId`) comment '';

	