CREATE TABLE `agent_account_checking_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理商ID',
  `agent_type` int(1) DEFAULT '1' COMMENT '代理类型(1公司、2个人)',
  `agent_name` varchar(100) NOT NULL COMMENT '个人/企业名称',
  `agent_identity_id` varchar(100) NOT NULL COMMENT '企业营业执照编号/个人身份证号',
  `agent_register_address` varchar(255) NOT NULL COMMENT '企业注册地址/个人身份证地址',
  `certify_image_urls` varchar(255) NOT NULL COMMENT '证件地址',
  `contact_user` varchar(20) NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) NOT NULL COMMENT '联系电话',
  `contact_email` varchar(50) NOT NULL COMMENT '联系人邮箱',
  `account_status` int(1) NOT NULL DEFAULT '1' COMMENT '账号状态(1正常、2冻结)',
  `add_user` varchar(100) NOT NULL COMMENT '添加人',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `check_status` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态(1未审核、2审核通过、3审核不通过)',
  `check_user` varchar(100) DEFAULT NULL COMMENT '审核人',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `login_name` varchar(100) DEFAULT NULL COMMENT '审核成功后添加账号',
  `check_remark` varchar(255) DEFAULT NULL COMMENT '审核备注信息',
  `contract_number` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `agent_level` int(11) DEFAULT NULL COMMENT '代理等级',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级代理ID',
  `bind_time` datetime DEFAULT NULL COMMENT '代理商绑定时间',
  `registered_type` varchar(100) NOT NULL COMMENT '注册方式',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除(0否，1是)',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `agent_name` (`agent_name`),
  KEY `agent_identity_id` (`agent_identity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='代理商信息表';

CREATE TABLE `agent_account_info` (
  `id` int(11) NOT NULL COMMENT '代理商ID',
  `login_name` varchar(100) NOT NULL COMMENT '登陆账号',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `secret` varchar(50) NOT NULL COMMENT '秘钥',
  `agent_type` int(1) NOT NULL DEFAULT '1' COMMENT '代理类型(1公司、2个人)',
  `agent_name` varchar(100) NOT NULL COMMENT '个人/企业名称',
  `agent_identity_id` varchar(100) NOT NULL COMMENT '企业营业执照编号/个人身份证号',
  `agent_register_address` varchar(255) NOT NULL COMMENT '企业注册地址/个人身份证地址',
  `certify_image_urls` varchar(255) NOT NULL COMMENT '证件地址',
  `contact_user` varchar(20) NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) NOT NULL COMMENT '联系电话',
  `contact_email` varchar(50) NOT NULL COMMENT '联系人邮箱',
  `account_status` int(1) NOT NULL DEFAULT '1' COMMENT '账号状态(1正常、2冻结)',
  `contract_number` varchar(100) NOT NULL COMMENT '合同编号',
  `agent_level` int(11) NOT NULL COMMENT '代理等级',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级代理ID',
  `bind_time` datetime DEFAULT NULL COMMENT '代理商绑定时间',
  `registered_type` varchar(100) NOT NULL COMMENT '注册方式',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_loginName` (`login_name`) USING BTREE,
  UNIQUE KEY `agent_identtity_id` (`agent_identity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商信息表';

