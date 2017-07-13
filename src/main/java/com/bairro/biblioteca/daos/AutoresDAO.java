package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Autores;

@Stateless
public class AutoresDAO {

	@PersistenceContext(unitName="bibliotecaDS")
	private EntityManager manager;

	public List<Autores> getAll(){
		return manager.createQuery("select a from Autores a", Autores.class).getResultList();
	}
	
	public Autores salvar(Autores autor){
		manager.persist(autor);
		return autor;
	}
	
	public Autores buscarPorId(Integer idAutor) {
		return manager.find(Autores.class, idAutor);
	}
	
	public void deletar(Integer idAutor) {
		Autores autor = manager.find(Autores.class, idAutor);
		if (autor != null) {
			manager.remove(autor);
		}
	}
}
