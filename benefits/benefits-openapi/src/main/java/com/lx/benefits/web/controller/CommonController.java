package com.lx.benefits.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

	@RequestMapping("/**")
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "API NOT FOUND")
	public void notFound() {
		return;
	}
}
