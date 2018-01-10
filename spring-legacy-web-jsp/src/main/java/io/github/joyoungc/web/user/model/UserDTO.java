package io.github.joyoungc.web.user.model;

import lombok.Data;

public class UserDTO {
	
	@Data
	public static class Create {
		private String userId;
		private String userName;
		private String password;
	}
	
	@Data	
	public static class Update {
		private String userName;
		private String password;
		private Integer enabled;
	}
	
	@Data
	public static class Read {
		private String userId;
		private String userName;
	}
	
	@Data
	public static class Response {
		private String userId;
		private String userName;
		private String password;
		private Integer enabled;
		private String createDatetime;
		private String updateDatetime;
	}

}
