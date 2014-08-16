package br.com.gestor.DAOHibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Agendamento;
import br.com.gestor.entidade.Ambiente;

public class AgendamentoDAOHibernate extends GenericDAO<Agendamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgendamentoDAOHibernate() {
		super(Agendamento.class);
	}
	
	/**
	 * Realiza o Registro de um agendamento no Banco
	 */
	public Agendamento salvarAgendamento(Agendamento agendamento) {
		Agendamento merged = (Agendamento) this.session.merge(agendamento);
		this.session.flush();
		this.session.clear();
		return merged;
	}

	/**
	 * Lista o Agendamento que será alterado
	 */
	public List<Agendamento> listarParaAgendar(Ambiente ambiente, Date data, char turno) {
		//String hql = "from Agendamento a where a.codigo_ambiente = :ambiente and a.data = :data and a.turno = :turno";
		Query query = this.session.createQuery("from Agendamento a where a.ambiente = :ambiente and a.data = :data and a.turno = :turno");
		query.setLong("ambiente", ambiente.getId());
		query.setDate("data", data);
		query.setCharacter("turno", turno);
		//traz um único resultado
		query.uniqueResult();
		List<Agendamento> lista = query.list();
		return lista;
	}

}
