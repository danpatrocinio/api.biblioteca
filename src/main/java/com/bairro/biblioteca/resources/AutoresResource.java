package com.bairro.biblioteca.resources;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.bairro.biblioteca.daos.AutoresDAO;
import com.bairro.biblioteca.daos.LivrosDAO;

@Path("/autores")
public class AutoresResource {

	@Inject
	private LivrosDAO livrosDao;
	
	@Inject
	private AutoresDAO autoresDao;
	
	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idAutor) {
		
		if (livrosDao.existeComPropriedade("WHERE l.idAutores = ", idAutor)) {
			return Response.ok("Este autor não pode ser excluído!").build();
		}
		
		if (autoresDao.buscarPorId(idAutor) == null) {
			return Response.ok("Autor não encontrado!").status(204).build();
		}
		
		autoresDao.deletar(idAutor);
		
		return Response.ok("Autor " + idAutor + " deletado com sucesso!").build();
	}
	
}
