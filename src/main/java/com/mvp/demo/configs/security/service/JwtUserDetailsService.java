package com.mvp.demo.configs.security.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mvp.demo.configs.security.service.JwtUserDetailsService;
import com.mvp.demo.configs.security.user.Usuarios;
import com.mvp.demo.configs.security.user.UsuariosService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UsuariosService usuariosService;
	
	public UserDetails loadUserByUsername(String email, String senha) throws UsernameNotFoundException {
		Usuarios usuarios = usuariosService.getByEmail(email, senha);
		if(usuarios==null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		} else {				
			if (usuarios.getEmail().toString().equals(email) && usuarios.getSenha().toString().equals(senha)) {
				return new User(usuarios.getEmail(), usuarios.getSenha(),
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with email: " + email);
			}
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuarios usuarios = usuariosService.getByEmail(email);
		if(usuarios==null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		} else {				
			if (usuarios.getEmail().toString().equals(email)) {
				return new User(usuarios.getNome(), usuarios.getSenha(),
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with email: " + email);
			}
		}
	}
}