package com.bairro.biblioteca.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bairro.biblioteca.daos.FuncionariosDAO;
import com.bairro.biblioteca.entidades.Funcionarios;
import com.bairro.biblioteca.exceptions.ModelException;

@Path("/funcionarios")
public class FuncionariosResource {

	@Inject
	private FuncionariosDAO dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionarios> getAll(){
		return dao.getAll();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idFuncionario) {
		try {
			dao.deletar(idFuncionario);
			return Response.ok("Funcion�rio deletado com sucesso!").build();
		} catch (ModelException e) {
			return Response.ok("Erro ao deletar funcion�rio: " + e.getMessage()).build();
		}
	}

	@GET
	@Path("/cargo/{id_cargo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionarios> getByCargo(@PathParam("id_cargo") Integer idCargo) {
		return dao.buscarPorPropriedade("WHERE f.idCargo = ", idCargo);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Funcionarios funcionario) {
		Funcionarios funcionarioSalvo = dao.salvar(funcionario);
		return Response.ok(funcionarioSalvo).status(201).build();
	}

}
