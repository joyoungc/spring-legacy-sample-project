package io.github.joyoungc.admin.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.joyoungc.admin.user.model.UserDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml",
		"classpath:test/config/spring/test-application-servlet.xml" })
@WebAppConfiguration
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getUserTest() throws Exception {
		mockMvc.perform(get("/user/rest/users/joyoungc")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value("joyoungc"));
	}
	
	@Test
	public void updateUserTest() throws Exception {
		UserDTO.Update dto = new UserDTO.Update();
		dto.setUserName("Aiden");
		dto.setPassword("password");
		
		mockMvc.perform(put("/user/rest/users/joyoungc")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .content(objectMapper.writeValueAsString(dto))
				  ).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void createUserTest() throws Exception {
		UserDTO.Create dto = new UserDTO.Create();
		dto.setUserId("NewOne");
		dto.setUserName("NewOne");
		dto.setPassword("password");
		dto.setEnabled(1);
		
		mockMvc.perform(post("/user/rest/users")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .content(objectMapper.writeValueAsString(dto))
				  ).andDo(print()).andExpect(status().isOk());
	}

}
