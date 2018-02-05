/**
 * UserDao
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.admin.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.joyoungc.admin.user.model.User;

@Repository
public interface UserDao {

	List<User> selectUser(User user);

	User getUser(String userId);

	int updateUser(User user);

	int createUser(User user);

	int deleteUser(String userId);
	
}
