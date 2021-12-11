package com.mvp.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvp.demo.model.entity.Pratos;

@Repository
public interface PratosRepository extends JpaRepository<Pratos, Long>{
	
	@Query("From Pratos p where p.restaurantes.id = :idRestaurante")
	List<Pratos> findPratosByRestauranteId(@Param("idRestaurante") long id_restaurante);

}