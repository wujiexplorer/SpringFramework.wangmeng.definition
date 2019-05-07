package com.wangmeng.mail.web;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通用访问拦截匹配
 * 创建者 科帮网
 * 创建时间	2018年4月3日
 */
@Api(tags ="通用访问拦截匹配")
@Controller
public class IndexController {
	
	/**
	 * 页面跳转
	 * @param module
	 * @param url
	 * @return
	 */
	@GetMapping("{url}.shtml")
	public String page(@PathVariable("url") String url) {
		return  url;
	}
	/**
	 * 页面跳转(二级目录)
	 * @param module
	 * @param function
	 * @param url
	 * @return
	 */
	@GetMapping("{module}/{url}.shtml")
	public String page(@PathVariable("module") String module,@PathVariable("url") String url) {
		return module + "/" + url;
	}
	
}
