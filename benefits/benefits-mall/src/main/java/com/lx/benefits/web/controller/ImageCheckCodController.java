package com.lx.benefits.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.util.Response;
import com.lx.benefits.utils.ImageCheckCodeUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/common/imagecode")
@Slf4j
public class ImageCheckCodController {

	@Autowired
	private ImageCheckCodeUtil imageCheckCodeUtil;

	@GetMapping("/register")
	public void register(HttpServletResponse response) throws IOException {
		String codeKey = UUID.randomUUID().toString().replace("-", "");
		response.setHeader("codeKey", codeKey);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		String imageChcekCode = imageCheckCodeUtil.getImageChcekCode(codeKey, response.getOutputStream());
		log.info("register imagecode codeKey={}, imageChcekCode={}", codeKey, imageChcekCode);
	}

	@GetMapping(value = "/register", params = "base64")
	public Object registerBase64() throws IOException {
		String codeKey = UUID.randomUUID().toString().replace("-", "");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String imageChcekCode = imageCheckCodeUtil.getImageChcekCode(codeKey, outputStream);
		log.info("register imagecode codeKey={}, imageChcekCode={}", codeKey, imageChcekCode);
		String base64Image = new String(Base64.getEncoder().encode(outputStream.toByteArray()), "UTF-8");
		Map<String, String> response = new HashMap<>();
		response.put("base64Image", base64Image);
		response.put("codeKey", codeKey);
		return Response.succ(response);
	}

}
