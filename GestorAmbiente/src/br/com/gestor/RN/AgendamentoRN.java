package br.com.gestor.RN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.AgendamentoDAOHibernate;
import br.com.gestor.entidade.Agendamento;
import br.com.gestor.entidade.Ambiente;
import br.com.gestor.web.util.DAOException;

public class AgendamentoRN {

	private AgendamentoDAOHibernate agendamentoDAO;
	private int gravados;
	private List<Agendamento> agendadosGravados;
	
	public AgendamentoRN() {
		this.agendamentoDAO = (AgendamentoDAOHibernate) DAOFactory.criarAgendamentoDAO();
	}

	/**
	 * Metodo que retorna todos os Agendamento sdo Banco
	 * 
	 * @return
	 */
	public List<Agendamento> listar() {
		return (List<Agendamento>) this.agendamentoDAO.listar();
	}

	/**
	 * Retorna a lista do Agendamento que será alterado
	 * 
	 * @param ambiente
	 * @param data
	 * @param turno
	 * @return
	 */
	public List<Agendamento> listarParaGravar(Ambiente ambiente, Date data,	char turno) {
		return this.agendamentoDAO.listarParaAgendar(ambiente, data, turno);
	}

	/**
	 * Método responsável por realizar o agendamento
	 * 
	 * @param agendamento = objeto
	 * @param qtdEventos  = quantidade de eventos que serão agendados
	 * @param diasSemana  = dias da semana que terá evento
	 */
	public void realizarAgendamento(Agendamento agendamento, Integer qtdEventos, Integer[] diasSemana) {

		// cria um objeto do tipo GREGORIAN
		GregorianCalendar dataGregorian = new GregorianCalendar();
		// objeto do tipo DATE recebe a data de início dos agendamentos
		Date dataDate = agendamento.getData();
		// objeto do tipo GREGORIAN recebe a data de início dos agendamentos
		dataGregorian.setTime(dataDate);

		int qtdEventosPercorridos = 0;
		int contador = 0;
		gravados = 0;

		while (qtdEventosPercorridos < qtdEventos) {
			//verifica se dia da semana está no rol enviado pelo ator
			while (dataGregorian.get(GregorianCalendar.DAY_OF_WEEK) != diasSemana[contador]) {
				contador++;
				//muda a data caso a data não estaja dentro do rol enviado pelo ator
				if (contador == diasSemana.length) {
					dataGregorian.add(Calendar.DATE, 1);
					contador = 0;
				}
			}

			// grava o agendamento
//			if (dataGregorian.get(GregorianCalendar.DAY_OF_WEEK) == diasSemana[contador]) {
//				// objeto tipo DATE recebe a data GREGORIAN
//				dataDate = dataGregorian.getTime();
//				// faz consulta e retorna o agendamento Solicitado
//				List<Agendamento> list = this.listarParaGravar(agendamento.getAmbiente(), dataDate,	agendamento.getTurno());
//				// agend recebe os dados da consulta
//				for (Agendamento agend : list) {
//					// atribuição dos parametros perdidos na consulta
//					agend.setResponsavel(agendamento.getResponsavel());
////					agend.setEvento(agendamento.getEvento());
////					agend.setReservado(true);
//					this.agendamentoDAO.atualizar(agend);
//					
//					//armazena a quantidade de agendamentos gravados
//					gravados++;
//					
//					//armazena os agendamentos gravados
//					this.getAgendadosGravados().add(agend);
//					
//					qtdEventosPercorridos++;
//					dataGregorian.setTime(dataDate);
//					dataGregorian.add(Calendar.DATE, 1);
//					contador = 0;
//				}
//			}
		}
	}

	/**
	 * Metodo acionado ao cadastrar um novo ambiente, preencha a tabela
	 * agendamento com todas as data de agendamento para o ambiente Cadastrado
	 * 
	 * @param ambiente
	 * @throws DAOException 
	 */
	public void salvaAmbienteVazio(Ambiente ambiente) throws DAOException {
		// foi utilizado o GregorianCalendar pela facilidade em aumentar dias
		GregorianCalendar dataParaAumentar = new GregorianCalendar();
		char turnoParaGravar;
		boolean feriado;

		Date dataConvertida = new Date();

		// percorre a quantidade de dias que deve gravar(definido para gravar o
		// período de 40 dias)
		for (int i = 0; i < 40; i++) {
			dataParaAumentar.add(Calendar.DATE, 1);
			// retorna o número do dia na semana (se 1 ou 7, respectivamente
			// serão domingo e sábado)
			int diaSemana = dataParaAumentar.get(GregorianCalendar.DAY_OF_WEEK);
			// se 1=domingo ou 7=sábado atribui true para o campo feriado,
			// isto serve para quando realizar um agendamento o sistema vai
			// ignorar Domingos e Sábados
			if (diaSemana == 1 || diaSemana == 7) {
				feriado = true;
			} else {
				feriado = false;
			}
			// percorre os turnos que devem ser gravados
			for (int r = 0; r < 3; r++) {
				if (r == 0) {
					turnoParaGravar = 'M';
				} else if (r == 1) {
					turnoParaGravar = 'V';
				} else {
					turnoParaGravar = 'N';
				}
				// recebe a data em Formato GregorianCalendar
				dataConvertida = dataParaAumentar.getTime();
				this.agendamentoDAO.salvar(new Agendamento(ambiente, null,
						null, dataConvertida, turnoParaGravar, feriado, false));
			}
		}
	}

	public int getGravados() {
		return gravados;
	}

	public List<Agendamento> getAgendadosGravados() {
		if(this.agendadosGravados == null){
			this.agendadosGravados = new ArrayList<Agendamento>();
		}
		return agendadosGravados;
	}

	
	
}
