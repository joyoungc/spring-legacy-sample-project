package io.github.joyoungc.web.admin.batch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.github.joyoungc.web.admin.model.Admin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("step")
public class AdminItemProcessor implements ItemProcessor<Admin, Admin> {

	@Override
	public Admin process(Admin arg0) throws Exception {
		log.info("process :  {}", arg0);
		return arg0;
	}

}
