CREATE TABLE `product_spec` (
  `spec_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格ID',
  `spec_name` varchar(50) NOT NULL COMMENT '规格名称',
  `supplier_id` int(11) NOT NULL DEFAULT '0' COMMENT '供应商ID,0表示基本的规格，-1表示运营添加的，其他表示供应商添加的',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`spec_id`),
  UNIQUE KEY `spec_name` (`supplier_id`,`spec_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品基本规格表';

insert into `product_spec` (`spec_name`) 
values ('颜色'),('尺码'),('尺寸'),('规格'),('款式'),('类型'), ('套餐'),
('容量'),('数量'),('口味'),('材质'),('型号'),('季节'),('主题'),('重量');

CREATE TABLE `product_spec_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spu_code` bigint(20) NOT NULL COMMENT 'spucode',
  `spec_id` int(11) NOT NULL COMMENT '规格ID',
  `spec_name` varchar(50) NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序值，默认为0，值越小排序越靠前',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `spu_spec_index` (`spu_code`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品规格信息表';

CREATE TABLE `sku_spec_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NOT NULL,
  `spec_id` int(11) NOT NULL,
  `spec_value` varchar(100) NOT NULL COMMENT '规格值',
  `spec_image` varchar(255) DEFAULT NULL COMMENT '图片信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_id` (`sku_id`,`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品SKU规格值表';

ALTER TABLE `product` ADD COLUMN `introductionEn` text COMMENT '英文详情';
ALTER TABLE `sku` 
 CHANGE COLUMN `sku_code` `sku_code` VARCHAR (50) COMMENT '第三方sku编码',
 ADD COLUMN `goods_name` VARCHAR (255) COMMENT '商品名称',
 ADD COLUMN `goods_images` text COMMENT '商品轮播图',
 CHANGE COLUMN `created_time` `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 ADD COLUMN `item_number` varchar(255) COMMENT '原厂货号',
 CHANGE COLUMN `goods_image` `goods_image` text CHARACTER SET utf8mb4 COMMENT '商品主图';
 
 CREATE TABLE `sku_storage_recorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NOT NULL COMMENT 'sku编码',
  `goods_storge` int(11) NOT NULL COMMENT '库存信息',
  `operator` varchar(255) NOT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `sku_id` (`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'sku库存更新记录';

ALTER TABLE `topic` ADD UNIQUE `index_name` (`name`) comment '专题名称唯一限制';

CREATE TABLE `product_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(255) NOT NULL COMMENT '键',
  `setting_value` varchar(255) NOT NULL COMMENT '值',
  `status` int(1) DEFAULT '1' COMMENT '状态',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `setting_key` (`setting_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品参数设置';

insert into `product_setting` (`setting_key`, `setting_value`, `update_user`) 
values ('goodsSaleRate', '0.18', 'SYSTEM');

delete from product_topic 
where product_topic.id not in(
	select * from (select MIN(id) from product_topic 
		group by product_topic.spu_code
	) temp
);
ALTER TABLE `product_topic` 
 	CHANGE COLUMN `sort_num` `sort_num` int(11) DEFAULT 0 COMMENT '商品排序',
	DROP INDEX `spuTopic`,
 	ADD UNIQUE `spuTopic` USING BTREE (`topic_id`, `spu_code`) COMMENT '',
 	ADD INDEX `index_spu` (`spu_code`) COMMENT '';
 
 update product_topic set sort_num=0 where sort_num is null;
 ALTER TABLE `user_collection` 
 	ADD COLUMN `spu_code` bigint AFTER `user_id`, 
 	CHANGE COLUMN `sku_id` `sku_id` bigint(20) UNSIGNED NULL COMMENT 'sku ID', 
 	DROP INDEX `idx_account_id_sku_id_unique`, 
 	ADD UNIQUE `idx_account_spu_unique` USING BTREE (`user_id`, `spu_code`) comment '用户id spu_code唯一索引';
 