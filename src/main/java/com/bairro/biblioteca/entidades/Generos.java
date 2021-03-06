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

	private String descricao;
	@Id
	@Column(name = "id_genero")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGenero;

	public String getDescricao() {
		return descricao;
	}

	public Integer getIdGenero() {
		return idGenero;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

}
