package com.bairro.biblioteca.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bairro.biblioteca.daos.CargosDAO;
import com.bairro.biblioteca.entidades.Cargos;
import com.bairro.biblioteca.exceptions.ModelException;
import com.bairro.biblioteca.utils.MensagemWrapper;

@Path("/cargos")
public class CargosResource {

	@Inject
	private CargosDAO cargosDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Cargos cargo) {
		
		try {
			Cargos cargoSalvo = cargosDao.salvar(cargo);
			return Response.ok(cargoSalvo).status(201).build();
		} catch (ModelException e) {
			MensagemWrapper mensagem = new MensagemWrapper("Erro ao salvar o cargo! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(cargosDao.getAll()).status(Status.OK).build();
	}
}
