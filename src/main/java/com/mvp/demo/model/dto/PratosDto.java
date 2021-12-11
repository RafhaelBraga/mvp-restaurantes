package com.mvp.demo.model.dto;

import com.mvp.demo.model.entity.Restaurantes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PratosDto {

	private long id;	
	private String nome;
	private String descricao;
	private long preco;	
	private Restaurantes restaurantes;
	
}
