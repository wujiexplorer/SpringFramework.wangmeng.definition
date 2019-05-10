package com.wangmeng.web;

import com.wangmeng.util.Constant;
import com.wangmeng.util.FfmpegUtil;
import com.wangmeng.util.XunfeiConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * 讯飞转换web入口
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年3月6日
 */
public class XunfeiConvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = request.getParameter("message");
		String basePath =request.getSession().getServletContext().getRealPath("");
		Calendar c = Calendar.getInstance();  
		String audioName = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);
		//讯飞转PCM
		String pcmPath = basePath+ Constant.SF_FILE_SEPARATOR+audioName+".pcm";
		XunfeiConvertUtil util = new XunfeiConvertUtil();
		util.convert(message, pcmPath);
		//由于生成PCM是异步的，这里while一直等待，直到生成
		File file = new File(pcmPath);
		while(true){
			if(file.exists()){
				//PCM转MP3
			    String audioPath =  basePath+Constant.SF_FILE_SEPARATOR+audioName;
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				PrintWriter out = response.getWriter();
				Boolean flag = FfmpegUtil.processAudio(pcmPath,audioPath,"mp3");
				if(flag){
					out.println(audioName+".mp3");
				}else{
					out.println("转换失败");
				}
				break;
			}
		}
	}
}
