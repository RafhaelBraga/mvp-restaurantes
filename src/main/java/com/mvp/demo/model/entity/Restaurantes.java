package com.mvp.demo.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurantes")
public class Restaurantes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	


	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "nome")
	private String nome;

	@Size(max = 20)
	@Column(name = "bairro")
	private String bairro;	
	
	@Size(max = 20)
	@Column(name = "logradouro")
	private String logradouro;	
	
	@Column(name = "numero")
	private long numero;	
	
	@Size(max = 20)
	@Column(name = "telefone")
	private long telefone;
	
	@OneToMany(mappedBy = "id")
    @JsonManagedReference
	private List<Pratos> pratos;
	
}
