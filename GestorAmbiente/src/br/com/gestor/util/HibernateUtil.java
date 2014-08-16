package br.com.gestor.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory	sessionFactory	= buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			//Cria o SessionFactory do arquivo hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Criação inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	} 

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
