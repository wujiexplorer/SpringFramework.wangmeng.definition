package com.wangmeng.mail.common.task;

import org.springframework.stereotype.Component;

/**
 * 统计失败邮件定时重新发送
 * 创建者 科帮网
 * 创建时间	2017年7月21日
 *
 */
@Component("sendMail")
public class SendMail {
	public void sendMail() {
		System.out.println("send wangmeng ok!");
	}
}
