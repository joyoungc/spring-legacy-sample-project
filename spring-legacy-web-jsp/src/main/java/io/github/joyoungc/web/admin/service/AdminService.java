/**
 * Admin Service
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.web.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.web.admin.dao.AdminDao;
import io.github.joyoungc.web.admin.model.Admin;
import io.github.joyoungc.web.admin.model.User;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	public Admin getAdmin(String adminId) {
		return adminDao.getAdmin(adminId); 
	}
	
	public User getUserByUsername(String username) {
		return adminDao.getUserByUsername(username);
	}

}
