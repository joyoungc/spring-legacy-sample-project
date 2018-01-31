package io.github.joyoungc.web.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.joyoungc.web.user.model.User;
import io.github.joyoungc.web.user.model.UserDTO;
import io.github.joyoungc.web.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/***
	 * 사용자 Page
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/user/list";
	}

	/***
	 * 사용자 등록
	 * 
	 * @param dto
	 */
	@PostMapping("/rest/users")
	public void createUser(@RequestBody @Valid UserDTO.Create dto) {
		userService.createUser(dto);
	}

	/***
	 * 사용자 목록 조회
	 * 
	 * @param read
	 * @return
	 */
	@GetMapping("/rest/users")
	@ResponseBody
	public List<User> selectUser(UserDTO.Read read) {
		return userService.selectUser(null);
	}

	/***
	 * 사용자 조회
	 * 
	 * @param read
	 * @return
	 */
	@GetMapping("/rest/users/{userId}")
	@ResponseBody
	public User getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	/***
	 * 사용자 수정
	 * 
	 * @param user
	 * @param userId
	 */
	@PutMapping("/rest/users/{userId}")
	public void updateUser(@RequestBody @Valid UserDTO.Update user, @PathVariable String userId) {
		userService.updateUser(user, userId);
	}
	
	/***
	 * 사용자 삭제
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/rest/users/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
	}

}
