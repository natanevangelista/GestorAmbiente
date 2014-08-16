package br.com.gestor.DAOHibernate;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Turma;

public class TurmaDAOHibernate extends GenericDAO<Turma>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurmaDAOHibernate() {
		super(Turma.class);
	}
}
