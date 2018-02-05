package io.github.joyoungc.admin.user.model;

import java.util.List;

import lombok.Data;

@Data
public class User {
	
	private String userId;
	private String userName;
	private String password;
	private Boolean enabled;
	private String createDatetime;
	private String updateDatetime;
	List<Authority> authorities;
	
	@Data
	public static class Authority {
		private String userId;
		private String authCode;
	}
	
}