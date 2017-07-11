package com.bairro.biblioteca.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "generos")
public class Generos {
	
	@Id
	@Column(name = "id_generos")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGeneros;
	private String descricao;

	public Integer getIdGeneros() {
		return idGeneros;
	}

	public void setIdGeneros(Integer idGeneros) {
		this.idGeneros = idGeneros;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
