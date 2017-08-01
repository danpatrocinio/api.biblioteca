package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Funcionarios;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class FuncionariosDAO {

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public List<Funcionarios> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select f from Funcionarios f " + whereClause + parametro, Funcionarios.class)
		        .getResultList();
	}

	public void deletar(Integer idFuncionario) throws ModelException {
		Funcionarios funcionario = getById(idFuncionario);
		if (funcionario != null) {
			manager.remove(funcionario);
		} else {
			throw new ModelException("Funcionário não encontrado!");
		}
	}

	public List<Funcionarios> getAll() {
		return manager.createQuery("select f from Funcionarios f", Funcionarios.class).getResultList();
	}

	public Funcionarios getById(Integer idFuncionario) {
		return manager.find(Funcionarios.class, idFuncionario);
	}

	public Funcionarios salvar(Funcionarios funcionario) {
		manager.persist(funcionario);
		return funcionario;
	}

}
