package io.github.joyoungc.web.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.joyoungc.web.user.dao.UserDao;
import io.github.joyoungc.web.user.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> selectUser(User user) {
		log.debug("## params : {}", user.getUserId());
		return userDao.selectUser(user);
	}

}
