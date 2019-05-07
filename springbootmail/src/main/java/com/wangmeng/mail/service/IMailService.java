package com.wangmeng.mail.service;

import com.wangmeng.mail.common.model.Email;
import com.wangmeng.mail.common.model.Result;

public interface IMailService {
	 /**
	  * 纯文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  */
	 public void send(Email mail) throws Exception;
	 /**
	  * 富文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 public void sendHtml(Email mail) throws Exception;
	 /**
	  * 模版发送 freemarker
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 public void sendFreemarker(Email mail) throws Exception;
	 /**
	  * 模版发送 thymeleaf(弃用、需要配合模板)
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 public void sendThymeleaf(Email mail) throws Exception;
	 /**
	  * 队列
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月4日
	  * 更新日志
	  * 2017年8月4日  科帮网 首次创建
	  *
	  */
	 public void sendQueue(Email mail) throws Exception;
	 /**
	  * Redis 队列
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月13日
	  * 更新日志
	  * 2017年8月13日  科帮网 首次创建
	  *
	  */
	 public void sendRedisQueue(Email mail) throws Exception;
	 
	 
	 public Result listMail(Email mail);
}
