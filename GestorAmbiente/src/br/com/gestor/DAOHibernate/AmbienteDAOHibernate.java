package br.com.gestor.DAOHibernate;

import java.util.List;

import org.hibernate.Query;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Ambiente;

/**
 * classe que persiste no banco
 * @author Natan
 *
 */
public class AmbienteDAOHibernate extends GenericDAO<Ambiente> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmbienteDAOHibernate() {
		super(Ambiente.class);
	}

	public List<Ambiente> listarOrderByIdentificao() {
		Query query = session.createQuery("from Ambiente a order by a.identificacao");
		return query.list();
	}

}
