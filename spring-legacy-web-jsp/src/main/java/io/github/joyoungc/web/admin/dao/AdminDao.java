/**
 * Admin Dao
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.web.admin.dao;

import org.springframework.stereotype.Repository;

import io.github.joyoungc.web.admin.model.Admin;
import io.github.joyoungc.web.admin.model.User;

@Repository
public interface AdminDao {
	
	Admin getAdmin(String adminId);

	User getUserByUsername(String username);

}