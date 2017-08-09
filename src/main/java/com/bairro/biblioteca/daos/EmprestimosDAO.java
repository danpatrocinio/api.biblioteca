package com.bairro.biblioteca.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bairro.biblioteca.entidades.Cargos;
import com.bairro.biblioteca.entidades.Emprestimos;
import com.bairro.biblioteca.entidades.Funcionarios;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class EmprestimosDAO {

	@Inject
	private CargosDAO cargosDao;

	@Inject
	private FuncionariosDAO funcionariosDao;

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	public Emprestimos atualizar(Emprestimos emprestimo) throws ModelException {
		buscarPorId(emprestimo.getIdEmprestimo()); // somente para validar se exite no banco, caso não exista dispara uma exception

		manager.merge(emprestimo);
		return emprestimo;
	}

	public Emprestimos buscarPorId(Integer idEmprestimo) throws ModelException {
		Emprestimos emprestimo = manager.find(Emprestimos.class, idEmprestimo);
		if (emprestimo == null) {
			throw new ModelException("Empréstimo não encontrado!");
		}
		return emprestimo;
	}

	public List<Emprestimos> buscarPorLivro(Integer idLivro) {
		return manager.createQuery("select e from Emprestimos e WHERE e.idLivro1 = " + idLivro + " or e.idLivro2 = "
		        + idLivro + " or e.idLivro3 = " + idLivro, Emprestimos.class).getResultList();
	}

	public List<Emprestimos> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select e from Emprestimos e " + whereClause + parametro, Emprestimos.class)
		        .getResultList();
	}

	public void deletar(Integer idEmprestimo) throws ModelException {
		Emprestimos emprestimo = buscarPorId(idEmprestimo);

		Funcionarios funcionarioEmprestimo = funcionariosDao.getById(emprestimo.getIdFuncionario());
		Cargos cargoFuncionarioEmprestimo = cargosDao.buscarPorId(funcionarioEmprestimo.getIdCargo());
		if (!"D".equals(cargoFuncionarioEmprestimo.getTipo())) {
			throw new ModelException("Este funcionário não esta autorizado a deletar um empréstimo!");
		}

		manager.remove(emprestimo);
	}

	public List<Emprestimos> getAll() {
		return manager.createQuery("select e from Emprestimos e", Emprestimos.class).getResultList();
	}

	public Emprestimos salvar(Emprestimos emprestimo) throws ModelException {
		if (emprestimo.getIdFuncionario() == null) {
			throw new ModelException("O funcionário não foi informado!");
		}
		Funcionarios funcionarioEmprestimo = funcionariosDao.getById(emprestimo.getIdFuncionario());
		Cargos cargo = cargosDao.buscarPorId(funcionarioEmprestimo.getIdCargo());
		if (!"DB".contains(cargo.getTipo())) {
			throw new ModelException("Este funcionário não esta autorizado a efetuar empréstimos!");
		}
		if (emprestimo.getIdLivro1() == null && emprestimo.getIdLivro2() == null && emprestimo.getIdLivro3() == null) {
			throw new ModelException("Nenhum livro foi infomardo!");
		}
		if (emprestimo.getDataEmprestimo() == null) {
			throw new ModelException("A data de empréstimo não foi infomarda!");
		}
		// Calculo do prazo para devolução
		int prazoEmDias = 0;
		if (emprestimo.getIdLivro1() != null) {
			prazoEmDias = prazoEmDias + 10;
		}
		if (emprestimo.getIdLivro2() != null) {
			prazoEmDias = prazoEmDias + 10;
		}
		if (emprestimo.getIdLivro3() != null) {
			prazoEmDias = prazoEmDias + 10;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(emprestimo.getDataEmprestimo());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		Date dateEmprestimoAjustada = new Date(cal.getTimeInMillis());
		emprestimo.setDataEmprestimo(dateEmprestimoAjustada); // para gravar corretamente esta sendo necessario somar + 1 ao dia.

		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1 + prazoEmDias);
		Date datePrevisaoDevolucao = new Date(cal.getTimeInMillis());
		emprestimo.setDataPrevisaoDevolucao(datePrevisaoDevolucao);

		manager.persist(emprestimo);
		return emprestimo;
	}
}