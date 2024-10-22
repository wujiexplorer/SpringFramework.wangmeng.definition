package com.wangmeng.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class MyRequest {
	
	private String url;
	private String method;
	
	public MyRequest(InputStream inputStream) throws IOException {
		
		String httpRequest = "";
		byte[] httpRequestBytes = new byte[1024];
		int length = 0;
		if((length = inputStream.read(httpRequestBytes)) > 0) {
			httpRequest = new String(httpRequestBytes, 0, length);
		}
		
		String httpHead = httpRequest.split("\n")[0];
		url = httpHead.split("\\s")[1];
		method = httpHead.split("\\s")[0];
		
		System.out.println(this.toString());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
		public String toString() {
			return "MyRequest -- url:" + url + ",method:" + method;
		}

}
