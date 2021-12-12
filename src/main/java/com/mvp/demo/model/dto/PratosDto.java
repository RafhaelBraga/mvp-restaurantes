package com.mvp.demo.model.dto;

import com.mvp.demo.model.entity.Restaurantes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PratosDto {

	private long id;	
	private String nome;
	private String descricao;
	private long preco;	
	public PratosDto(long id, String nome, String descricao, long preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	private Restaurantes restaurantes;
	
}
