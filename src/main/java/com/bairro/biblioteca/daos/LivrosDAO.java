package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Livros;

@Stateless
public class LivrosDAO {

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public Livros salvar(Livros livro) {
		manager.persist(livro);
		return livro;
	}

	public Livros atualizar(Livros livro) {
		manager.merge(livro);
		return livro;
	}

	public List<Livros> buscarTodos() {
		return manager.createQuery("select l from Livros l", Livros.class).getResultList();
	}

	public Livros buscarPorId(Integer idLivro) {
		return manager.find(Livros.class, idLivro);
	}

	public void deletar(Integer idLivro) {
		Livros livro = manager.find(Livros.class, idLivro);
		if (livro != null) {
			manager.remove(livro);
		}
	}
	
	public List<Livros> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select l from Livros l " + whereClause + parametro, Livros.class).getResultList();
	}
	
}
