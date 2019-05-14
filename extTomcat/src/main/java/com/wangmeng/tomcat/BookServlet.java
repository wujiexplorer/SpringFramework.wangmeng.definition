package com.wangmeng.tomcat;

import java.io.IOException;

/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class BookServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("[get] book...");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("[post] book...");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
