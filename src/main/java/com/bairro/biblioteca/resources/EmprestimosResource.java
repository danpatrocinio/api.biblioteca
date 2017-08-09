package com.bairro.biblioteca.resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bairro.biblioteca.daos.EmprestimosDAO;
import com.bairro.biblioteca.entidades.Emprestimos;
import com.bairro.biblioteca.exceptions.ModelException;
import com.bairro.biblioteca.utils.MensagemWrapper;

@Path("/emprestimos")
public class EmprestimosResource {

	/*
	8.11. Ao cadastrar um empr�stimo � obrigat�rio informar: usu�rio, funcion�rio, no m�nimo 01 e no m�ximo 03 livros; data de empr�stimo.
	8.12. Ao cadastrar um empr�stimo a API deve calcular a data de previs�o de devolu��o do empr�stimo com 10 dias para cada livro. 
	     (ex. Ao emprestar dois livros 2*10=20 dias de prazo para devolver os dois).
	8.13. Permitir atualizar o empr�stimo inserindo a informa��o da data de devolu��o do(s) livro(s) do empr�stimo.
	8.14. Ao informar a data de devolu��o em atraso em rela��o a data de previs�o da devolu��o a API deve calcular uma multa pelo atraso de 5 reais + 2 reais por dia de atraso.
	 */

	@Inject
	private EmprestimosDAO dao;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Emprestimos emprestimo) {
		try {
			Emprestimos generoAtualizado = dao.atualizar(emprestimo);
			return Response.ok(generoAtualizado).status(202).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao atualizar o empr�stimo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletar(@PathParam("id") Integer idEmprestimo) {
		try {
			dao.deletar(idEmprestimo);
			MensagemWrapper mensagem = new MensagemWrapper("Emprestimo " + idEmprestimo + "deletado com sucesso!");
			return Response.ok(mensagem).status(200).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("N�o foi poss�vel deletar o empr�stimo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getAll() {
		return dao.getAll();
	}

	@GET
	@Path("/atrasados")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getAtrasados() {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(Calendar.getInstance().getTimeInMillis());
		String dataFormatada = "'" + formatador.format(data) + "'";
		return dao.buscarPorPropriedade("WHERE e.dataDevolucao is null and e.dataPrevisaoDevolucao < ", dataFormatada);
	}

	@GET
	@Path("/data_devolucao/{dataDevolucao}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataDevolucao(@PathParam("dataDevolucao") Long dataDevolucao) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(dataDevolucao);
		String dataFormatada = "'" + formatador.format(data) + "'";
		return dao.buscarPorPropriedade("WHERE e.dataDevolucao = ", dataFormatada);
	}

	@GET
	@Path("/data_emprestimo/{dataEmprestimo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataEmprestimo(@PathParam("dataEmprestimo") Long dataEmprestimo) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(dataEmprestimo);
		String dataFormatada = "'" + formatador.format(data) + "'";
		return dao.buscarPorPropriedade("WHERE e.dataEmprestimo = ", dataFormatada);
	}

	@GET
	@Path("/data_prev_devolucao/{dataPrevisaoDevolucao}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataPrevisaoDevolucao(
	        @PathParam("dataPrevisaoDevolucao") Long dataPrevisaoDevolucao) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(dataPrevisaoDevolucao);
		String dataFormatada = "'" + formatador.format(data) + "'";
		return dao.buscarPorPropriedade("WHERE e.dataPrevisaoDevolucao = ", dataFormatada);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Integer idEmprestimo) {
		try {
			Emprestimos emprestimo = dao.buscarPorId(idEmprestimo);
			return Response.ok(emprestimo).status(200).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao buscar empr�stimo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

	@GET
	@Path("/funcionario/{idFuncionario}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByIdFuncionario(@PathParam("idFuncionario") Integer idFuncionario) {
		return dao.buscarPorPropriedade("WHERE e.idFuncionario = ", idFuncionario);
	}

	@GET
	@Path("/livro/{idLivro}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByIdLivro(@PathParam("idLivro") Integer idLivro) {
		return dao.buscarPorLivro(idLivro);
	}

	@GET
	@Path("/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByIdUsuario(@PathParam("idUsuario") Integer idUsuario) {
		return dao.buscarPorPropriedade("WHERE e.idUsuario = ", idUsuario);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Emprestimos emprestimo) {
		try {
			Emprestimos emprestimoSalvo = dao.salvar(emprestimo);
			return Response.ok(emprestimoSalvo).status(201).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao salvar o empr�stimo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

}
