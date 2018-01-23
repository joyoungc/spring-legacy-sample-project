package io.github.joyoungc.web.admin;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.joyoungc.web.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml",
		"classpath:config/spring/application-servlet.xml" })
@WebAppConfiguration
@ActiveProfiles(profiles = "local")
public class UserTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MessageSourceAccessor message;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// @Test
	public void messageSourceAccessorTest() {
		/* Case 1 */
		String success = message.getMessage("success"); // args : 메시지코드
		assertThat(success, is("성공입니다."));

		/* Case 2 */
		Object[] args = new String[] { "1", "userId" };
		String required = message.getMessage("required", args); // args : 메시지코드, 메시지 Arguments 바인딩
		assertThat(required, is("1) userId은 필수항목입니다."));
	}
	
	// @Test
	public void updateUserTest() throws Exception {
		UserDTO.Update dto = new UserDTO.Update();
		dto.setUserName("Aiden");
		dto.setPassword("password");
		// dto.setEnabled(1);
		
		mockMvc.perform(put("/users/1")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .content(objectMapper.writeValueAsString(dto))
				  ).andExpect(status().isOk());
	}
	
	@Test
	public void createUserTest() throws Exception {
		UserDTO.Update dto = new UserDTO.Update();
		dto.setUserName("Aiden");
		dto.setPassword("password");
		dto.setEnabled(2);
		
		mockMvc.perform(post("/user/rest/users")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .content(objectMapper.writeValueAsString(dto))
				  ).andExpect(status().isOk());
	}
	
}
