package com.lx.benefits.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.ArgumentException;
import com.google.common.collect.ImmutableSet;
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

import java.util.Set;


@Api(tags = "福粒平台-公共上传模块")
@RestController
@RequestMapping("/common/upload")
public class UploadController {

    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private Set<String> imgTypeSet = ImmutableSet.of("jpg","jpeg","png","gif","bmp","webp");
    
    @Autowired
    private QiniuUtils qiniuUtils;
    

    @ApiOperation(value = "员工-通用上传文件入口", response = String.class)
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public JSONObject img(@ApiParam(value = "file", name = "file") @RequestParam MultipartFile file) {
        return Response.succ(uploadImg(file));
    }
    
    private String uploadImg(MultipartFile file) {
        if (null == file) {
            return "请选择需要上传的文件!";
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        if (!imgTypeSet.contains(suffix.toLowerCase())){
            throw new ArgumentException("上传图片格式不符合要求");
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