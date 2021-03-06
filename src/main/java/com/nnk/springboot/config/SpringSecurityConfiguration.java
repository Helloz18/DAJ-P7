package com.nnk.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	
	/**
	 * AuthenticationManagerBuilder is a class that handle the authentication ruleset
	 * 
	 * @param auth
	 * @throws Exception 
	 * a query is used to check the username and its role. Spring Security demands the id to be named "username"
	 */
	   @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery(
					"select username, password, enabled from demo.users where username=?")
			.authoritiesByUsernameQuery(
					"select username, role from demo.users where username=?");
}

	/**
	 * Filters : The HTTP requests will be processed through the filter chain 
	 *  exceptionHandling() will use the CustomAccessDeniedHandler : to use a personalized 403 page
	 *  A login form by default is created
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
	
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/user/*").hasRole("ADMIN")
			.antMatchers("/admin/*").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		    .and()
			.logout()
			.logoutUrl("/app-logout");
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	

}
