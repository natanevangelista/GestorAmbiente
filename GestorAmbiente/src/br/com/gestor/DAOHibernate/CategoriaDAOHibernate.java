package br.com.gestor.DAOHibernate;

import java.util.List;

import org.hibernate.Query;

import br.com.gestor.DAO.GenericDAO;
import br.com.gestor.entidade.Categoria;

public class CategoriaDAOHibernate extends GenericDAO<Categoria>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaDAOHibernate() {
		super(Categoria.class);
	}

	public List<Categoria> listarOrderByDescricao() {
		Query query = session.createQuery("from Categoria c order by c.descricao");
		return query.list();
	}


}
