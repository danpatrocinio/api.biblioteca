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

	/**
	 * @param whereClause
	 *            - WHERE <coluna> = <parametro>
	 * @param parametro
	 * @return Retorna TRUE caso exista mais que 0 (zero) registros encontrados de acordo com a
	 *         cláusula e o parâmetro passado nos argumentos "whereClause" e "parametro"
	 *         respectivamente. Utiliza sempre a primeira letra do nome da tabela como alias.
	 */
	public boolean existeComPropriedade(String whereClause, Object parametro) {
		if (quantidadeComPropriedade(whereClause, parametro) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param whereClause
	 *            - WHERE <coluna> = <parametro>
	 * @param parametro
	 * @return Retorna a quantidade de registros encontrados de acordo com a cláusula e o parâmetro
	 *         passado nos argumentos "whereClause" e "parametro" respectivamente. Caso não encontre
	 *         nenhum registro retorna 0 (zero). Utiliza sempre a primeira letra do nome da tabela
	 *         como alias.
	 */
	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		return (Long) manager.createQuery("select COUNT(l.idLivro) from Livros l " + whereClause + parametro)
		        .getSingleResult();
	}

	public Livros salvar(Livros livro) {
		manager.persist(livro);
		return livro;
	}

}
