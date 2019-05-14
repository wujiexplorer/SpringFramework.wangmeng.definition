package com.wangmeng.tomcat;
/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public abstract class MyServlet {
	
	public abstract void doGet(MyRequest myRequest, MyResponse myResponse);
	
	public abstract void doPost(MyRequest myRequest, MyResponse myResponse);
	
	public void service(MyRequest myRequest, MyResponse myResponse) {
		if(myRequest.getMethod().equalsIgnoreCase("POST")) {
			doPost(myRequest, myResponse);
		}else if(myRequest.getMethod().equalsIgnoreCase("GET")) {
			doGet(myRequest, myResponse);
		}
	}
}
