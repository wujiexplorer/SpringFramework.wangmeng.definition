create table voucher(
voucher_id bigint primary key auto_increment comment "优惠卷ID",
voucher_name varchar(128) comment "优惠卷名称",
total_release int(11) comment "发放总量",
use_case int(11) comment "0：全场商品，商品模板ID:部分商品",
use_threshold int(11) comment "使用门槛",
benefit_content int(11) comment "优惠内容",
validate_start_time datetime comment "优惠开始时间",
validate_end_time datetime comment "优惠结束时间",
limit_num int(11) comment "每人限领次数",
use_desc varchar(512) comment "使用说明",
is_stop tinyint comment "是否停止(0:未停止，1：停止)",
create_time datetime comment "创建时间",
update_time datetime comment "更新时间")comment "优惠卷"

create table voucher_product_template(
template_id bigint primary key auto_increment comment "商品模板ID",
template_name varchar(64) comment "商品模板名称",
product_range tinyint comment "1:类目，2：品牌，3：专题，4：商品",
product_items varchar(2560) comment "商品范围对应的具体IDs",
create_time datetime comment "创建时间",
update_time datetime comment "更新时间",
deleted tinyint comment "0:未删除，1：已删除")comment "商品模板"


alter table sku add column voucher_ids varchar(256) comment "优惠卷IDs";



alter table voucher_product_template add column updater varchar(256) comment "更新人";


alter table voucher add column voucher_type tinyint comment "0：满减券";


alter table voucher add column left_amount tinyint comment "剩余满减券数量";


alter table voucher add column received_amount tinyint comment "已领取满减卷数量";


alter table voucher add column used_amount tinyint comment "已使用满减券数量";

alter table voucher add column voucher_status tinyint comment "0:未开始，1：进行中，2：已结束";


alter table employee_credit_info add column used_vouchers varchar(256) comment "已使用优惠卷";

alter table employee_credit_info add column received_vouchers varchar(256) comment "已领取优惠卷";


alter table `order` add column voucher_benefit bigint comment "优惠卷优惠金额";


alter table `order` add column voucher_id bigint comment "优惠卷ID";


create table seckill(
seckill_id bigint primary key auto_increment comment "秒杀ID",
seckill_name varchar(8) comment "秒杀名称",
validate_start_time datetime comment "有效开始时间",
validate_end_time datetime comment "有效结束时间",
sku_id bigint comment "skuID",
seckill_price decimal(11,2) comment "秒杀价",
seckill_storage int(11) comment "秒杀库存",
limit_per_user int comment "0:不限购，1：限购数量",
order_cancel_time int comment "自动取消时间",
already_buy int comment "已购商品数量",
status tinyint comment "0:未开始，1：进行中，2：已结束",
create_time datetime comment "创建时间",
update_time datetime comment "更新时间")comment "秒杀表"


alter table seckill add column is_stop tinyint comment "0：未停止，1：已停止";

alter table sku add column seckill_id bigint comment "秒杀ID";


alter table voucher_product_template add column category_id1 varchar(256) comment "一级类目IDs";

alter table voucher_product_template add column category_id2 varchar(256) comment "二级类目IDs";

alter table voucher_product_template add unique(template_name);


alter table product add column voucher_ids varchar(256) comment "优惠卷IDs";


alter table sku add column seckill_ids varchar(256) comment "秒杀IDs";

alter table seckill add unique(seckill_name);

alter table voucher add unique(voucher_name);

alter table `order` add column seckill_id bigint comment "秒杀ID";


alter table seckill add left_storage tinyint comment "秒杀剩余库存";

alter table employee_credit_info add column received_seckillIds varchar(256) comment "已领的秒杀商品";


alter table employee_user_info add column vouchers_num varchar(256) comment "已领取的优惠卷";

alter table voucher modify use_threshold decimal(11,2) comment "使用门槛";
alter table voucher modify benefit_content decimal(11,2) comment "优惠内容";


alter table voucher_product_template drop index template_name;

create table product_voucher(
id bigint primary key auto_increment comment "主键ID",
spu_code bigint comment "spu编码",
voucher_id bigint comment "优惠卷ID",
create_time datetime comment "创建时间",
update_time datetime comment "更新时间",
deleted tinyint comment "是否删除：0，未删除；1，已删除")comment "Spu关联优惠卷表";


create table employee_credit_voucher(
id bigint primary key auto_increment comment "主键ID",
employee_id bigint comment "员工ID",
campaign_id tinyint comment "积分所属活动{0: 总积分; 其它值: 所属的具体活动}",
voucher_id bigint comment "优惠卷ID",
no_used_num tinyint comment "未使用的优惠卷",
received_num tinyint comment "已领取的优惠卷",
create_time datetime comment "创建时间",
update_time datetime comment "更新时间")comment "员工关联优惠卷表";
