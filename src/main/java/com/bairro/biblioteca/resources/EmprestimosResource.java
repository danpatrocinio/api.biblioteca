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
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao atualizar o empréstimo! " + e.getMessage());
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
			MensagemWrapper mensagem = new MensagemWrapper("Não foi possível deletar o empréstimo! " + e.getMessage());
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
	@Path("/data_devolucao/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataDevolucao(@PathParam("dataLong") Long dataDev) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataDevolucao = new Date(dataDev);
		String dataFormatada = "'" + formatador.format(dataDevolucao) + "'";
		return dao.buscarPorPropriedade("WHERE e.dataDevolucao = ", dataFormatada);
	}

	@Path("/data_emprestimo/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataEmprestimo(@PathParam("dataLong") Long dataEmprestimo) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataEmp = new Date(dataEmprestimo);
		String dataFormatada = "'" + formatador.format(dataEmp) + "'";
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
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao buscar empréstimo! " + e.getMessage());
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
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao salvar o empréstimo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

}
