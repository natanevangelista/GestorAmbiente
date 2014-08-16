package br.com.gestor.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import br.com.gestor.web.util.RNException;

/**
 * DAO Generico, com todas as persistencias.
 * As especificas serão implementadas na Herança.
 * @author Natan
 *
 * @param <T>
 */
public abstract class GenericDAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Session session;
	
	private Class<T> entityClass;
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(T entityClass) {
		this.session.save(entityClass);
	}

	public void atualizar(T entityClass) {
		this.session.update(entityClass);
	}

	public void excluir(T entityClass) throws RNException {
		try{
			this.session.delete(entityClass);
			this.session.flush();
		}catch(PersistenceException e){
			throw e;
		}catch(ConstraintViolationException e){
			throw new RNException("operacao_excluir_violacao_chave", e);
		}
	}
	
	public List<T> listar() {
		return this.session.createCriteria(entityClass).list();
	}
	
	public Object carregar(Long id){
		return this.session.get(entityClass, id);
	}

}
