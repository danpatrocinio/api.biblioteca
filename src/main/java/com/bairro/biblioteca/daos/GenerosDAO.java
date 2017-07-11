package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Generos;

@Stateless
public class GenerosDAO {

	@PersistenceContext(unitName="bibliotecaDS")
	private EntityManager manager;
	
	public List<Generos> getAll(){
		return manager.createQuery("select g from Generos g", Generos.class).getResultList();
	}
	
	public Generos salvar(Generos genero){
		manager.persist(genero);
		return genero;
	}
	
	public Generos atualizar(Generos genero){
		manager.merge(genero);
		return genero;
	}

	public void deletarPorDescricao(String descricao) {
		Generos genero =  manager
				.createQuery("select g from Generos g where descricao = '" + descricao + "'", Generos.class)
				.getSingleResult();
		if (genero != null) {			
			manager.remove(genero);
		}
	}
	
	public void deletar(Integer idGenero) {
		Generos genero = manager.find(Generos.class, idGenero);

		if (genero != null) {			
			manager.remove(genero);
		}
	}
	
	public Generos findById(Integer idGenero) {
		return manager.find(Generos.class, idGenero);
	}
	
}







