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

	public Livros atualizar(Livros livro) {
		manager.merge(livro);
		return livro;
	}

	public Livros buscarPorId(Integer idLivro) {
		return manager.find(Livros.class, idLivro);
	}

	public List<Livros> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select l from Livros l " + whereClause + parametro, Livros.class).getResultList();
	}

	public List<Livros> buscarTodos() {
		return manager.createQuery("select l from Livros l", Livros.class).getResultList();
	}

	public void deletar(Integer idLivro) {
		Livros livro = manager.find(Livros.class, idLivro);
		if (livro != null) {
			manager.remove(livro);
		}
	}

	public boolean existeComPropriedade(String whereClause, Object parametro) {
		Livros livroEncontrado = manager.createQuery("select l from Livros l " + whereClause + parametro, Livros.class)
		        .getSingleResult();
		if (livroEncontrado != null) {
			return true;
		}
		return false;
	}

	public Livros salvar(Livros livro) {
		manager.persist(livro);
		return livro;
	}

}
