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
import com.bairro.biblioteca.exceptions.ModelException;
import com.bairro.biblioteca.utils.MensagemWrapper;

@Path("/livros")
public class LivrosResources {

	@Inject
	private LivrosDAO dao;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livros atualizar(Livros livro) {
		return dao.atualizar(livro);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Integer idLivro) {
		try {
			Livros livro = dao.buscarPorId(idLivro);
			return Response.ok(livro).build();
		} catch (ModelException e) {
			MensagemWrapper resultado = new MensagemWrapper(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> buscarTodos() {
		return dao.buscarTodos();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idLivro) {
		try {
			dao.deletar(idLivro);
			return Response.ok("Livro removido com sucesso!").build();
		} catch (ModelException e) {
			MensagemWrapper resultado = new MensagemWrapper(e.getMessage());
			return Response.ok(resultado).status(404).build();
		}
	}

	@GET
	@Path("/autor/{idAutor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByAutor(@PathParam("idAutor") Integer idAutor) {
		return dao.buscarPorPropriedade("WHERE l.idAutor = ", idAutor);
	}

	@GET
	@Path("/editora/{idEditora}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByEditora(@PathParam("idEditora") Integer idEditora) {
		return dao.buscarPorPropriedade("WHERE l.idEditora = ", idEditora);
	}

	@GET
	@Path("/genero/{idGenero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livros> findByGenero(@PathParam("idGenero") Integer idGenero) {
		return dao.buscarPorPropriedade("WHERE l.idGenero = ", idGenero);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livros salvar(Livros livro) {
		return dao.salvar(livro);
	}

}
