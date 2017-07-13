package com.bairro.biblioteca.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bairro.biblioteca.daos.FuncionariosDAO;
import com.bairro.biblioteca.entidades.Funcionarios;

@Path("/funcionarios")
public class FuncionariosResource {

	@Inject
	private FuncionariosDAO dao;
	
	@GET
	@Path("/cargo/{id_cargo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionarios> getByCargo(@PathParam("id_cargo") Integer idCargo) {
		return dao.buscarPorPropriedade("WHERE f.idCargo = ", idCargo);
	}
	
}
