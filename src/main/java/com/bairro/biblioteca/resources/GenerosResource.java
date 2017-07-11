package com.bairro.biblioteca.resources;

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

import com.bairro.biblioteca.daos.GenerosDAO;
import com.bairro.biblioteca.entidades.Generos;

@Path("/generos")
public class GenerosResource {

	@Inject
	private GenerosDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Generos> getAll(){
		return dao.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Generos getById(@PathParam("id") Integer idGenero){
		return dao.findById(idGenero);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Generos genero) {
		Generos generoSalvo = dao.salvar(genero);
		return Response.ok(generoSalvo).status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Generos genero) {
		Generos generoAtualizado = dao.atualizar(genero);
		return Response.ok(generoAtualizado).status(202).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletar(@PathParam("id") Integer idGenero) {
		dao.deletar(idGenero);
		return Response.ok("Genero " + idGenero
				+ "deletado com sucesso!").status(200).build();
	}
	
}


















