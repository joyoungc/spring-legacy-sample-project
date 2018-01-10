package io.github.joyoungc.web.common.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableTransactionManagement
public class SpringRootConfig {
	
	/***
	 * DataSource 설정
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2)
				.addScript("/sql/create-db-h2.sql")
				.addScript("/sql/insert-data.sql")
				.build();
		return db;
	}
	
	
	/***
	 * DataSource 설정
	 * 
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/***
	 * Jackson ObjectMapper 빈 생성
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}