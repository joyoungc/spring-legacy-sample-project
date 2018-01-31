package io.github.joyoungc.web.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

public class UserDTO {
	
	@Data
	public static class Create {
		
		@NotBlank
		@Size(min = 4, max = 20)
		private String userId;
		
		@NotBlank		
		private String userName;
		
		@NotBlank
		private String password;
		
		@NotNull
		@Range(min=0, max=1)
		private Integer enabled;
	}
	
	@Data	
	public static class Update {
		@NotBlank
		private String userName;
		private String password;
		private Integer enabled;
	}
	
	@Data
	public static class Read {
		@NotBlank
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
