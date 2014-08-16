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
	 * este metodo é executado quando o aplicativo ou o servidor são desativados
	 */
	@Override
	public void destroy() {
	}

	/**
	 * Método onde a requisição web é interceptada. 
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		try {
			this.sf.getCurrentSession().beginTransaction();  //iniciando uma transação
			chain.doFilter(servletRequest, servletResponse);
			
			//this.sf.getCurrentSession().flush();
			
			this.sf.getCurrentSession().getTransaction().commit(); //todo processamento já foi realizado, conclui a transação commit
			
			this.sf.getCurrentSession().close();           //fecha a sessão atual com close().
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
	 * este metodo é executado quando o aplicativo é colocado no ar.
	 * Aqui criamos o SessionFactory do hibernate, classe que cria as sessões do Hibernate a cada requisição. 
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}

}
