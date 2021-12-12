package com.mvp.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvp.demo.model.dto.RestaurantesDto;
import com.mvp.demo.model.entity.Restaurantes;
import com.mvp.demo.repository.RestaurantesRepository;

@Service
public class RestaurantesService {
	
	@Autowired
	private RestaurantesRepository restaurantesRepository;
	

	public Optional<List<RestaurantesDto>> buscaRestaurantes() {	
		
		List<RestaurantesDto> listRestaurantesDto = new ArrayList<>();
		List<Restaurantes> listRestaurantes = restaurantesRepository.findAll();
	
		for(Restaurantes restaurantes: listRestaurantes) {
			listRestaurantesDto.add(
					new RestaurantesDto(restaurantes.getId(),
										restaurantes.getNome(),
										restaurantes.getBairro(),
										restaurantes.getLogradouro()
										,restaurantes.getNumero(),
										restaurantes.getTelefone(),
										restaurantes.getPratos()
			  ));
		}
		return Optional.of(listRestaurantesDto);
	}
	
}
