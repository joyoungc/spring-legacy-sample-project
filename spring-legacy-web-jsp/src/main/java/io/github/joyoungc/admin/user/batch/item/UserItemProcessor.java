package io.github.joyoungc.admin.user.batch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.github.joyoungc.admin.user.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("step")
public class UserItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User user) throws Exception {
		log.info("process :  {}", user);
		return user;
	}

}
