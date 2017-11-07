package io.github.joyoungc.web.admin;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;

import io.github.joyoungc.web.admin.model.ResponseDataIF;
import io.github.joyoungc.web.admin.model.ResponseDataIF.Intent;
import io.github.joyoungc.web.common.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTest {
	
	ObjectMapper mapper;
	String json;
	
	@Before
	public void setup() throws IOException {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		FileReader file = new FileReader("src/test/resources/test/data/outputData.json");
		json = IOUtils.toString(file);
	}
	
	// @Test
	public void java8lambdaTest() {
		
		Intent source = new Intent();
		source.setIntent("Intent1");
		source.setConfidence(Double.parseDouble("1"));
		
		List<Intent> intents = new ArrayList<>();
		
		Intent i1 = new Intent();
		i1.setIntent("Intent1");
		i1.setConfidence(Double.parseDouble("1"));
		intents.add(i1);
		
		Intent i2 = new Intent();
		i2.setIntent("Intent2");
		i2.setConfidence(Double.parseDouble("0.7"));
		intents.add(i2);
		
		Intent i3 = new Intent();
		i3.setIntent("Intent3");
		i3.setConfidence(Double.parseDouble("0.1"));
		intents.add(i3);
		
		assertTrue(i2.getConfidence() > 0.6);
		
		List<Intent> ambiguousIntents = intents.stream()
				.filter(i -> (!i.getIntent().equals(source.getIntent()) && i.getConfidence() > 0.6))
				.collect(Collectors.toList());
		
		System.out.println(ambiguousIntents);
		
		assertTrue(ambiguousIntents.size() > 0);
		
	}

	
	// @Test
	public void regExTest() {
		String test = "[|내용이 들어있다|]";
		//String regex = "^[  [\\|.\|])$";
		assertTrue(test.startsWith("[|"));
		test = test.replaceAll("[\\|\\[\\]]", "");
		assertEquals("내용이 들어있다",test);
		System.out.println(test);
		// System.out.println(test.matches(regex));
		//test.replaceAll(regex, replacement)
		//assertTrue(test.matches(regex));
	}
	
	// @Test
	public void timeTest() {
		int max = 2000;
		int min = 100;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		log.debug("## randomNum : {}" ,randomNum);
	}

	// @Test
	public void jsonFileReaderTest() throws JsonParseException, JsonMappingException, IOException {
		FileReader file = new FileReader("src/test/resources/test/data/outputData.json");
		String jsonStr = IOUtils.toString(file);
		ResponseDataIF responseObj = mapper.readValue(jsonStr, ResponseDataIF.class);
		System.out.println("## response : " + responseObj);
	}
	
	// @Test
	public void jsonPathTest() {
		assertThat(JsonPath.parse(json).read("$.output.text"), hasItem("TE_상품_0001"));
		assertThat(JsonPath.parse(json).read("$.output.text[0]"), is("TE_상품_0001"));
	}
	
	// @Test
	public void stringUtilsTest() {
		String intentName = "TE_상품_0001";
		assertEquals("TE", StringUtils.left(intentName, 2));
		
//		String resultCode = "자동차_anything_else!!!";
		String resultCode = "TE_상품_0001_테스트테스트";
		log.info(StringUtils.abbreviate(resultCode, 13));
		
	}

	@Test
	public void stringTest() {
		String str = "what is this!? 이것은 무엇??";
		System.out.println(str.getBytes().length);
		String res = CommonUtils.subStringBytes(str, 13, 3); 
		System.out.println(res);
		assertEquals(13, res.getBytes().length);
	}
	
	// @Test
	public void hashMapTest() {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("repeatBool", true);
		map.put("repeatStr", "true");
		map.put("repeatNull", null);
		
		String result1 = Objects.toString(map.get("repeatBool"));
		String result2 = Objects.toString(map.get("repeatStr"));
		String result3 = Objects.toString(map.get("repeatNull"),"");
		
		// Boolean.TRUE.toString();
		assertEquals("true", result1);
		assertEquals("true", result2); 
		assertEquals("", result3);
	}

}
