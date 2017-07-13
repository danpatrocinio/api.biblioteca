package com.bairro.biblioteca.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editoras")
public class Editoras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editora")
	private Integer idEditora;
	private String nome;

	public Integer getIdEditora() {
		return idEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
