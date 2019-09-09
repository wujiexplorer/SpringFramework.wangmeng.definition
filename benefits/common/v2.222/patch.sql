CREATE TABLE `enterpr_menu_info`(
	`menu_id` INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`custom_id` BIGINT(20) UNSIGNED	NOT NULL,
	`menu_name` VARCHAR(100) 	NOT NULL COMMENT '菜单栏名称',
	`status` tinyint(4) 	NOT NULL COMMENT '菜单是否显示(0不显示 1显示)',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  	`update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业菜单栏定制';