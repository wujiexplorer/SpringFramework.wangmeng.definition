package com.wangmeng.doc.web;

import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.common.constans.Option;
import com.wangmeng.doc.model.Books;
import com.wangmeng.doc.repository.BooksRepository;
import com.wangmeng.doc.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "book")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	private BooksRepository booksRepository;
	@Value("${server.path}")
	private String serverPath;
	
	@RequestMapping(value="",method=RequestMethod.GET)
    public String  book(ModelMap map) {
		 logger.info("我的项目");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Books> bookList =  booksRepository.findAll();
		 map.addAttribute("bookList",bookList);
		 map.addAttribute("serverPath",serverPath);
		 return "book/index";
    }
	@RequestMapping(value="{identify}/dashboard",method=RequestMethod.GET)
    public String  dashboard(@PathVariable String identify,ModelMap map) {
		 logger.info("项目概要");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 Books book =  booksRepository.findByIdentify(identify);
		 map.addAttribute("book",book);
		 return "book/dashboard";
    }
	@RequestMapping(value="{identify}/setting",method=RequestMethod.GET)
    public String  setting(@PathVariable String identify,ModelMap map) {
		 logger.info("项目设置");
		 Books book =  booksRepository.findByIdentify(identify);
		 map.addAttribute("book",book);
		 map.addAttribute("serverPath",serverPath);
		 return "book/setting";
    }
	@RequestMapping(value="saveBook",method=RequestMethod.GET)
    public String  saveBook(Books book,ModelMap map) {
		 logger.info("更新项目");
		 return "book/setting";
    }
	@RequestMapping(value="{identify}/users",method=RequestMethod.GET)
    public String  users(@PathVariable String identify,ModelMap map) {
		 logger.info("项目成员");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 List<Object[]> list = memberRepository.findByIdentify(identify);
		 map.addAttribute("userList",list);
		 map.addAttribute("identify",identify);
		 return "book/users";
    }
}
