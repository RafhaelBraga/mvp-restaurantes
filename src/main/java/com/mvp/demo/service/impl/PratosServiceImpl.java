package com.mvp.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvp.demo.model.dto.PratosDto;
import com.mvp.demo.model.entity.Pratos;
import com.mvp.demo.repository.PratosRepository;
import com.mvp.demo.service.PratosService;

@Service
public class PratosServiceImpl implements PratosService{

	@Autowired
	private PratosRepository pratosRepository;
	
	@Override
	public Optional<List<PratosDto>> buscaPratosPorRestaurante(long id_restaurante) {
		
		List<PratosDto> listPratosDto = new ArrayList<>();
		List<Pratos> listPratos = pratosRepository.findPratosByRestauranteId(id_restaurante);
	
		for(Pratos prato: listPratos) {
			listPratosDto.add(new PratosDto(prato.getId(), prato.getNome(), prato.getDescricao(), prato.getPreco(), prato.getRestaurantes()));
		}
		return Optional.of(listPratosDto);
	}

}