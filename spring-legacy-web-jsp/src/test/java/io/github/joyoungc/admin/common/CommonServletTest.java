package io.github.joyoungc.admin.common;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml",
"classpath:test/config/spring/test-application-servlet.xml" })
@WebAppConfiguration
public class CommonServletTest {
	
	@Autowired
	private MessageSourceAccessor message;
	
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

}
