package br.com.gestor.mensagens;

import junit.framework.TestCase;

import org.junit.Test;

public class TesteCalendarTest extends TestCase{

	@Test
	public void testRetornarDiaSemana() {
		String teste = TesteCalendar.retornarDiaSemana(2012, 11, 12);
		assertEquals("Segunda", teste);
		
	}

}
