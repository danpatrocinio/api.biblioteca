package com.bairro.biblioteca.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargos {

	private String atribuicoes;
	private String descricao;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cargo")
	private Integer idCargo;
	private String tipo;

	public String getAtribuicoes() {
		return atribuicoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setAtribuicoes(String atribuicoes) {
		this.atribuicoes = atribuicoes;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
