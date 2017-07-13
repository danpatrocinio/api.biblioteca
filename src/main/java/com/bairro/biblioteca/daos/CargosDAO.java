package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Cargos;

@Stateless
public class CargosDAO {

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public Cargos buscarPorId(Integer idCargo) {
		return manager.find(Cargos.class, idCargo);
	}

	public void deletar(Integer idCargo) {
		Cargos cargo = manager.find(Cargos.class, idCargo);
		if (cargo != null) {
			manager.remove(cargo);
		}
	}

	public List<Cargos> getAll() {
		return manager.createQuery("select c from Cargos c", Cargos.class).getResultList();
	}

	public Cargos salvar(Cargos cargo) {
		manager.persist(cargo);
		return cargo;
	}
}
