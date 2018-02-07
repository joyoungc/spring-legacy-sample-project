package io.github.joyoungc.admin.common.configuration;

import java.util.Arrays;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.joyoungc.admin.common.interceptor.RestApiRequestLoggingInterceptor;

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
	 * Transaction 설정
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
	
	/***
	 * ModelMapper 빈 생성 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public RestTemplate basicAuthRestTemplate(@Value("${spring.rest.username:user}") String username,
			@Value("${spring.rest.password:pw}") String password,
			@Value("${spring.rest.connect.timeout:5000}") int connectTimeout,
			@Value("${spring.rest.read.timeout:5000}") int readTimeout) {

		final RestTemplate restTemplate = new RestTemplate(this.requestFactory(connectTimeout, readTimeout));

		// restTemplate에서 사용될 Message Converter 설정
		restTemplate.setMessageConverters(
				Arrays.asList(new MappingJackson2HttpMessageConverter(), new StringHttpMessageConverter()));

		// BasicAuthorization 을 위해 Header에 username과 password를 설정
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
		
		// request, response 에 대한 내용을 logging
		restTemplate.getInterceptors().add(new RestApiRequestLoggingInterceptor());

		return restTemplate;
	}

	
	/***
	 * Time out 설정 및 <Attempted read from closed stream> 오류 회피
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	private BufferingClientHttpRequestFactory requestFactory(int connectTimeout, int readTimeout) {
		SimpleClientHttpRequestFactory simplefactory = new SimpleClientHttpRequestFactory();
		simplefactory.setConnectTimeout(connectTimeout);
		simplefactory.setReadTimeout(readTimeout);
		return new BufferingClientHttpRequestFactory(simplefactory);
	}
	
/*	@Bean
	public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}*/

}