/**
 * UserDao
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.web.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.joyoungc.web.user.model.User;

@Repository
public interface UserDao {
	List<User> selectUser(User user);
}
