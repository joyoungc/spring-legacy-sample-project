/**
 * Admin Rest Controller
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.web.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.web.admin.model.Admin;
import io.github.joyoungc.web.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AdminRestController {
	
	@Autowired
	AdminService adminService;

	@GetMapping("/admins/{adminId}")
	public Admin getAdmin(@PathVariable String adminId) {
		log.info("## getAdmin()");
		return adminService.getAdmin(adminId);
	}

}
