package br.com.gestor.RN;

import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.EventoDAOHibernate;
import br.com.gestor.entidade.Evento;
import br.com.gestor.web.util.DAOException;
import br.com.gestor.web.util.RNException;

public class EventoRN {

	private EventoDAOHibernate eventoDAO;
	
	public EventoRN() {
		this.eventoDAO = (EventoDAOHibernate) DAOFactory.criarEventoDAO();
	}
	
	public void salvar(Evento evento) throws DAOException{
		Long id = evento.getId();
		if(id == null || id == 0){
			this.eventoDAO.salvar(evento);
		} else {
			this.eventoDAO.atualizar(evento);
		}
	}
	
	public void excluir(Evento evento) throws RNException{
			this.eventoDAO.excluir(evento);
	}
	
	public List<Evento> listar(){
		return (List<Evento>) this.eventoDAO.listar();
	}
	
	public Evento carregar(Long evento){
		return (Evento) this.eventoDAO.carregar(evento);
	}
	
	public List<Evento> listarOrderByNome(){
		return (List<Evento>) this.eventoDAO.listarOrderByNome();
	}
}
