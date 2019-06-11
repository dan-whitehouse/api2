package org.ricone.security;

import org.ricone.init.CacheService;
import org.ricone.security.jwt.JWTAuthorizationFilter;
import org.ricone.security.oneroster.OneRosterAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
		https://www.baeldung.com/spring-security-granted-authority-vs-role
		https://www.baeldung.com/spring-security-method-security
		https://www.baeldung.com/spring-security-expressions
		https://docs.spring.io/spring-security/site/docs/current/reference/html/authorization.html#el-access
		https://www.baeldung.com/spring-security-create-new-custom-security-expression
	 */


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	//https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html#multiple-httpsecurity

	@Configuration @Order(1)
	public static class XPressWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final CacheService cacheService;

		public XPressWebSecurityConfigurationAdapter(CacheService cacheService) {this.cacheService = cacheService;}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/requests/**")
					.authorizeRequests().anyRequest().authenticated()
					.and()
						.addFilter(new JWTAuthorizationFilter(authenticationManagerBean(), cacheService))
							//.exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
			;
		}
	}

	@Configuration @Order(2)
	public static class OneRosterWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final CacheService cacheService;

		public OneRosterWebSecurityConfigurationAdapter(CacheService cacheService) {this.cacheService = cacheService;}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/ims/oneroster/v1p1/**")
					.authorizeRequests().anyRequest().authenticated()
					.and().addFilter(new OneRosterAuthorizationFilter(authenticationManagerBean(), cacheService))
			;
		}
	}
}
