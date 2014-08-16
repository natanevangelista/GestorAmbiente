package br.com.gestor.web.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletInformaProfessor extends HttpServlet{
	
	
	static final int HORA = 1;
	static final int MINUTO = 1;
	static final int SEGUNDO = 1;
	static final int SEGUNDOS = 600000;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		/**
		 * Configuração do Horario Inicial de Execução da Scheduler
		 * Será executado todos os dias às 23:59:59.
		 */
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, HORA);
		calendar.set(Calendar.MINUTE, MINUTO);
		calendar.set(Calendar.SECOND, SEGUNDO);

		Timer timer = new Timer();
		timer.schedule(new EnviaEmailTask(), calendar.getTime(),
				SEGUNDOS
				
			);
	}

}
