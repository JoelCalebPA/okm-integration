package com.domain.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.domain.cache.WSCacheDAO;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.bean.CommonUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private WSCacheDAO wsCache;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws org.springframework.security.core.AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		try {
			wsCache.evictOKMWebservices(username); // Remove any previous cached username
			OKMWebservices ws = wsCache.setOKMWebservices(username, password);

			CommonUser commonUser = ws.getUser(username);
			String userFullName = commonUser.getName();

			// set roles for spring-security according to those received from OKM ws
			// response
			List<GrantedAuthority> authorities = new ArrayList<>();
			List<String> roles = ws.getRolesByUser(username);

			for (String role : roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}

			log.info("Loaded authorities for user: " + authorities);
			CustomUser user = new CustomUser(username, password, true, true, true, true, authorities, userFullName);
			log.info("logging in " + user);
			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			throw new BadCredentialsException("Login failed...!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
