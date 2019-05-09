package com.wangmeng.doc.repository;

import com.wangmeng.doc.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 项目
 * 创建者 小柒2012
 * 创建时间	2017年9月7日
 */
public interface BooksRepository extends JpaRepository<Books, Integer> {
	@Query(value ="SELECT COUNT(book_id) FROM  md_books",nativeQuery = true)
	long count();
	Books findByIdentify(String identify);
}
