/***
 * SpringSecurityConfig 
 * 
 * @author 	joyoungc
 * @date 	2017.10.25
 */
package io.github.joyoungc.admin.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.joyoungc.admin.common.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private final CustomUserDetailService userDetailService;
    
	public SpringSecurityConfig(CustomUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	/***
	 * 사용자 인증정보를 DB와 연동, 암호화된 Password를 위한 encoder 설정
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvier = new DaoAuthenticationProvider();
		authProvier.setUserDetailsService(userDetailService);
		authProvier.setPasswordEncoder(passwordEncoder());
		auth.eraseCredentials(false);
		auth.authenticationProvider(authProvier);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  	  	http
  	  		.csrf().disable()
  	  		.headers().frameOptions().sameOrigin()
  	  		.and()
  	  		.formLogin() // 로그인 설정
		  	  	.loginPage("/login")
		  	  	// .defaultSuccessUrl("/welcome")
		  	  	.failureUrl("/login?error")
		  	  	.usernameParameter("userId")
		  	  	.passwordParameter("password")
		  	  	.permitAll()
		  	  	.and()
            .logout() // 로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
  	  		.authorizeRequests()
  	  			.antMatchers("/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
  	  			.anyRequest().authenticated()
  	  			.and()
  			.exceptionHandling()
  				.accessDeniedPage("/403");
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers("/resources/**");
	}
	
	/***
	 * BCrypt 암호화 encoder Bean 등록
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}