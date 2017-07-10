package com.bairro.biblioteca.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autores")
public class Autores {

	@Id
	@Column(name = "id_autores")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAutores;
	private String nome;

	public Integer getIdAutores() {
		return idAutores;
	}

	public void setIdAutores(Integer idAutores) {
		this.idAutores = idAutores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