CREATE TABLE `agent_bind_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `parent_agent_id` int(11) NOT NULL COMMENT '父级代理商ID',
  `parent_agent_name` varchar(255) NOT NULL,
  `status` int(1) DEFAULT NULL COMMENT '绑定状态（1绑定0解绑）',
  `bind_remark` varchar(255) NOT NULL COMMENT '绑定备注信息',
  `bind_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '解绑时间',
  `unbind_remark` varchar(255) DEFAULT NULL COMMENT '解绑备注信息',
  `unbind_time` datetime DEFAULT NULL COMMENT '解绑时间',
  PRIMARY KEY (`id`),
  KEY `agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='代理商绑定记录';

CREATE TABLE `agent_black_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rebate_type` int(11) NOT NULL,
  `category_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '分类列表',
  `brand_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌列表',
  `good_Ids` varchar(255) NOT NULL DEFAULT '' COMMENT '商品列表',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_rebate_type` (`rebate_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `agent_income_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `voucher_number` varchar(100) NOT NULL COMMENT '凭证号码',
  `pay_order_number` varchar(100) DEFAULT NULL COMMENT '支付级订单号(用于统计下单次数)',
  `cash_amount` decimal(32,2) NOT NULL COMMENT '金额',
  `profit_proportion` decimal(4,2) DEFAULT NULL,
  `type` int(1) NOT NULL COMMENT '流水类型(1销售额返佣/2异常订单扣除/3充值返点/4提现，5利润额返佣)',
  `enterpr_id` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_voucherType` (`voucher_number`,`type`) USING BTREE,
  KEY `index_agent` (`agent_id`,`create_time`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='代理商现金流水表';

CREATE TABLE `agent_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级ID',
  `name` varchar(100) NOT NULL COMMENT '等级名称',
  `profit_type` int(1) NOT NULL COMMENT '收益方式（1佣金、2返点）',
  `rebate_type` int(11) NOT NULL COMMENT '返点模式（1充值返点、2销售额返点、3利润额返点）',
  `profit_proportion` decimal(4,2) NOT NULL COMMENT '收益比例',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='代理商级别表';

CREATE TABLE `agent_wallet` (
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `cash_total` decimal(32,2) NOT NULL COMMENT '总余额',
  `history_cash_total` decimal(32,2) NOT NULL COMMENT '截止到上月月底收入总额，即本月的可提现金额',
  `check_time` datetime NOT NULL COMMENT '金额校验时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '1正常，2异常冻结',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商钱包';

CREATE TABLE `agent_wallet_checking_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `cash_total` decimal(32,2) NOT NULL COMMENT '总余额',
  `history_cash_total` decimal(32,2) NOT NULL COMMENT '截止到上月月底收入总额，即本月的可提现金额',
  `check_time` datetime NOT NULL COMMENT '金额校验时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '1正常，2异常冻结',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='代理商钱包';

CREATE TABLE `agent_withdraw_recorder` (
  `cash_number` varchar(30) NOT NULL COMMENT '流水号',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `cash_amount` decimal(32,2) NOT NULL COMMENT '提现金额',
  `check_status` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态(1未审核、2审核通过、3审核不通过)',
  `check_user` varchar(50) DEFAULT NULL COMMENT '审核人',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `pay_voucher` varchar(50) DEFAULT NULL COMMENT '打款凭证',
  `check_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `bank_name` varchar(50) NOT NULL COMMENT '开户行',
  `bank_card_number` varchar(50) NOT NULL COMMENT '银行卡号',
  `apply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`cash_number`),
  KEY `agent_id` (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现记录/申请表';

CREATE TABLE `credit_cheking_recorder` (
  `cash_number` varchar(50) NOT NULL COMMENT '充值/退款流水号',
  `enterpr_id` bigint(20) NOT NULL COMMENT '企业ID',
  `credit_type` int(1) NOT NULL COMMENT '积分类型(1 普通积分、2节日积分、3认可激励)',
  `cash_amount` decimal(20,2) NOT NULL COMMENT '充值/退款金额',
  `apply_remark` varchar(255) DEFAULT NULL COMMENT '充值/退款申请备注信息',
  `apply_type` int(1) NOT NULL COMMENT '申请类型(1充值、2退款)',
  `apply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `apply_user` varchar(100) DEFAULT NULL COMMENT '添加人',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `check_user` varchar(100) DEFAULT NULL COMMENT '审核人',
  `check_status` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态（1未审核2审核通过3审核不通过）',
  `check_remark` varchar(255) DEFAULT NULL COMMENT '审核备注信息',
  `pay_voucher` varchar(100) DEFAULT NULL COMMENT '打款凭证',
  `pay_time` datetime DEFAULT NULL COMMENT '收/付款时间',
  `pay_amount` decimal(20,2) DEFAULT NULL COMMENT '收/付款金额',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`cash_number`),
  KEY `index_checkStatus` (`check_status`,`apply_time`) USING BTREE,
  KEY `inde_apply_time` (`apply_time`) USING BTREE,
  KEY `index_ enterpr` (`enterpr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `enterpr_bind_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterpr_id` bigint(20) NOT NULL,
  `agent_id` int(11) NOT NULL,
  `agent_name` varchar(100) NOT NULL COMMENT '代理商名称',
  `status` int(1) NOT NULL COMMENT '绑定状态，1绑定0解绑',
  `bind_remark` varchar(255) DEFAULT NULL,
  `bind_user` varchar(100) NOT NULL COMMENT '绑定人员',
  `bind_time` datetime NOT NULL COMMENT '创建时间',
  `unbind_remark` varchar(255) DEFAULT NULL,
  `unbind_user` varchar(100) DEFAULT NULL COMMENT '解绑人',
  `unbind_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_agent` (`agent_id`) USING BTREE,
  KEY `index_enterpr` (`enterpr_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `enterpr_checking_recorder` (
  `enterpr_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '企业客户id,自增主键ID',
  `enterpr_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业名称',
  `sub_enterpr_name` varchar(255) DEFAULT NULL COMMENT '子公司名称',
  `enterpr_identity_id` varchar(100) NOT NULL COMMENT '企业营业执照编号',
  `enterpr_register_address` varchar(255) NOT NULL COMMENT '企业注册地址',
  `certify_image_url` varchar(255) NOT NULL COMMENT '营业执照照片',
  `country` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '中国' COMMENT '企业所在国家',
  `province` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在省',
  `city` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在城市',
  `county` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在地区县',
  `street` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业所在地街道',
  `address` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在地详细地址【不含省市区街道部分】',
  `leave_credit` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '员工离职积分处理方式{0:不回收；1：回收}',
  `contact_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人',
  `mobile` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人手机号',
  `email` varchar(80) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人Email',
  `add_user` varchar(100) NOT NULL COMMENT '添加人',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `agent_id` int(11) NOT NULL DEFAULT '0' COMMENT '代理商ID',
  `check_user` varchar(100) DEFAULT NULL COMMENT '审批人',
  `check_time` datetime DEFAULT NULL COMMENT '审批时间',
  `check_status` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态（1未审核、2审核通过、3审核未通过）',
  `contract_number` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `login_name` varchar(20) DEFAULT NULL COMMENT '企业登录用户名',
  `check_remark` varchar(255) DEFAULT NULL COMMENT '审批备注',
  `registered_type` varchar(100) NOT NULL COMMENT '注册方式',
  `deleted` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 {1:已删除; 0:未删除}',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`enterpr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COMMENT='企业客户审批记录';

CREATE TABLE `enterpr_user_info_new` (
  `enterpr_id` bigint(20) unsigned NOT NULL COMMENT '企业客户id',
  `login_name` varchar(20) NOT NULL COMMENT '企业登录用户名',
  `password` varchar(40) CHARACTER SET utf8 NOT NULL COMMENT '企业登录密码',
  `secret` varchar(40) NOT NULL COMMENT '企业登录secret码',
  `enterpr_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业名称',
  `account_status` int(1) unsigned NOT NULL DEFAULT '1' COMMENT '企业状态（1正常、2冻结）',
  `employee_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '企业员工总数',
  `country` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '中国' COMMENT '企业所在国家',
  `province` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在省',
  `city` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在城市',
  `county` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在地区县',
  `street` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业所在地街道',
  `address` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '企业所在地详细地址【不含省市区街道部分】',
  `enterpr_identity_id` varchar(100) DEFAULT NULL,
  `enterpr_register_address` varchar(255) DEFAULT NULL,
  `certify_image_url` varchar(255) DEFAULT NULL,
  `leave_credit` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '员工离职积分处理方式{0:不回收；1：回收}',
  `contact_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人',
  `mobile` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人手机号',
  `email` varchar(80) CHARACTER SET utf8 NOT NULL COMMENT '企业联系人Email',
  `agent_id` int(11) NOT NULL DEFAULT '0' COMMENT '代理商ID',
  `bind_time` datetime DEFAULT NULL COMMENT '绑定时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`enterpr_id`),
  UNIQUE KEY `idx_enterprName` (`enterpr_name`),
  UNIQUE KEY `idx_loginName` (`login_name`),
  KEY `idx_created_isDeleted` (`create_time`),
  KEY `idx_updated_isDeleted` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业客户主表';

INSERT INTO enterpr_user_info_new 
	(enterpr_id, login_name, `password`, secret, enterpr_name, employee_num, country, province, city,
    county, street, address, leave_credit, contact_user, mobile, email, create_time, update_time)
(
SELECT enterprId, loginName, `password`, secret, enterprName, employeeNum, country, province, city, county,
	street, address, leaveCredit, contactUser, mobile, email, FROM_UNIXTIME(created), FROM_UNIXTIME(updated)
FROM enterpr_user_info
);

