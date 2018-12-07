package org.ricone.security;

import org.ricone.security.jwt.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	/*
		https://www.baeldung.com/spring-security-granted-authority-vs-role
		https://www.baeldung.com/spring-security-method-security
		https://www.baeldung.com/spring-security-expressions
		https://docs.spring.io/spring-security/site/docs/current/reference/html/authorization.html#el-access
		https://www.baeldung.com/spring-security-create-new-custom-security-expression
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests().anyRequest().authenticated().and().addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Doing Nothing... but somehow still required
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}