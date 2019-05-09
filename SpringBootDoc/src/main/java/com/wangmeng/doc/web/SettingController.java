package com.wangmeng.doc.web;

import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.common.utils.FileUtil;
import com.wangmeng.doc.common.utils.MD5Util;
import com.wangmeng.doc.common.utils.Result;
import com.wangmeng.doc.model.Member;
import com.wangmeng.doc.repository.MemberRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "setting")
public class SettingController {
	private static final Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@Value("${web.upload.path}")
    private String uploadPath;
	
	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping(value="",method=RequestMethod.GET)
    public String  setting() {
		 logger.info("用户中心 ");
		 return "setting/index";
    }
	@RequestMapping(value="password",method=RequestMethod.GET)
    public String  password() {
		 logger.info("修改密码 ");
		 return "setting/password";
    }
	@RequestMapping(value="password",method=RequestMethod.POST)
    public @ResponseBody Result password(String password, String newPassword, HttpServletRequest request) {
		 logger.info("用户登陆 ");
		 Member member = (Member) request.getSession().getAttribute(Constans.CURRENT_USER);
		 Result result = new Result();
		 if(member.getPassword().equals(MD5Util.MD5(password))){
			 member.setPassword( MD5Util.MD5(newPassword));
			 memberRepository.save(member);
			 result.setCode(Constans.SUCCESS);
		 }else{
			 result.setCode(Constans.ERROR);
			 result.setMsg("原始密码错误");
		 }
		 return result;
    }
	@RequestMapping(value="modfiyUser",method=RequestMethod.POST)
    public @ResponseBody Result modfiyUser(String email,String phone,String description,HttpServletRequest request) {
		 logger.info("修改用户信息 ");
		 Member member = (Member) request.getSession().getAttribute(Constans.CURRENT_USER);
		 Result result = new Result();
		 member.setEmail(email);
		 member.setPhone(phone);
		 member.setDescription(description);
		 memberRepository.save(member);
		 result.setCode(Constans.SUCCESS);
		 return result;
    }
	@RequestMapping(value="upload")
    public @ResponseBody Result upload(@RequestParam("file")MultipartFile[] files,HttpServletRequest request) {
		logger.info("用户上传头像 ");
		Result result = new Result();
		try {
			Member member = (Member) request.getSession().getAttribute(Constans.CURRENT_USER);
	        //多文件上传
	        if(files!=null && files.length>=1) {
	            BufferedOutputStream bw = null;
	            try {
	                String fileName = files[0].getOriginalFilename();
	                //判断是否有文件(实际生产中要判断是否是音频文件)
	                if(StringUtils.isNoneBlank(fileName)) {
	                    //创建输出文件对象
	                    File outFile = new File(uploadPath + Constans.AVATAR_PATH + Constans.SF_FILE_SEPARATOR + member.getMemberId()+ FileUtil.getFileType(fileName));
	                    //拷贝文件到输出文件对象
	                    FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
	                }
	                result.setCode(Constans.SUCCESS);
	            } catch (Exception e) {
	                e.printStackTrace();
	                result.setCode(Constans.ERROR);
	            } finally {
	                try {
	                    if(bw!=null) {bw.close();}
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Constans.ERROR);
		}
		return result;
    }
}
