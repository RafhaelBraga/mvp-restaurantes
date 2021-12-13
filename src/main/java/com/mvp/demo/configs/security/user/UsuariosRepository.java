package com.mvp.demo.configs.security.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	@Query("From Usuarios u where u.email = :email"
			+ " and  u.senha = :senha")
	List<Usuarios> findUsuariosByEmail(@Param("email") String email, @Param("senha") String senha);

	@Query("From Usuarios u where u.email = :email")
	List<Usuarios> findUsuariosByEmail(@Param("email") String email);
}