package com.bairro.biblioteca.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Usuarios;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class UsuariosDAO {

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public Usuarios buscarPorId(Integer idUsuario) throws ModelException {
		Usuarios usuario = manager.find(Usuarios.class, idUsuario);
		if (usuario == null) {
			throw new ModelException("Usuário não encontrado!");
		}
		return usuario;
	}

	public Usuarios salvar(Usuarios usuario) {
		manager.persist(usuario);
		return usuario;
	}
	
	public List<Usuarios> getAll(){
		return manager.createQuery("select u from Usuarios u", Usuarios.class).getResultList();
	}
}
