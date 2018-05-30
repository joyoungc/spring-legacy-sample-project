package io.github.joyoungc.admin.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.admin.common.exception.DataNotFoundException;
import io.github.joyoungc.admin.user.dao.UserDao;
import io.github.joyoungc.admin.user.model.User;
import io.github.joyoungc.admin.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	private final UserDao userDao;
	
	private final ModelMapper modelMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserDao userDao, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<User> selectUser(User user) {
		log.debug("## params : {}", user.getUserId());
		return userDao.selectUser(user);
	}

	public User getUser(String userId) {
		log.debug("## params : {}", userId);
		return userDao.getUser(userId);
	}

	@Transactional
	public void updateUser(UserDTO.Update dto, String userId) {
		log.debug("## params : {}", dto);
		User user = modelMapper.map(dto, User.class);
		user.setUserId(userId);
		int result = userDao.updateUser(user);
		
		if (result < 1) {
			throw new DataNotFoundException("User Not Exist");
		}
	}

	@Transactional
	public void createUser(UserDTO.Create dto) {
		log.debug("## params : {}", dto);
		User user = modelMapper.map(dto, User.class);
		user.setPassword(passwordEncoder.encode(dto.getPassword())); // 입력된 password를 암호화
		userDao.createUser(user);
	}

	@Transactional
	public void deleteUser(String userId) {
		log.debug("## params : {}", userId);
		userDao.deleteUser(userId);
	}

}
