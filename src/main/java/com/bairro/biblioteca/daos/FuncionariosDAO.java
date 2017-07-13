package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Funcionarios;

@Stateless
public class FuncionariosDAO {

	@PersistenceContext(unitName="bibliotecaDS")
	private EntityManager manager;
	
	public Funcionarios getById(Integer idFuncionario) {
		return manager.find(Funcionarios.class, idFuncionario);
	}
	
	public List<Funcionarios> getAll(){
		return manager
				.createQuery("select f from Funcionarios f", Funcionarios.class)
				.getResultList();
	}
	
	public void deletar(Integer idFuncionario) {
		Funcionarios funcionario = getById(idFuncionario);
		if (funcionario != null) {
			manager.remove(funcionario);
		}
	}
	
	public List<Funcionarios> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager
				.createQuery("select f from Funcionarios f " + whereClause + parametro
					,Funcionarios.class)
				.getResultList();
	}
	

	
}
