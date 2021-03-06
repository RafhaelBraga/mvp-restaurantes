package com.mvp.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pratos")
public class Pratos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull
	@Column(name = "preco")
	private long preco;	

	@ManyToOne
    @JoinColumn(name = "id_restaurante")
    @JsonIgnore
	private Restaurantes restaurantes;
	
}
