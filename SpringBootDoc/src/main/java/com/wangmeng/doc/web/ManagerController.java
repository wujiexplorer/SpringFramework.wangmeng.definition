package com.wangmeng.doc.web;

import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.common.constans.Option;
import com.wangmeng.doc.common.utils.Result;
import com.wangmeng.doc.model.Attachment;
import com.wangmeng.doc.model.Books;
import com.wangmeng.doc.model.Member;
import com.wangmeng.doc.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "manager")
public class ManagerController {
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DocumentsRepository documentsRepository;
	@Autowired
	BooksRepository booksRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	OptionsRepository optionsRepository;
	
	@RequestMapping(value="",method=RequestMethod.GET)
    public String  manager(ModelMap map) {
		 logger.info("管理后台 ");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 long booksCount =  booksRepository.count();
		 long documentCount = documentsRepository.count();
		 long memberCount =  memberRepository.count();
		 long attachmentCount = attachmentRepository.count();
		 map.addAttribute("booksCount", booksCount);
		 map.addAttribute("documentCount", documentCount);
		 map.addAttribute("memberCount", memberCount);
		 map.addAttribute("attachmentCount", attachmentCount);
		 return "manager/index";
    }
	@RequestMapping(value="users",method=RequestMethod.GET)
    public String  users(ModelMap map) {
		 logger.info("用户管理 ");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Member> memberList =  memberRepository.findAll();
		 map.addAttribute("memberList", memberList);
		 return "manager/users";
    }
	@RequestMapping(value="createMember",method=RequestMethod.POST)
    public @ResponseBody Result createMember(Member member,ModelMap map) {
		 logger.info("创建用户");
		 Result result = new Result();
		 Member user = memberRepository.findByAccount(member.getAccount());
		 if(user!=null){
			 result.setCode(Constans.ERROR);
			 result.setMsg("账号已存在");
		 }else{
			 memberRepository.save(member);
			 result.setCode(Constans.SUCCESS);
		 }
		 return result;
    }
	@RequestMapping(value="setting",method=RequestMethod.GET)
    public String  setting(ModelMap map) {
		 logger.info("用户管理 ");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 map.addAttribute("ENABLE_ANONYMOUS", Constans.mapOptions.get(Option.ENABLE_ANONYMOUS.getCode()));
		 map.addAttribute("ENABLED_REGISTER", Constans.mapOptions.get(Option.ENABLED_REGISTER.getCode()));
		 map.addAttribute("ENABLED_CAPTCHA", Constans.mapOptions.get(Option.ENABLED_CAPTCHA.getCode()));
		 map.addAttribute("ENABLE_DOCUMENT_HISTORY", Constans.mapOptions.get(Option.ENABLE_DOCUMENT_HISTORY.getCode()));
		 return "manager/setting";
    }
	@RequestMapping(value="setting",method=RequestMethod.POST)
    public @ResponseBody Result setting(Member member, ModelMap map) {
		 logger.info("配置管理");
		 Result result = new Result();
		 return result;
    }
	@RequestMapping(value="attach/list",method=RequestMethod.GET)
    public String attachList(Member member,ModelMap map) {
		 logger.info("附件管理");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Attachment> attachList =  attachmentRepository.findAll();
		 map.addAttribute("attachList", attachList);
		 return "manager/attach_list";
    }
	@RequestMapping(value="books",method=RequestMethod.GET)
    public String  books(ModelMap map) {
		 logger.info("项目管理");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Books> bookList =  booksRepository.findAll();
		 map.addAttribute("bookList",bookList);
		 return "manager/books";
    }
}
