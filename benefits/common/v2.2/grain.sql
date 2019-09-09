
 
 alter table employee_user_info add grain_id int COMMENT '颗粒号ID';


 alter table employee_credit_info add column grain_value double(11,2) comment "奖励的颗粒值";
 
 alter table employee_credit_info add column grain_id BIGINT comment "颗粒号ID";

create unique index idx_employeeId_grainId on employee_credit_info(employeeId,grain_id);


alter table employee_credit_info add column is_read TINYINT comment "(0:未读；1：已读）";

update employee_credit_info set is_read = 0;

alter table enterpr_user_info add column grain_id int comment "颗粒号ID";


			
alter table employee_credit_info add column article_id_list varchar(256) comment "文章ID列表";


create unique index idx_employeeId_articleId on grain_article_award(employee_id,article_id);


alter table grain_article_info add column publish_enterprise varchar(256) comment "发布公司";


alter table grain_info add column is_suspend int comment "0:暂停状态；1：开启状态";


update grain_info set is_suspend = 1;

alter table grain_article_info add column is_screen int comment "0:未屏蔽；1：已屏蔽";


alter table grain_article_info add column is_read int comment "0:未读；1：已读";
