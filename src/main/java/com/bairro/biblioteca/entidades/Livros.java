package com.bairro.biblioteca.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "livros")
public class Livros {

	@Temporal(TemporalType.DATE)
	@Column(name = "data_lancamento")
	private Date dataLancamento;
	private String edicao;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer idLivro;
	private Integer idAutor;
	private Integer idGenero;
	private Integer idEditora;
	private String titulo;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public String getEdicao() {
		return edicao;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	
	public Integer getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public Integer getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
