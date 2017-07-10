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

import com.bairro.biblioteca.daos.LivrosDAO;
import com.bairro.biblioteca.entidades.Livros;

@Path("/livros")
public class LivrosResources {

	@Inject
	private LivrosDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> buscarTodos() {
		return dao.buscarTodos();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Livros buscarPorId(@PathParam("id") Integer idLivro) {
		return dao.buscarPorId(idLivro);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livros atualizar(Livros livro) {
		return dao.atualizar(livro);
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idLivro) {
		dao.deletar(idLivro);
		return Response.ok("Livro removido com sucesso!").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livros salvar(Livros livro) {
		return dao.salvar(livro);
	}

	@GET
	@Path("/autor/{idAutor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByAutor(@PathParam("idAutor") Integer idAutor) {
		return dao.buscarPorPropriedade("WHERE l.idAutor = ", idAutor);
	}

	@GET
	@Path("/genero/{idGenero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByGenero(@PathParam("idGenero") Integer idGenero) {
		return dao.buscarPorPropriedade("WHERE l.idGenero = ", idGenero);
	}

	@GET
	@Path("/editora/{idEditora}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByEditora(@PathParam("idEditora") Integer idEditora) {
		return dao.buscarPorPropriedade("WHERE l.idEditora = ", idEditora);
	}

}
