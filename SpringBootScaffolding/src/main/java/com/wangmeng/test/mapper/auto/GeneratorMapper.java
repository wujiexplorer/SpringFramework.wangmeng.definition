package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.custom.TsysTables;

import java.util.List;
import java.util.Map;

/**
 * 数据库接口
* @Title: GeneratorMapper.java 
* @Package com.fc.test.mapper.auto 
* @author fuce  
* @date 2019年5月9日 上午12:31:13 
* @version V1.0   
 */
public interface GeneratorMapper {
	/**
	 * 查询当前所有表
	 * @param tableName 条件搜索
	 * @return
	 */
	 List<TsysTables> queryList(String tableName);
	 
	 /**
	  * 查询具体某表信息
	  * @param tableName
	  * @return
	  */
	 TsysTables queryTable(String tableName);
	 
	 /**
	  * 查询表详情
	  * @param tableName
	  * @return
	  */
	 List<Map<String, String>> queryColumns(String tableName);
}
