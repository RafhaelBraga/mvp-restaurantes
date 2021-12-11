package com.mvp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.demo.model.dto.PratosDto;
import com.mvp.demo.service.PratosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pratos")
@Api(value = "/pratos")
public class PratosController {
	
	@Autowired
	private PratosService pratosService;
	
	@GetMapping(value = "/busca-pratos-por-restaurante/{id_restaurante}")
	@ApiOperation(value = "Busca pratos pelo id do restaurante")
	private ResponseEntity<List<PratosDto>> buscaPratosPorRestaurante(@PathVariable long id_restaurante) {
		Optional<List<PratosDto>> listPratosDto = pratosService.buscaPratosPorRestaurante(id_restaurante);
		return listPratosDto.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(listPratosDto.get());
	}
	
}
