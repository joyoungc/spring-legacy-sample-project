package io.github.joyoungc.web.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
public class SpringRootConfig {
	
	@Value("${application.base.package}")
	private String componentId;

	/***
	 * DataSource 설정
	 * 
	 * @return
	 */
	/*
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
	*/
	
	/***
	 * DataSource 설정
	 * 
	 * @return
	 * @throws SQLException
	 */ 
	@Value("${jdbc.username}") 
	private String username;
	@Value("${jdbc.password}") 
	private String password;
	@Value("${jdbc.url}") 
	private String url;
	
	@Bean
	public DataSource dataSource() {
		try {
			OracleDataSource dataSource = new OracleDataSource();
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setURL(url);
	/*		dataSource.setImplicitCachingEnabled(true);
			dataSource.setFastConnectionFailoverEnabled(true);*/
			return dataSource;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
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