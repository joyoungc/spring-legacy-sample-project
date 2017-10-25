package io.github.joyoungc.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT USERNAME ,PASSWORD, ENABLED FROM TB_ADM_LOGIN WHERE USERNAME = ?")
			.authoritiesByUsernameQuery("SELECT USERNAME, AUTHORITY FROM TB_ADM_AUTHORITIES WHERE USERNAME = ?");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  	  	http
  	  		.csrf().disable()  	  			
  	  		.formLogin() // 로그인 설정
		  	  	.loginPage("/login")
		  	  	.failureUrl("/login?error")		  	  	
		  	  	.usernameParameter("username")
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
  	  			//.antMatchers("/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
  	  			//.antMatchers("/**").access("hasRole('ROLE_ADMIN')")
  	  			//.antMatchers("/","/home").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
  	  			.antMatchers("/","/home").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
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

}
