package com.lx.benefits.web.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.QiniuUtils;
import com.lx.benefits.bean.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author unknow on 2018-12-15 22:08.
 */
@Api(tags = "福粒平台-公共上传模块")
@RestController
@RequestMapping("/common/upload")
public class UploadController {

	private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private QiniuUtils qiniuUtils;

	@ApiOperation(value = "通用上传文件入口", response = String.class)
	@RequestMapping(value = { //
			"/saveadminfile", // 运营后台
			"/saveenterprisefile", // 企业后台
			"/savesupplierfile", // 供应商后台
			"/saveemployeefile", // 员工
			"/saveagentfile" // 代理商
	}, method = RequestMethod.POST)
	public JSONObject saveFile(@ApiParam(value = "file", name = "file") @RequestParam MultipartFile file) {
		return Response.succ(uploadFile(file));
	}

	private String uploadFile(MultipartFile file) {
		if (null == file) {
			return "请选择需要上传的文件!";
		}
		try {
			String filePath = qiniuUtils.upload(file);
			if (null == filePath || filePath.isEmpty()) {
				return "上传文件失败,请联系网站管理员!";
			}
			return filePath;
		} catch (Exception e) {
			logger.error("upload file exception, fileName={}", file.getOriginalFilename(), e);
			return "上传文件时出现了异常!";
		}
	}
}