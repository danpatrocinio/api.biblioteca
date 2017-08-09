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
import com.bairro.biblioteca.enums.TipoDeCargo;
import com.bairro.biblioteca.exceptions.ModelException;

@Stateless
public class EmprestimosDAO {

	@Inject
	private CargosDAO cargosDao;

	@Inject
	private FuncionariosDAO funcionariosDao;

	@Inject
	private LivrosDAO livrosDao;

	@PersistenceContext(unitName = "bibliotecaDS")
	private EntityManager manager;

	@Inject
	private UsuariosDAO usuarioDao;

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

	/**
	 * O médoto buscar empréstimos por livro deve considerar as 3 possibilidades.
	 * 
	 * @param idLivro
	 * @return
	 */
	public List<Emprestimos> buscarPorLivro(Integer idLivro) {
		return manager.createQuery("select e from Emprestimos e WHERE e.idLivro1 = " + idLivro + " or e.idLivro2 = "
		        + idLivro + " or e.idLivro3 = " + idLivro, Emprestimos.class).getResultList();
	}

	public List<Emprestimos> buscarPorPropriedade(String whereClause, Object parametro) {
		//select e from Emprestimos e WHERE ?<campo que eu quero filtrar>? = ?<parametro>?
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

	public boolean existeComPropriedade(String whereClause, Object parametro) {
		if (quantidadeComPropriedade(whereClause, parametro) > 0) {
			return true;
		}
		return false;
	}

	public List<Emprestimos> getAll() {
		return manager.createQuery("select e from Emprestimos e", Emprestimos.class).getResultList();
	}

	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		Object count = manager.createQuery("select COUNT(e.idEmprestimo) from Emprestimos e " + whereClause + parametro)
		        .getSingleResult();
		return count != null ? (Long) count : 0;
	}

	public Emprestimos salvar(Emprestimos emprestimo) throws ModelException {

		validaFuncionarioInformado(emprestimo);

		validaLivrosInformados(emprestimo);

		validaUsuarioInformado(emprestimo);

		if (emprestimo.getDataEmprestimo() == null) {
			throw new ModelException("A data de empréstimo não foi informada!");
		}

		// Ajustar data de emprestimo;
		Calendar cal = Calendar.getInstance();
		cal.setTime(emprestimo.getDataEmprestimo());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);

		int prazoEmDias = calculoPrazoDevolucao(emprestimo);

		Date dateEmprestimoAjustada = new Date(cal.getTimeInMillis());
		emprestimo.setDataEmprestimo(dateEmprestimoAjustada); // para gravar corretamente esta sendo necessario somar + 1 ao dia.

		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1 + prazoEmDias);
		Date datePrevisaoDevolucao = new Date(cal.getTimeInMillis());
		emprestimo.setDataPrevisaoDevolucao(datePrevisaoDevolucao);

		manager.persist(emprestimo);
		return emprestimo;
	}

	/**
	 * Calculo do prazo para devolução
	 * 
	 * @param emprestimo
	 * @return
	 */
	private int calculoPrazoDevolucao(Emprestimos emprestimo) {
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
		return prazoEmDias;
	}

	/**
	 * Valida o funcionário informado, caso não seja válido ou n�o esteja informado dispara uma
	 * exception interrompendo o processo.
	 * 
	 * @param emprestimo
	 * @throws ModelException
	 */
	private void validaFuncionarioInformado(Emprestimos emprestimo) throws ModelException {
		if (emprestimo.getIdFuncionario() == null || funcionariosDao.getById(emprestimo.getIdFuncionario()) == null) {
			throw new ModelException("O funcionário não foi informado!");
		}
		Funcionarios funcionarioEmprestimo = funcionariosDao.getById(emprestimo.getIdFuncionario());
		Cargos cargo = cargosDao.buscarPorId(funcionarioEmprestimo.getIdCargo());
		if (!TipoDeCargo.DIRETOR.getLetra().equals(cargo.getTipo())) {
			if (!TipoDeCargo.BIBLIOTECARIO.getLetra().equals(cargo.getTipo())) {
				throw new ModelException("Este funcionário não esta autorizado a efetuar empréstimos!");
			}
		}
	}

	/**
	 * Valida os livros informados, caso não esteja informado pelo menos 01 ou seja informado um
	 * código de livro não cadastrado dispara uma exception interromendo o processo.
	 * 
	 * @param emprestimo
	 * @throws ModelException
	 */
	private void validaLivrosInformados(Emprestimos emprestimo) throws ModelException {
		if (emprestimo.getIdLivro1() == null && emprestimo.getIdLivro2() == null && emprestimo.getIdLivro3() == null) {
			throw new ModelException("Nenhum livro foi infomardo!");
		}
		if (emprestimo.getIdLivro1() != null) {
			livrosDao.buscarPorId(emprestimo.getIdLivro1());
		}
		if (emprestimo.getIdLivro2() != null) {
			livrosDao.buscarPorId(emprestimo.getIdLivro2());
		}
		if (emprestimo.getIdLivro3() != null) {
			livrosDao.buscarPorId(emprestimo.getIdLivro3());
		}
	}

	/**
	 * Valida se o usuario foi informado e se existe com o código de usuário informado.
	 * 
	 * @param emprestimo
	 * @throws ModelException
	 */
	private void validaUsuarioInformado(Emprestimos emprestimo) throws ModelException {
		if (emprestimo.getIdUsuario() == null) {
			throw new ModelException("O usuário não foi informado!");
		}
		usuarioDao.buscarPorId(emprestimo.getIdUsuario()); // caso não encontre uma exception será disparada pelo método buscarPorId de usuarioDao.
	}
}
