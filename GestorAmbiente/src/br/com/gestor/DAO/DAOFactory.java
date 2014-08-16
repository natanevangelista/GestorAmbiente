package br.com.gestor.DAO;

import br.com.gestor.DAOHibernate.AgendamentoDAOHibernate;
import br.com.gestor.DAOHibernate.AmbienteDAOHibernate;
import br.com.gestor.DAOHibernate.CategoriaDAOHibernate;
import br.com.gestor.DAOHibernate.EventoDAOHibernate;
import br.com.gestor.DAOHibernate.ResponsavelDAOHibernate;
import br.com.gestor.DAOHibernate.TurmaDAOHibernate;
import br.com.gestor.util.HibernateUtil;
/**
 * Construtor de DAOs, é aonde instaciamos as classes DAO
 * @author Natan
 *
 */
public class DAOFactory {

	public static GenericDAO<?> criarAmbienteDAO(){
		AmbienteDAOHibernate ambienteDAO = new AmbienteDAOHibernate();
		ambienteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return ambienteDAO;
	}
	
	public static GenericDAO<?> criarCategoriaDAO(){
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}
	
	public static GenericDAO<?> criarEventoDAO(){
		EventoDAOHibernate eventoDAO = new EventoDAOHibernate();
		eventoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return eventoDAO;
	}
	
	public static GenericDAO<?> criarResponsavelDAO(){
		ResponsavelDAOHibernate responsavelDAO = new ResponsavelDAOHibernate();
		responsavelDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return responsavelDAO;
	}
	
	public static GenericDAO<?> criarAgendamentoDAO(){
		AgendamentoDAOHibernate agendamentoDAO = new AgendamentoDAOHibernate();
		agendamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return agendamentoDAO;
	}
	
	public static GenericDAO<?> criarTurmaDAO(){
		TurmaDAOHibernate turmaDAO = new TurmaDAOHibernate();
		turmaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return turmaDAO;
	}
}
