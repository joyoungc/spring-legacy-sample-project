package io.github.joyoungc.web.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.web.admin.model.Admin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AdminRestController {

	@GetMapping("/admin/1")
	public Admin getAdmin() {
		log.info("## getAdmin()");
		Admin obj = new Admin();
		obj.setAdminId("test11");
		obj.setAdminName("¾îµå¹Î");
		return obj;
	}

}
