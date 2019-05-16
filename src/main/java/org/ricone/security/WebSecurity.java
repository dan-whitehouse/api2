package org.ricone.security;

import org.ricone.security.jwt.JWTAuthorizationFilter;
import org.ricone.security.jwt.JWTAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

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
			//.authorizeRequests().antMatchers("/api/requests/**").authenticated().and().addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
			//.authorizeRequests().antMatchers("/ims/oneroster/v1p1/**").authenticated().and().addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
			.authorizeRequests()
				.antMatchers(HttpMethod.GET,
			env.getProperty("springfox.documentation.swagger.v2.path"),
						"/docs/**",
						"/swagger-resources/**",
						"/swagger-ui.html**",
						"/webjars/**",
						"favicon.ico"
				).permitAll()
				.antMatchers("/requests/**")
					.authenticated()
						.and()
							.addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and().exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
		; // this disables session creation on Spring Security
	}


	//TODO - CheckOut : https://github.com/murraco/spring-boot-jwt

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