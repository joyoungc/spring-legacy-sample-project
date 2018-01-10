package io.github.joyoungc.web.admin;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.joyoungc.web.admin.service.AdminBatchService;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"classpath:test/config/spring/test-application-context.xml"
		,"classpath:config/spring/application-servlet.xml" 
		})
@WebAppConfiguration
@ActiveProfiles(profiles = "local")
public class AdminTest {
	
	private MockMvc mockMvc;


	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	public AdminBatchService adminBatchService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	// @Test
	public void getAdmin() throws Exception {
		mockMvc
			.perform(get("/rest/admins/aiden"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.adminName").value("aiden"));
	}
	
	
	@Test
	public void adminBatchService() throws Exception {
		JobExecution result = adminBatchService.executeBatch();
		assertEquals("COMPLETED", result.getExitStatus().getExitCode());
	}

}