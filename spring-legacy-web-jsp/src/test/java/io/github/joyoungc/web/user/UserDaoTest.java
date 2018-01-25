package io.github.joyoungc.web.user;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.web.user.dao.UserDao;
import io.github.joyoungc.web.user.model.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test/config/spring/test-application-context.xml" })
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	private String testUserId;

	@Before
	public void setup() {
		testUserId = "joyoungc";
	}

	@Test
	public void selectUserTest() {
		User user = new User();
		List<User> users = userDao.selectUser(user);
		assertThat(users.size(), greaterThan(0));
	}

	@Test
	public void getUserTest() {
		User user = userDao.getUser(testUserId);
		assertThat(user.getUserId(), is(testUserId));
	}

	@Test
	public void updateUserTest() {

		User user = new User();
		user.setUserId(testUserId);
		user.setUserName("테스트");

		int resultCount = userDao.updateUser(user);
		assertThat(resultCount, is(1));

		User result = userDao.getUser(testUserId);
		assertThat(result.getUserName(), is("테스트"));
	}

	@Test
	public void createUserTest() {

		User user = new User();
		user.setUserId("createTest");
		user.setUserName("테스트다");
		user.setPassword("password");
		user.setEnabled(1);

		int resultCount = userDao.createUser(user);
		assertThat(resultCount, is(1));

		User result = userDao.getUser("createTest");
		assertThat(result, is(user));
	}

}
