package com.wangmeng.doc.repository;

import com.wangmeng.doc.model.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 文档管理
 * 创建者 小柒2012
 * 创建时间	2017年9月9日
 */
public interface DocumentsRepository extends JpaRepository<Documents, Integer> {
	
	@Query(value ="SELECT COUNT(document_id) FROM  md_documents",nativeQuery = true)
	long count();
}
