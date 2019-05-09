package com.wangmeng.doc.repository;

import com.wangmeng.doc.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文档选项词典
 * 创建者 小柒2012
 * 创建时间	2017年9月7日
 *
 */
public interface OptionsRepository extends JpaRepository<Options, Integer> {
	Options findByOptionName(String optionName);
}
