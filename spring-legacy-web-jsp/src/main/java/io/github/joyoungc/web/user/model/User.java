package io.github.joyoungc.web.user.model;

import lombok.Data;

@Data
public class User {
	private String userId;
	private String userName;
	private String password;
	private Integer enabled;
	private String createDatetime;
	private String updateDatetime;
}