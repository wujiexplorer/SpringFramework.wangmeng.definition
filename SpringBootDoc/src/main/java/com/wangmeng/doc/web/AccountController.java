package com.wangmeng.doc.web;

import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.common.constans.Option;
import com.wangmeng.doc.common.utils.MD5Util;
import com.wangmeng.doc.common.utils.Result;
import com.wangmeng.doc.model.Member;
import com.wangmeng.doc.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
    public String  login(ModelMap map) {
		 logger.info("用户登陆 ");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 map.addAttribute("ENABLED_REGISTER", Constans.mapOptions.get(Option.ENABLED_REGISTER.getCode()));
		 map.addAttribute("ENABLED_CAPTCHA", Constans.mapOptions.get(Option.ENABLED_CAPTCHA.getCode()));
		 return "account/login";
    }
	@RequestMapping(value="login",method=RequestMethod.POST)
    public @ResponseBody Result login(Member member, String code, HttpServletRequest request) {
		 Result result = new Result();
		 logger.info("用户登陆 ");
		 Member user = memberRepository.findByAccount(member.getAccount());
		 String vrifyCode =  (String) request.getSession().getAttribute("vrifyCode");
		 if(vrifyCode.equalsIgnoreCase(code)){
			 if(user!=null){
				 if(user.getPassword().equals(MD5Util.MD5(member.getPassword()))){
					 request.getSession().setAttribute(Constans.CURRENT_USER, user);
					 result.setCode(Constans.SUCCESS);
				 }else{
					 result.setCode(Constans.ERROR);
					 result.setMsg("密码错误");
				 }
			 }else{
				 result.setCode(Constans.ERROR);
				 result.setMsg("账号不存在");
			 }
		 }else{
			 result.setCode(Constans.ERROR);
			 result.setMsg("验证码错误");
		 }
		 return result;
    }
	@RequestMapping(value="register",method=RequestMethod.GET)
    public String  register(ModelMap map) {
		 logger.info("用户注册 ");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 map.addAttribute("ENABLED_REGISTER", Constans.mapOptions.get(Option.ENABLED_REGISTER.getCode()));
		 map.addAttribute("ENABLED_CAPTCHA", Constans.mapOptions.get(Option.ENABLED_CAPTCHA.getCode()));
		 return "account/register";
    }
	@RequestMapping(value="register",method=RequestMethod.POST)
    public @ResponseBody Result  register(Member member, String code,HttpServletRequest request) {
		 Result result = new Result();
		 logger.info("用户注册 ");
		 Member user = memberRepository.findByAccount(member.getAccount());
		 String vrifyCode =  (String) request.getSession().getAttribute("vrifyCode");
		 if(vrifyCode.equalsIgnoreCase(code)){
			 if(user==null){
				 memberRepository.save(member);
				 result.setCode(Constans.SUCCESS);
			 }else{
				 result.setCode(Constans.ERROR);
				 result.setMsg("账号已存在");
			 }
		 }else{
			 result.setCode(Constans.ERROR);
			 result.setMsg("验证码错误");
		 }
		 return result;
    }
	@RequestMapping(value="find_password",method=RequestMethod.GET)
    public String  findPassword(ModelMap map) {
		 logger.info("找回密码");
		 map.addAttribute("SITE_NAME", Constans.mapOptions.get(Option.SITE_NAME.getCode()));
		 return "account/find_password_setp1";
    }
	@RequestMapping(value="find_password",method=RequestMethod.POST)
    public @ResponseBody Result  findPassword(String email, String code,HttpServletRequest request) {
		 logger.info("{}:找回密码",email);
		 Result result = new Result();
		 Member user = memberRepository.findByEmail(email);
		 String vrifyCode =  (String) request.getSession().getAttribute("vrifyCode");
		 if(vrifyCode.equalsIgnoreCase(code)){
			 if(user!=null){
				 result.setCode(Constans.SUCCESS);
			 }else{
				 result.setCode(Constans.ERROR);
				 result.setMsg("账号不存在");
			 }
		 }else{
			 result.setCode(Constans.ERROR);
			 result.setMsg("验证码错误");
		 }
		 return result;
    }
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().setAttribute(Constans.CURRENT_USER, null);
		return "redirect:index";
	}
}
