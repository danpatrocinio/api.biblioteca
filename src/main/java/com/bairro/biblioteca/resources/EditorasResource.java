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

import com.bairro.biblioteca.daos.EditorasDAO;
import com.bairro.biblioteca.entidades.Editoras;

@Path("/editoras")
public class EditorasResource {

	@Inject
	private EditorasDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Editoras> getAll(){
		return dao.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Editoras getById(@PathParam("id") Integer idGenero){
		return dao.buscarPorId(idGenero);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Editoras editora) {
		Editoras editoraSalva = dao.salvar(editora);
		return Response.ok(editoraSalva).status(201).build();
	}

	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletar(@PathParam("id") Integer idEditora) {
		dao.deletar(idEditora);
		return Response.ok("Editora " + idEditora + "deletada com sucesso!").status(200).build();
	}
	
}
