package com.mvp.demo.configs.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuariosService {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	public Usuarios getByEmail(String email, String senha) {
		List<Usuarios> listUsuarios = usuariosRepository.findUsuariosByEmail(email, senha);
		if(listUsuarios.isEmpty())
			return null;
		return listUsuarios.get(0);
	}	
	
	public Usuarios getByEmail(String email) {
		List<Usuarios> listUsuarios = usuariosRepository.findUsuariosByEmail(email);
		if(listUsuarios.isEmpty())
			return null;
		return listUsuarios.get(0);
	}	

}