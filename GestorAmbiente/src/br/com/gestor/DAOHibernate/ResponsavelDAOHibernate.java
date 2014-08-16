package br.com.gestor.DAOHibernate;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Responsavel;

public class ResponsavelDAOHibernate extends GenericDAO<Responsavel> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponsavelDAOHibernate() {
		super(Responsavel.class);
	}
}
