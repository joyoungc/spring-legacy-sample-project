package io.github.joyoungc.admin.common.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
    @Autowired
	DataSource dataSource;
    
    @Autowired
    CustomUserDetailService userDetailService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  	  	http
  	  		.csrf().disable()
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