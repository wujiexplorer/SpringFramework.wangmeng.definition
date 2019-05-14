package com.wangmeng.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class MyTomcat {
	private int port;
	//��������url�ʹ�������servlet�Ķ�Ӧ��ϵ
	private Map<String, String>  urlServletMap = new HashMap<String, String>();
	
	public MyTomcat(int port) {
		this.port = port;
	}
	
	public void start() {
		initServletMapping();
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("MyTomcat is start...\n�����˿ڣ�" + port);
			
			while(true) {
				System.out.println("�ȴ�����...");
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				MyRequest myRequest = new MyRequest(inputStream);
				MyResponse myResponse = new MyResponse(outputStream);
				
				//����ַ�
				disPatch(myRequest, myResponse);
				socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//��ʼ��url�ʹ����servlet�Ķ�Ӧ��ϵ
	private void initServletMapping() {
		for(ServletMapping servletMapping: ServletMappingConfig.servletMappingList) {
			urlServletMap.put(servletMapping.getUrl(), servletMapping.getClassName());
		}
	}
	
	//�ַ���������
	private void disPatch(MyRequest myRequest, MyResponse myResponse) {
		String className = urlServletMap.get(myRequest.getUrl());
		
		//����
		try {
			Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(className);
			MyServlet myServlet = myServletClass.newInstance();
			
			myServlet.service(myRequest, myResponse);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MyTomcat myTomcat = new MyTomcat(8080);
		myTomcat.start();
	}
}
