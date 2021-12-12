package com.mvp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.demo.model.entity.Restaurantes;

@Repository
public interface RestaurantesRepository extends JpaRepository<Restaurantes, Long>{

}
