ALTER TABLE enterpr_email  MODIFY `content` TEXT NOT NULL;

ALTER TABLE enterpr_email add title varchar(256)  NOT NULL;

CREATE TABLE `returned_money_recorder`(
	`id` BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`cash_number` VARCHAR(50) 	NOT NULL COMMENT '充值流水号',
	`pay_voucher` VARCHAR(100) 	NOT NULL COMMENT '打款凭证',
	`pay_time` datetime 	NOT NULL COMMENT '回款时间',
	`pay_amount` DECIMAL(20,2) 	NOT NULL COMMENT '回款金额',
	`create_user` VARCHAR(100) NOT NULL COMMENT '添加人',
	`create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回款记录表';

insert into returned_money_recorder(cash_number,pay_voucher,pay_time,pay_amount,create_user,create_time)  
(select cash_number,  pay_voucher,pay_time,pay_amount, check_user, check_time from 
credit_cheking_recorder where apply_type = 1 and check_status = 2);