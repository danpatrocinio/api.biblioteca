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
@Table(name = "emprestimos")
public class Emprestimos {

	@Temporal(TemporalType.DATE)
	@Column(name = "data_devolucao")
	private Date dataDevolucao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emprestimo")
	private Date dataEmprestimo;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_previsao_devolucao")
	private Date dataPrevisaoDevolucao;
	@Id
	@Column(name = "id_emprestimo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmprestimo;
	@Column(name = "id_funcionario")
	private Integer idFuncionario;
	@Column(name = "id_livro1")
	private Integer idLivro1;
	@Column(name = "id_livro2")
	private Integer idLivro2;
	@Column(name = "id_livro3")
	private Integer idLivro3;
	@Column(name = "id_usuario")
	private Integer idUsuario;
	@Column(name = "multa_por_atraso")
	private Double multaPorAtraso;
	private String observacoes;

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public Date getDataPrevisaoDevolucao() {
		return dataPrevisaoDevolucao;
	}

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public Integer getIdLivro1() {
		return idLivro1;
	}

	public Integer getIdLivro2() {
		return idLivro2;
	}

	public Integer getIdLivro3() {
		return idLivro3;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public Double getMultaPorAtraso() {
		return multaPorAtraso;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public void setDataPrevisaoDevolucao(Date dataPrevisaoDevolucao) {
		this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public void setIdLivro1(Integer idLivro1) {
		this.idLivro1 = idLivro1;
	}

	public void setIdLivro2(Integer idLivro2) {
		this.idLivro2 = idLivro2;
	}

	public void setIdLivro3(Integer idLivro3) {
		this.idLivro3 = idLivro3;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setMultaPorAtraso(Double multaPorAtraso) {
		this.multaPorAtraso = multaPorAtraso;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
