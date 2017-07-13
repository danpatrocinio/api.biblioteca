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
	@Column(name = "id_autor")
	private Integer idAutor;
	@Column(name = "id_editora")
	private Integer idEditora;
	@Column(name = "id_genero")
	private Integer idGenero;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer idLivro;
	private String titulo;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public String getEdicao() {
		return edicao;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public Integer getIdEditora() {
		return idEditora;
	}

	public Integer getIdGenero() {
		return idGenero;
	}

	public Integer getIdLivro() {
		return idLivro;
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

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
