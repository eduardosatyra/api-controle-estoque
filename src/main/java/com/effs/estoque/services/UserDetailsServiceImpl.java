package com.effs.estoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.security.UserSS;

/**
 * @author eduardosatyra
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente c = this.clienteRepository.findByEmail(email);
		if (c == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(c.getId(), c.getEmail(), c.getSenha(), c.getPerfil());
	}
}
