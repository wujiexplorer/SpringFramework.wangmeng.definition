package com.wangmeng.doc.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.common.constans.Option;
import com.wangmeng.doc.model.Books;
import com.wangmeng.doc.repository.BooksRepository;
import com.wangmeng.doc.repository.MemberRepository;
import com.wangmeng.doc.repository.OptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private OptionsRepository optionsRepository;
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired  
	private DefaultKaptcha defaultKaptcha;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
    public String  index(ModelMap map) {
		 logger.info("首页");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Books> bookList =  booksRepository.findAll();
		 map.addAttribute("bookList",bookList);
		 return "home/index";
    }
	@RequestMapping("/defaultKaptcha")  
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{  
	     byte[] captchaChallengeAsJpeg = null;    
	     ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();    
	     try {    
	     String createText = defaultKaptcha.createText();  
	     httpServletRequest.getSession().setAttribute("vrifyCode", createText);  
	     BufferedImage challenge = defaultKaptcha.createImage(createText);  
	     ImageIO.write(challenge, "jpg", jpegOutputStream);  
	     } catch (IllegalArgumentException e) {    
	         httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);    
	         return;    
	     }   
	     captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
	     httpServletResponse.setHeader("Cache-Control", "no-store");    
	     httpServletResponse.setHeader("Pragma", "no-cache");    
	     httpServletResponse.setDateHeader("Expires", 0);    
	     httpServletResponse.setContentType("image/jpeg");    
	     ServletOutputStream responseOutputStream =    
	             httpServletResponse.getOutputStream();    
	     responseOutputStream.write(captchaChallengeAsJpeg);    
	     responseOutputStream.flush();    
	     responseOutputStream.close();    
    }  
}
