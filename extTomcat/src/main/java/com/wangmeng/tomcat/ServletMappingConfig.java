package com.wangmeng.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������url�ʹ����servlet�Ķ�Ӧ��ϵ
 * 
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class ServletMappingConfig {
	
	public static List<ServletMapping> servletMappingList = new ArrayList<>();;
	
	static {
		servletMappingList.add(new ServletMapping("Book", "/book", "com.wangmeng.tomcat.BookServlet"));
		servletMappingList.add(new ServletMapping("Car", "/car", "com.wangmeng.tomcat.CarServlet"));
	}

}
