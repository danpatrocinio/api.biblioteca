package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Livros;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class LivrosDAO {

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public Livros atualizar(Livros livro) {
		manager.merge(livro);
		return livro;
	}

	public Livros buscarPorId(Integer idLivro) throws ModelException {
		Livros livro = manager.find(Livros.class, idLivro);
		if (livro == null) {
			throw new ModelException("Livro " + idLivro + " não encontrado!");
		}
		return livro;
	}

	public List<Livros> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select l from Livros l " + whereClause + parametro, Livros.class).getResultList();
	}

	public List<Livros> buscarTodos() {
		return manager.createQuery("select l from Livros l", Livros.class).getResultList();
	}

	public void deletar(Integer idLivro) throws ModelException {
		Livros livro = buscarPorId(idLivro);
		if (livro != null) {
			manager.remove(livro);
		}
	}

	public boolean existeComPropriedade(String whereClause, Object parametro) {
		if (quantidadeComPropriedade(whereClause, parametro) > 0) {
			return true;
		}
		return false;
	}

	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		Object count = manager.createQuery("select COUNT(l.idLivro) from Livros l " + whereClause + parametro)
		        .getSingleResult();
		return count != null ? (Long) count : 0;
	}

	public Livros salvar(Livros livro) {
		manager.persist(livro);
		return livro;
	}

}
