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
	@Column(name = "id_autor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAutor;
	private String nome;

	public Integer getIdAutor() {
		return idAutor;
	}

	public String getNome() {
		return nome;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
