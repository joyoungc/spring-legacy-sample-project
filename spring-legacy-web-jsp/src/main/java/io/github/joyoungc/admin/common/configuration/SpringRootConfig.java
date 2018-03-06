package io.github.joyoungc.admin.common.configuration;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.joyoungc.admin.common.interceptor.RestApiRequestLoggingInterceptor;

@Configuration
public class SpringRootConfig {
	
	/***
	 * Jackson ObjectMapper 빈 생성
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper;
	}
	
	/***
	 * ModelMapper 빈 생성 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/***
	 * Watson Conversation RestTemplate (with BasicAuthorization)
	 * @param username
	 * @param password
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	@Bean
	public RestTemplate conversationRestTemplate(@Value("${watson.conversation.username:user}") String username,
			@Value("${watson.conversation.password:pw}") String password,
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
	 * Default RestTemplate
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	@Bean
	public RestTemplate defaultRestTemplate(@Value("${spring.rest.connect.timeout:5000}") int connectTimeout,
			@Value("${spring.rest.read.timeout:5000}") int readTimeout) {

		final RestTemplate restTemplate = new RestTemplate(this.requestFactory(connectTimeout, readTimeout));

		// restTemplate에서 사용될 Message Converter 설정
		restTemplate.setMessageConverters(
				Arrays.asList(new MappingJackson2HttpMessageConverter(), new StringHttpMessageConverter()));
		
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