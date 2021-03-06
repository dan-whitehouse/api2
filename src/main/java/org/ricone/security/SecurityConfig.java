package org.ricone.security;

import org.ricone.config.cache.AppService;
import org.ricone.logging.LoggingFilter;
import org.ricone.security.oneroster.OneRosterAuthenticationEntryPoint;
import org.ricone.security.oneroster.OneRosterAuthorizationFilter;
import org.ricone.security.xpress.XPressAuthenticationEntryPoint;
import org.ricone.security.xpress.XPressAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
	@PropertySource("classpath:security.properties")
	public static class XPressWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final AppService service;
		private final Environment environment;
		private final LoggingFilter loggingFilter;

		public XPressWebSecurityConfigurationAdapter(AppService cacheService, Environment environment, LoggingFilter loggingFilter) {
			this.service = cacheService;
			this.environment = environment;
			this.loggingFilter = loggingFilter;
		}

		@Bean
		protected XPressAuthorizationFilter xPressAuthorizationFilter() throws Exception {
			return new XPressAuthorizationFilter(authenticationManagerBean(), service, environment);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
			http.antMatcher("/requests/**")
				.authorizeRequests().anyRequest().authenticated()
				.and()
					.addFilter(xPressAuthorizationFilter())
					.addFilterAfter(loggingFilter, XPressAuthorizationFilter.class)
					.exceptionHandling().authenticationEntryPoint(new XPressAuthenticationEntryPoint())
				.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
		}
	}

	@Configuration @Order(2)
	@PropertySource("classpath:security.properties")
	public static class OneRosterWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final AppService service;
		private final Environment environment;
		private final LoggingFilter loggingFilter;

		public OneRosterWebSecurityConfigurationAdapter(AppService cacheService, Environment environment, LoggingFilter loggingFilter) {
			this.service = cacheService;
			this.environment = environment;
			this.loggingFilter = loggingFilter;
		}

		@Bean
		protected OneRosterAuthorizationFilter oneRosterAuthorizationFilter() throws Exception {
			return new OneRosterAuthorizationFilter(authenticationManagerBean(), service, environment);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/ims/oneroster/v1p1/**")
					.authorizeRequests().anyRequest().authenticated()
					.and()
					.addFilter(oneRosterAuthorizationFilter())
					.addFilterAfter(loggingFilter, OneRosterAuthorizationFilter.class)
					.exceptionHandling().authenticationEntryPoint(new OneRosterAuthenticationEntryPoint())
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
		}
	}
}
