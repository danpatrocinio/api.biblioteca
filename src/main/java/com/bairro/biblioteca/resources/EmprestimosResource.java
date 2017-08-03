package com.bairro.biblioteca.resources;

import java.text.SimpleDateFormat;
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
import javax.ws.rs.core.Response.Status;

import com.bairro.biblioteca.daos.EmprestimosDAO;
import com.bairro.biblioteca.entidades.Emprestimos;
import com.bairro.biblioteca.exceptions.ModelException;
import com.bairro.biblioteca.utils.MensagemWrapper;

@Path("/emprestimos")
public class EmprestimosResource {

	/*
	 * 8.1. Permitir inserir, atualizar, deletar e buscar um ou listagem de
	 * empréstimos. 
	 * 8.2. Disponibilizar listagem de empréstimos por livro. 
	 * 8.3. Disponibilizar listagem de empréstimos por usuário. 
	 * 8.4. Disponibilizar listagem de empréstimos por funcionário. 
	 * 
	 * 8.6. Disponibilizar listagem de empréstimos por data de previsão de devolução. 
	 * 8.7. Disponibilizar listagem de empréstimos por data de devolução. 
	 * 8.8. Disponibilizar listagem de empréstimos com atraso da previsão de devolução. 
	 * 
	 * 8.9. Só poderá cadastrar um empréstimo funcionários com o cargo de bibliotecário ou diretor. 
	 * 8.10. Só poderá deletar um empréstimo funcionários com o
	 * cargo de diretor. 
	 * 8.11. Ao cadastrar um empréstimo é obrigatório informar: usuário, funcionário, no mínimo 01 e no máximo 03 livros; data
	 * de empréstimo. 
	 * 8.12. Ao cadastrar um empréstimo a API deve calcular a
	 * data de previsão de devolução do empréstimo com 10 dias para cada livro.
	 * (ex. Ao emprestar dois livros 2*10=20 dias de prazo para devolver os
	 * dois). 
	 * 8.13. Permitir atualizar o empréstimo inserindo a informação da
	 * data de devolução do(s) livro(s) do empréstimo. 
	 * 8.14. Ao informar a data
	 * de devolução em atraso em relação a data de previsão da devolução a API
	 * deve calcular uma multa pelo atraso de 5 reais + 2 reais por dia de
	 * atraso.
	 */

	@Inject
	private EmprestimosDAO dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getAll() {
		return dao.getAll();
	}

	@GET
	@Path("/data_emprestimo/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataEmprestimo(@PathParam("dataLong") Long dataEmprestimo) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataEmp = new Date(dataEmprestimo);
		String dataFormatada = "'" + formatador.format(dataEmp) + "'";
		return dao.buscarPorPriedade("WHERE e.dataEmprestimo = ", dataFormatada);
	}
	
	@GET
	@Path("/data_devolucao/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprestimos> getByDataDevolucao(@PathParam("dataLong") Long dataDev) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataDevolucao = new Date(dataDev);
		String dataFormatada = "'" + formatador.format(dataDevolucao) + "'";
		return dao.buscarPorPriedade("WHERE e.dataDevolucao = ", dataFormatada);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Emprestimos getById(@PathParam("id") Integer idEmprestimo) {
		return dao.findById(idEmprestimo);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Emprestimos emprestimo) {
		try {
			Emprestimos emprestimoSalvo = dao.salvar(emprestimo);
			return Response.ok(emprestimoSalvo).status(Status.CREATED).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("O emprestímo não pode ser salvo! " + e.getMessage());
			return Response.ok(mensagem).status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Emprestimos emprestimo) {
		Emprestimos emprestimoAtualizado = dao.atualizar(emprestimo);
		return Response.ok(emprestimoAtualizado).status(Status.OK).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletar(@PathParam("id") Integer idEmprestimo) {
		dao.deletar(idEmprestimo);
		return Response.ok("Genero " + idEmprestimo + "deletado com sucesso!").status(200).build();
	}

}
