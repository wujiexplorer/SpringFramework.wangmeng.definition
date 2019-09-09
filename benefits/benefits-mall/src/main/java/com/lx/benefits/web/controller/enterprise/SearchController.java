/**
 * NewHeight.com Inc.
 * Copyright (c) 2008-2010 All Rights Reserved.
 */
package com.lx.benefits.web.controller.enterprise;


import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.base.dto.PageForSearch;
import com.lx.benefits.bean.dto.query.QuerySearch;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.vo.query.NavigationVO;
import com.lx.benefits.bean.vo.query.SearchConditionVO;
import com.lx.benefits.bean.vo.query.SearchItemVO;
import com.lx.benefits.bean.vo.query.SearchShopVO;
import com.lx.benefits.web.ao.SearchAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 搜索控制器
 * @author zhuss
 * @2016年3月2日 上午11:42:03
 */
@RestController
@RequestMapping("/enterprise/search")
public class SearchController {
	private final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Resource
	private SearchAO searchAO;
	
	/**
	 * 搜索-导航(分类和品牌)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/navigation",method = RequestMethod.GET)
	public String navigation(QuerySearch search){
		try{
			MResultVO<NavigationVO> result = searchAO.navigation();
			return JsonUtil.convertObjToStr(result);
		}catch(MobileException me){
			log.error("[API接口 - 搜索导航  MobileException] = {}",me.getMessage());
			log.error("[API接口 - 搜索导航 返回值] = {}",JsonUtil.convertObjToStr(new MResultVO<>(me)));
			return JsonUtil.convertObjToStr(new MResultVO<>(me));
		}
	}
	
	
	/**
	 * 执行搜索
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String search(QuerySearch search){
		try{
			if(log.isInfoEnabled()){
				log.info("[API接口 - 执行搜索 入参] = {}",JsonUtil.convertObjToStr(search));
			}
			MResultVO<PageForSearch<SearchItemVO,List<SearchShopVO>>> result = searchAO.search(search);
			return JsonUtil.convertObjToStr(result);
		}catch(MobileException me){
			log.error("[API接口 - 执行搜索  MobileException] = {}",me.getMessage());
			log.error("[API接口 - 执行搜索 返回值] = {}",JsonUtil.convertObjToStr(new MResultVO<>(me)));
			return JsonUtil.convertObjToStr(new MResultVO<>(me));
		}
	}
	
	/**
	 * 搜索结果 -筛选
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/condition",method = RequestMethod.GET)
	public String condition(QuerySearch search){
		try{
			if(log.isInfoEnabled()){
				log.info("[API接口 - 搜索结果 -筛选 入参] = {}",JsonUtil.convertObjToStr(search));
			}
			MResultVO<List<SearchConditionVO>> result = searchAO.condition(search);
			return JsonUtil.convertObjToStr(result);
		}catch(MobileException me){
			log.error("[API接口 - 搜索结果 -筛选  MobileException] = {}",me.getMessage());
			log.error("[API接口 - 搜索结果 -筛选 返回值] = {}",JsonUtil.convertObjToStr(new MResultVO<>(me)));
			return JsonUtil.convertObjToStr(new MResultVO<>(me));
		}
	}

	/**
	 * 搜索结果 -筛选
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/conditionNav",method = RequestMethod.GET)
	public String conditionNav(QuerySearch search){
		try{
			if(log.isInfoEnabled()){
				log.info("[API接口 - 搜索结果 -筛选 入参] = {}",JsonUtil.convertObjToStr(search));
			}
			MResultVO<List<SearchConditionVO>> result = searchAO.conditionNav(search);
			return JsonUtil.convertObjToStr(result);
		}catch(MobileException me){
			log.error("[API接口 - 搜索结果 -筛选  MobileException] = {}",me.getMessage());
			log.error("[API接口 - 搜索结果 -筛选 返回值] = {}",JsonUtil.convertObjToStr(new MResultVO<>(me)));
			return JsonUtil.convertObjToStr(new MResultVO<>(me));
		}
	}
}
