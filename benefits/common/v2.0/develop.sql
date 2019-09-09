alter table enterpr_festival_packet add campaignThemeIdList VARCHAR(2048);

UPDATE enterpr_festival_packet  SET campaignThemeIdList = campaignThemeId;



福粒2.2版本 5.16-6.15


//生日自动发放积分提醒{1：开启；0：关闭},默认关闭
alter table employee_user_info add birthdayRemind tinyint(1) DEFAULT 0 NOT NULL;

//生日发放积分
alter table employee_credit_info add birthdayCredit decimal(20,2) default 0.00 NOT NULL ;

//企业定制邮件表格
create table enterpr_email(
				id int primary key auto_increment,
				enterprId bigint(20) unsigned,
				content varchar(4000),
				is_Birthday_Email_Remind tinyint(1) DEFAULT 0 NOT NULL,
				create_time datetime ,
				update_time datetime
);

//员工生日积分发放记录表
create table employee_birthday_credit_record(
				id int primary key auto_increment,
				employeeId bigint(20) unsigned not null,
				credit decimal(20,2)  NOT NULL,
				birthday_credit_status tinyint(1) not null,
				credit_type tinyint(4) not null,
				create_time date not null,
				update_time date not null
);