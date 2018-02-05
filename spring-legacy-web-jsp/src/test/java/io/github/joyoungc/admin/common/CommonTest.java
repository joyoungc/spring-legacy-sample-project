package io.github.joyoungc.admin.common;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;

import io.github.joyoungc.admin.common.util.CommonUtils;
import io.github.joyoungc.admin.user.service.UserBatchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml",
		"classpath:config/spring/application-servlet.xml" })
@WebAppConfiguration
public class CommonTest {
	
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MessageSourceAccessor message;
	
	@Autowired
	public UserBatchService userBatchService;
	
	String json;
	
	@Before
	public void setup() throws IOException {
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		FileReader file = new FileReader("src/test/resources/test/data/outputData.json");
		json = IOUtils.toString(file);
	}
	
	@Test
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
	public void userBatchService() throws Exception {
		JobExecution result = userBatchService.executeBatch();
		assertThat("COMPLETED", is(result.getExitStatus().getExitCode()) );
	}
	
	// @Test
	public void regExTest() {
		String test = "[|내용이 들어있다|]";
		assertThat(test.startsWith("[|"), is(true));
		test = test.replaceAll("[\\|\\[\\]]", "");
		assertThat("내용이 들어있다", is(test));
	}
	
	// @Test
	public void timeTest() {
		int max = 2000;
		int min = 100;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		log.debug("## randomNum : {}" ,randomNum);
	}
	
	@Test
	public void jsonPathTest() {
		assertThat(JsonPath.parse(json).read("$.output.text"), hasItem("TE_상품_0001"));
		assertThat(JsonPath.parse(json).read("$.output.text[0]"), is("TE_상품_0001"));
	}
	
	@Test
	public void stringUtilsTest() {
		String intentName = "TE_상품_0001";
		assertThat("TE", is(StringUtils.left(intentName, 2)));
		String resultCode = "TE_상품_0001_테스트테스트";
		log.info(StringUtils.abbreviate(resultCode, 13));
	}	

	@Test
	public void stringTest() {
		String str = "what is this!? 이것은 무엇??";
		log.debug("{}",str.getBytes().length);
		String res = CommonUtils.subStringBytes(str, 13, 3); 
		log.debug(res);
		assertThat(res.getBytes().length, is(13));
	}
	
	@Test
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
