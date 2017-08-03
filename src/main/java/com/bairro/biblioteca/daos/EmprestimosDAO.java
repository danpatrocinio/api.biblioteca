package com.bairro.biblioteca.daos;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Emprestimos;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class EmprestimosDAO {

	@PersistenceContext(unitName="bibliotecaDS")
	private EntityManager manager;
	
	@Inject
	private UsuariosDAO usuarioDao;
	
	@Inject
	private FuncionariosDAO funcionariosDao;
	
	@Inject
	private LivrosDAO livrosDao;
	
	public List<Emprestimos> getAll(){
		return manager.createQuery("select e from Emprestimos e", Emprestimos.class).getResultList();
	}
	
	public List<Emprestimos> buscarPorPriedade(String where, Object parametro){
		//select e from Emprestimos e WHERE ?<campo que eu quero filtrar>? = ?<parametro>?
		return manager.createQuery("select e from Emprestimos e " 
		+ where + parametro,Emprestimos.class).getResultList();
	}
	
	
	public Emprestimos salvar(Emprestimos emprestimo) throws ModelException {
		
		if (emprestimo.getIdUsuario() == null 
				|| usuarioDao.buscarPorId(emprestimo.getIdUsuario()) == null) {
			throw new ModelException("O usuário não foi informado!");
		}
		if (emprestimo.getIdFuncionario() == null
				|| funcionariosDao.getById(emprestimo.getIdFuncionario()) == null) {
			throw new ModelException("O funcionário não foi informado!");
		}
		// Valida se pelo menos 1 livro foi informado
		if (emprestimo.getIdLivro1() == null 
			&& emprestimo.getIdLivro2() == null
			&& emprestimo.getIdLivro3() == null) {
			throw new ModelException("Nenhum livro foi informado!");
		}
		// Valida se está cadastrado no banco e calcula o prazo
		int prazo = 0;
		if (emprestimo.getIdLivro1() != null) {
			prazo = prazo + 10;
			livrosDao.buscarPorId(emprestimo.getIdLivro1());
		}
		if (emprestimo.getIdLivro2() != null) {
			prazo = prazo + 10;
			livrosDao.buscarPorId(emprestimo.getIdLivro2());
		}
		if (emprestimo.getIdLivro3() != null) {
			prazo = prazo + 10;
			livrosDao.buscarPorId(emprestimo.getIdLivro3());
		}
		if (emprestimo.getDataEmprestimo() == null) {
			throw new ModelException("A data de empréstimo não foi informada!");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(emprestimo.getDataEmprestimo());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		emprestimo.setDataEmprestimo(cal.getTime());
		
		cal.clear();
		cal.setTime(emprestimo.getDataEmprestimo());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + prazo);
		emprestimo.setDataPrevisaoDevolucao(cal.getTime());
		
		manager.persist(emprestimo);
		return emprestimo;
	}
	
	public Emprestimos atualizar(Emprestimos emprestimo){
		manager.merge(emprestimo);
		return emprestimo;
	}
	
	public void deletar(Integer idEmprestimo) {
		Emprestimos emprestimo = manager.find(Emprestimos.class, idEmprestimo);

		if (emprestimo != null) {			
			manager.remove(emprestimo);
		}
	}
	
	public Emprestimos findById(Integer idEmprestimo) {
		return manager.find(Emprestimos.class, idEmprestimo);
	}
	
}







