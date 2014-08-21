package br.com.gestor.DAOHibernate;

import java.util.List;

import org.hibernate.Query;

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
	
	public List<Responsavel> listarOrderByNome() {
		Query query = session.createQuery("from Responsavel a order by a.nome");
		return query.list();
	}
}
