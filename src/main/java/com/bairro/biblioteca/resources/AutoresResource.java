package com.bairro.biblioteca.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bairro.biblioteca.daos.AutoresDAO;
import com.bairro.biblioteca.daos.LivrosDAO;
import com.bairro.biblioteca.entidades.Autores;

@Path("/autores")
public class AutoresResource {

	@Inject
	private AutoresDAO autoresDao;

	@Inject
	private LivrosDAO livrosDao;

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idAutor) {

		if (livrosDao.existeComPropriedade("WHERE l.idAutor = ", idAutor)) {
			return Response.ok("Este autor nao pode ser excluido!").build();
		}

		if (autoresDao.buscarPorId(idAutor) == null) {
			return Response.ok("Autor nao encontrado!").status(204).build();
		}

		autoresDao.deletar(idAutor);

		return Response.ok("Autor " + idAutor + " deletado com sucesso!").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Autores autor) {
		Autores autorSalvo = autoresDao.salvar(autor);
		return Response.ok(autorSalvo).status(201).build();
	}

}
