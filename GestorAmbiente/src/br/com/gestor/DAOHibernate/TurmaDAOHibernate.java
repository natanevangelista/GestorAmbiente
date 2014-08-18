package br.com.gestor.DAOHibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
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
		
		//Trata o nome
		if(!turma.getNome().equals("")){
			criteria.add(Restrictions.eq("nome", turma.getNome()));
		}

		//Trata o Evento
		if(turma.getEvento() != null){
			criteria.add(Restrictions.eq("evento", turma.getEvento()));
		}
		
		//Trata a Descrição
		if(!turma.getDescricao().equals("")){
			criteria.add(Restrictions.ilike("descricao", turma.getDescricao(), MatchMode.ANYWHERE));
		}
		
		//Trata o status
		if(turma.getStatus() == 'T'){
			List< Character> characters = new ArrayList<Character>();
			characters.add('I');
			characters.add('N');
			characters.add('F');
			characters.add('S');
			criteria.add(Restrictions.in("status", characters));
		} else {
			criteria.add(Restrictions.eq("status", turma.getStatus()));
		}
		return criteria.list();
		
//		Example example = Example.create(turma);
//		example.enableLike();
//		return session.createCriteria(Turma.class).add(example).list();
	}
}
