package br.com.gestor.DAOHibernate;

import java.util.List;

import org.hibernate.Query;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Evento;

public class EventoDAOHibernate extends GenericDAO<Evento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EventoDAOHibernate() {
		super(Evento.class);
	}

	public List<Evento> listarOrderByNome() {
		Query query = session.createQuery("from Evento e order by e.nome");
		return query.list();
	}
}

