alter table agent_level add column brand_building_fee decimal(11,2) comment "品牌建设费";
alter table agent_level add column market_fee decimal(11,2) comment "营销费用";
alter table agent_level add column team_building_fee decimal(11,2) comment "团队建设费";
alter table agent_level add column introducer_award decimal(11,2) comment "介绍人奖励";
alter table agent_level add column is_proxy_operative tinyint comment "是否代运营(0：关闭；1：开启）";

create table agent_rebate_deploy(
id bigint primary key auto_increment comment "利润返点配置ID",
management_fee_ratio decimal(11,2) comment "综合管理费比例",
matter_tax decimal(11,2) comment "实物税金",
virtual_tax decimal(11,2) comment "虚拟/服务税金")


insert into agent_rebate_deploy(management_fee_ratio,matter_tax,virtual_tax) values(3.00,13.00,6.72);

alter table `order` add column num int comment "支付级订单生成次数";


alter table agent_income_recorder drop index index_voucherType;

ALTER TABLE `product` ADD INDEX `idx_updated_time` USING BTREE (`updated_time`);
