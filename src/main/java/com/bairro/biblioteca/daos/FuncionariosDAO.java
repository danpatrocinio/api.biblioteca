package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Funcionarios;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class FuncionariosDAO {

	@Inject
	private EmprestimosDAO emprestimosDao;

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public List<Funcionarios> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select f from Funcionarios f " + whereClause + parametro, Funcionarios.class)
		        .getResultList();
	}

	public void deletar(Integer idFuncionario) throws ModelException {
		Funcionarios funcionario = getById(idFuncionario);
		if (emprestimosDao.existeComPropriedade("WHERE e.idFuncionario = ", idFuncionario)) {
			throw new ModelException("Existe empréstimos com este funcionário!");
		}

		if (funcionario != null) {
			manager.remove(funcionario);
		} else {
			throw new ModelException("Funcionário não encontrado!");
		}
	}

	public List<Funcionarios> getAll() {
		return manager.createQuery("select f from Funcionarios f", Funcionarios.class).getResultList();
	}

	public Funcionarios getById(Integer idFuncionario) throws ModelException {
		Funcionarios funcionario = manager.find(Funcionarios.class, idFuncionario);
		if (funcionario == null) {
			throw new ModelException("Funcionário não encontrado!");
		}
		return funcionario;
	}

	public Funcionarios salvar(Funcionarios funcionario) {
		manager.persist(funcionario);
		return funcionario;
	}

}
