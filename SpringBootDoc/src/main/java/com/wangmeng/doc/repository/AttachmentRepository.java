package com.wangmeng.doc.repository;

import com.wangmeng.doc.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 附件管理
 * 创建者 小柒2012
 * 创建时间	2017年9月9日
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
	
	@Query(value ="SELECT COUNT(attachment_id) FROM  md_attachment",nativeQuery = true)
	long count();
}
