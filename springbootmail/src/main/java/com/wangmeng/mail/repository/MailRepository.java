package com.wangmeng.mail.repository;

import com.wangmeng.mail.entity.OaEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 邮件管理
 * 创建者 小柒2012
 * 创建时间	2017年9月9日
 */
public interface MailRepository extends JpaRepository<OaEmail, Integer> {
	
}
