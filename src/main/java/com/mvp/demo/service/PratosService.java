package com.mvp.demo.service;

import java.util.List;
import java.util.Optional;

import com.mvp.demo.model.dto.PratosDto;

public interface PratosService {

	Optional<List<PratosDto>> buscaPratosPorRestaurante(long id_restaurante);
	
}