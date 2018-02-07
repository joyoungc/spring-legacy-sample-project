package io.github.joyoungc.admin.common;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;

import io.github.joyoungc.admin.common.util.CommonUtils;
import io.github.joyoungc.admin.user.service.UserBatchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml" })
public class CommonContextTest {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserBatchService userBatchService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	String json;
	
	@Before
	public void setup() throws IOException {
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
/*		FileReader file = new FileReader("src/test/resources/test/data/outputData.json");
		json = IOUtils.toString(file);*/
	}
	
	@Test
	public void passwordEncoderTest() {
		String encrypted = passwordEncoder.encode("qwer");
		log.debug("## encrypted : {}", encrypted);
	}
	
	@Ignore
	public void userBatchService() throws Exception {
		JobExecution result = userBatchService.executeBatch();
		assertThat("COMPLETED", is(result.getExitStatus().getExitCode()) );
	}
	
	@Ignore
	public void regExTest() {
		String test = "[|내용이 들어있다|]";
		assertThat(test.startsWith("[|"), is(true));
		test = test.replaceAll("[\\|\\[\\]]", "");
		assertThat("내용이 들어있다", is(test));
	}
	
	@Ignore
	public void timeTest() {
		int max = 2000;
		int min = 100;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		log.debug("## randomNum : {}" ,randomNum);
	}
	
	@Ignore
	public void jsonPathTest() {
		assertThat(JsonPath.parse(json).read("$.output.text"), hasItem("TE_상품_0001"));
		assertThat(JsonPath.parse(json).read("$.output.text[0]"), is("TE_상품_0001"));
	}
	
	@Ignore
	public void stringUtilsTest() {
		String intentName = "TE_상품_0001";
		assertThat("TE", is(StringUtils.left(intentName, 2)));
		String resultCode = "TE_상품_0001_테스트테스트";
		log.info(StringUtils.abbreviate(resultCode, 13));
	}	

	@Ignore
	public void stringTest() {
		String str = "what is this!? 이것은 무엇??";
		log.debug("{}",str.getBytes().length);
		String res = CommonUtils.subStringBytes(str, 13, 3); 
		log.debug(res);
		assertThat(res.getBytes().length, is(13));
	}
	
	@Ignore
	public void hashMapTest() {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("repeatBool", true);
		map.put("repeatStr", "true");
		map.put("repeatNull", null);
		
		String result1 = Objects.toString(map.get("repeatBool"));
		String result2 = Objects.toString(map.get("repeatStr"));
		String result3 = Objects.toString(map.get("repeatNull"),"");
		
		// Boolean.TRUE.toString();
		assertThat(result1, is("true"));
		assertThat(result2, is("true")); 
		assertThat(result3, is(""));
	}
	
}
