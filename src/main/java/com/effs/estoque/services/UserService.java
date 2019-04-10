package com.effs.estoque.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.effs.estoque.security.UserSS;

/**
 * @author eduardosatyra
 *
 */
public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
