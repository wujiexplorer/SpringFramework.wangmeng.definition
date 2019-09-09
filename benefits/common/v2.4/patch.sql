CREATE TABLE `card_key_storage` (
	`id` int(11) PRIMARY KEY AUTO_INCREMENT,
	`sku` int(11) NOT NULL COMMENT 'SKU编码',
    `store_time` datetime NOT NULL COMMENT '入库时间',
	`card_number` varchar(500) NOT NULL COMMENT '卡号',
	`password` varchar(500) NOT NULL COMMENT '密码',
	`dead_time` datetime NOT NULL COMMENT '截止时间',
	`goods_costprice` decimal(20,2) NOT NULL COMMENT '成本价',
	`status` int(1)  COMMENT '卡密状态（1库存中,2已发货,3已完成,4已失效）',
	`deliver_time` datetime  COMMENT '发货时间',
	`order_number` bigint(20)  COMMENT '订单号',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡密仓库';

alter table card_key_storage add buyer_user_id BIGINT(20) COMMENT '买家用户ID'

alter table card_key_storage add parent_order_number BIGINT(20) COMMENT '商家级订单号'

ALTER TABLE `card_key_storage` 
ADD INDEX `idx_buyer_user_id` USING BTREE (`buyer_user_id`) comment '买家用户ID索引'

ALTER TABLE `card_key_storage` 
ADD INDEX `idx_order_number` USING BTREE (`order_number`) comment '商品级订单索引'

UPDATE sku,product SET goods_storge = 0 where sku.spu_code = product.spu_code and product.goods_type=1