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

import com.bairro.biblioteca.daos.UsuariosDAO;
import com.bairro.biblioteca.entidades.Usuarios;

@Path("/usuarios")
public class UsuariosResource {

	@Inject
	private UsuariosDAO usuariosDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Usuarios usuario) {
		Usuarios autorSalvo = usuariosDao.salvar(usuario);
		return Response.ok(autorSalvo).status(201).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(usuariosDao.getAll()).status(Status.OK).build();
	}
}
