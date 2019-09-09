package com.lx.benefits.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.service.card.MemberCardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/cards")
public class MemberCardController {

	@Autowired
	private MemberCardService memberCardService;

}
