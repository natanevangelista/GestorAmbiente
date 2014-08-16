package br.com.gestor.RN;

import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.TurmaDAOHibernate;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.RNException;

public class TurmaRN {

	private TurmaDAOHibernate turmaDAO;
	
	public TurmaRN() {
		this.turmaDAO = (TurmaDAOHibernate) DAOFactory.criarTurmaDAO();
	}
	
	public void salvar(Turma turma){
		Long id = turma.getId();
		if(id == null || id == 0){
			this.turmaDAO.salvar(turma);
		} else {
			this.turmaDAO.atualizar(turma);
		}
	}
	
	public void excluir(Turma turma) throws RNException{
		this.turmaDAO.excluir(turma);
	}
	
	public List<Turma> listar(){
		return (List<Turma>) this.turmaDAO.listar();
	}
	
}
