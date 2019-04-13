package com.effs.estoque.resources.post;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.security.JWTUtil;
import com.effs.estoque.security.UserSS;
import com.effs.estoque.services.UserService;

/**
 * @author eduardosatyra
 *
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse res) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		res.addHeader("Authorization", "Bearer" + token);
		return ResponseEntity.noContent().build();
	}
}
