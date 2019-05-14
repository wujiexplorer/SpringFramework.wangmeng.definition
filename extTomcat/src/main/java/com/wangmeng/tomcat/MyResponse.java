package com.wangmeng.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class MyResponse {
	
	private OutputStream outputStream;
	
	public MyResponse (OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void write(String content) throws IOException {
		StringBuffer httpResponse = new StringBuffer();
		httpResponse.append("HTTP/1.1 200 OK\n")
					.append("Content-Type: text/html\n")
					.append("\r\n")
					.append(content);
		
		outputStream.write(httpResponse.toString().getBytes());
		outputStream.close();
	}

}
