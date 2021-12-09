package com.mvp.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Pratos {

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
    @JoinColumn(name="id", nullable=false)
	private List<Restaurantes> restaurantes;
	
}
