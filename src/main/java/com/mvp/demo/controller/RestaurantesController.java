package com.mvp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.demo.model.dto.RestaurantesDto;
import com.mvp.demo.service.RestaurantesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/restaurantes")
@Api(value = "/restaurantes")
public class RestaurantesController {
	
	@Autowired
	private RestaurantesService restaurantesService;
	
	@GetMapping(value = "/busca-restaurantes")
	@ApiOperation(value = "Busca restaurantes")
	private ResponseEntity<List<RestaurantesDto>> buscaRestaurantes() {
		Optional<List<RestaurantesDto>> listRestaurantesDto = restaurantesService.buscaRestaurantes();
		return listRestaurantesDto.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(listRestaurantesDto.get());
	}
	
}
