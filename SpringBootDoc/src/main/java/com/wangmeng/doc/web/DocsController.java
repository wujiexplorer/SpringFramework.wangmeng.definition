package com.wangmeng.doc.web;

import com.wangmeng.doc.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "docs")
public class DocsController {
	private static final Logger logger = LoggerFactory.getLogger(DocsController.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping(value="{identify}",method=RequestMethod.GET)
    public String  login(ModelMap map) {
		 logger.info("查看项目");
		 return "account/login";
    }
	
}
