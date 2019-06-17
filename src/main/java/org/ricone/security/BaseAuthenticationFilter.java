package org.ricone.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/*
	Notes: https://medium.com/fullstackblog/spring-security-jwt-token-expired-custom-response-b85437914b81
*/


public abstract class BaseAuthenticationFilter extends BasicAuthenticationFilter {
	private final Environment environment;

	public BaseAuthenticationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		AuthRequest authRequest = new AuthRequest(req);
		if(authRequest.isAuthEnabled()) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req, authRequest.getToken());
			if(authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, String token) {
		try {
			if(StringUtils.isBlank(token)) {
				throw new JWTVerificationException(environment.getProperty("security.auth.error.token-blank"));
			}
			return getUsernamePasswordAuthenticationToken(token);
		}
		catch(SignatureVerificationException | JWTDecodeException exception) {
			req.setAttribute("JWTVerificationException", environment.getProperty("security.auth.error.invalid"));
		}
		catch (JWTVerificationException exception) {
			req.setAttribute("JWTVerificationException", exception.getMessage());
		}
		return null;
	}

	protected abstract UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) throws JWTVerificationException;

	protected Collection<GrantedAuthority> getACLs(Application application) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		application.getPermissions().forEach(pathPermission -> {
			if(pathPermission.getGet()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("get:" + pathPermission.getPath()));
			}
			if(pathPermission.getPost()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("post:" + pathPermission.getPath()));
			}
			if(pathPermission.getPut()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("put:" + pathPermission.getPath()));
			}
			if(pathPermission.getDelete()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("delete:" + pathPermission.getPath()));
			}
		});
		return grantedAuthorities;
	}
}
