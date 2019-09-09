CREATE TABLE `client_message` (
  `message_id` char(32) NOT NULL,
  `message_type` int(1) NOT NULL COMMENT '消息类型，1下单成功，2下单失败',
  `message_info` text NOT NULL COMMENT '消息内容',
  `org_code` varchar(50) NOT NULL COMMENT '公司编码',
  `is_success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否发送成功',
  `send_count` int(11) NOT NULL DEFAULT '1' COMMENT '发送次数',
  `client_user_id` varchar(100) DEFAULT NULL COMMENT '第三方用户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端信息发送';

ALTER TABLE `enterpr_custom_goods` 
ADD COLUMN `lowestPrice` decimal(10, 2) NULL COMMENT '商品最低价' AFTER `categoryIdsList`;
