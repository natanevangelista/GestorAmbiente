package br.com.gestor.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import br.com.gestor.util.HibernateUtil;

/**
 * pag: 194 verificar muito importante
 * @author Natan
 *
 */
public class ConexaoHibernateFilter implements Filter{

	private SessionFactory sf;
	
	/**
	 * este metodo � executado quando o aplicativo ou o servidor s�o desativados
	 */
	@Override
	public void destroy() {
	}

	/**
	 * M�todo onde a requisi��o web � interceptada. 
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		try {
			this.sf.getCurrentSession().beginTransaction();  //iniciando uma transa��o
			chain.doFilter(servletRequest, servletResponse);
			
			//this.sf.getCurrentSession().flush();
			
			this.sf.getCurrentSession().getTransaction().commit(); //todo processamento j� foi realizado, conclui a transa��o commit
			
			this.sf.getCurrentSession().close();           //fecha a sess�o atual com close().
		} catch (Throwable ex) {
			try {
				if (this.sf.getCurrentSession().getTransaction().isActive()) {
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			//throw new ServletException(ex);
		}
	}

	/**
	 * este metodo � executado quando o aplicativo � colocado no ar.
	 * Aqui criamos o SessionFactory do hibernate, classe que cria as sess�es do Hibernate a cada requisi��o. 
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}

}
