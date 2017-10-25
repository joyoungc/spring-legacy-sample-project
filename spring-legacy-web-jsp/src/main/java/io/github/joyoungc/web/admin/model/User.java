package io.github.joyoungc.web.admin.model;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	private Integer enabled;
}