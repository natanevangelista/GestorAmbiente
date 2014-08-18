package br.com.gestor.DAOHibernate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
	
	/**
	 * Retorna a última Turma do ANO passado como Parâmetro 
	 * @return 
	 */
	public Turma findUltimaTurmabyAno(String ano){
		Criteria criteria = session.createCriteria(Turma.class);
		criteria.add(Restrictions.ilike("nome", ano, MatchMode.END));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
		return (Turma) criteria.uniqueResult();
	}

	public List<Turma> findByParametros(Turma turma) {
		Criteria criteria = session.createCriteria(Turma.class);
		criteria.add(Restrictions.ilike("nome", turma.getNome()));
		
		
//		Example example = Example.create(turma).ignoreCase();
//		example.enableLike();
//		return session.createCriteria(Turma.class).add(example).list();
		
		return criteria.list();
	}
}
