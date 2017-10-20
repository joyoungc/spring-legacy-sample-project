package io.github.joyoungc.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/page")
public class AdminController {
	
	@RequestMapping("/admin/login")
	public String login() {
		log.info("## login()");
		return "/admin/login";
	}

}
