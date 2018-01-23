package io.github.joyoungc.web.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.web.user.dao.UserDao;
import io.github.joyoungc.web.user.model.User;
import io.github.joyoungc.web.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<User> selectUser(User user) {
		log.debug("## params : {}", user.getUserId());
		return userDao.selectUser(user);
	}

	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Transactional
	public void updateUser(UserDTO.Update dto, String userId) {
		User user = modelMapper.map(dto, User.class);
		user.setUserId(userId);
		log.debug("## user : {}", user);
		userDao.updateUser(user);
	}

	public void createUser(UserDTO.Create dto) {
		// TODO Auto-generated method stub
		
	}

	public User deleteUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
