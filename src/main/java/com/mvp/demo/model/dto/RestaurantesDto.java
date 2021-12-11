package com.mvp.demo.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantesDto {

	private long id;	
	private String nome;
	private String bairro;	
	private String logradouro;	
	private long numero;	
	private long telefone;
	private List<PratosDto> pratos;
	
}
