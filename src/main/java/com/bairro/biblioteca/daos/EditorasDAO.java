package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Editoras;

@Stateless
public class EditorasDAO {

	@PersistenceContext(unitName="bibliotecaDS")
	private EntityManager manager;

	public List<Editoras> getAll(){
		return manager.createQuery("select e from Editoras e", Editoras.class).getResultList();
	}
	
	public Editoras salvar(Editoras editora){
		manager.persist(editora);
		return editora;
	}
	
	public Editoras buscarPorId(Integer idEditora) {
		return manager.find(Editoras.class, idEditora);
	}
	
	public void deletar(Integer idEditora) {
		Editoras editora = manager.find(Editoras.class, idEditora);
		if (editora != null) {
			manager.remove(editora);
		}
	}
}
