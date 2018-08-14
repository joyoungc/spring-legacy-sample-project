package io.github.joyoungc.admin.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThan;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.admin.user.mapper.UserMapper;
import io.github.joyoungc.admin.user.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml" })
public class UserDaoTest {

	@Autowired
	private UserMapper userMapper;

	private String testIdForSelectUpdate;
	private String testIdForDelete;

	@Before
	public void setup() {
		testIdForSelectUpdate = "joyoungc";
		testIdForDelete = "aiden";
	}

	@Test
	public void selectUserTest() {
		User user = new User();
		List<User> users = userMapper.selectUser(user);
		assertThat(users.size(), greaterThan(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void getUserTest() {
		User user = userMapper.getUser(testIdForSelectUpdate);
		assertThat(user.getUserId(), is(testIdForSelectUpdate));
		log.debug("## getAuthorities : {}", user.getAuthorities());
		assertThat(user.getAuthorities(), hasSize(2));
		assertThat(user.getAuthorities(),
				contains(hasProperty("authCode", is("ROLE_ADMIN")), hasProperty("authCode", is("ROLE_USER"))));
	}

	@Test
	public void updateUserTest() {
		User user = new User();
		user.setUserId(testIdForSelectUpdate);
		user.setUserName("테스트");

		int resultCount = userMapper.updateUser(user);
		assertThat(resultCount, is(1));

		User result = userMapper.getUser(testIdForSelectUpdate);
		assertThat(result.getUserName(), is("테스트"));
	}

	@Test
	public void createUserTest() {
		User user = new User();
		user.setUserId("createTest");
		user.setUserName("테스트다");
		user.setPassword("password");
		user.setEnabled(true);

		int resultCount = userMapper.createUser(user);
		assertThat(resultCount, is(1));

		User result = userMapper.getUser("createTest");
		assertThat(result.getUserId(), is(user.getUserId()));
		assertThat(result.getEnabled(), is(true));
	}

	@Test
	public void deleteUserTest() {
		int resultCount = userMapper.deleteUser(testIdForDelete);
		assertThat(resultCount, is(1));

		User result = userMapper.getUser(testIdForDelete);
		assertThat(result, nullValue());
	}

}
