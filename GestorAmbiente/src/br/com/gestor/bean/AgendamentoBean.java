package br.com.gestor.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.gestor.RN.AgendamentoRN;
import br.com.gestor.entidade.Agendamento;
import br.com.gestor.web.util.MensagemUtil;

/**
 * 
 * @author Natan
 *
 */
@ManagedBean(name="agendamentoBean")
@RequestScoped
public class AgendamentoBean extends MensagemUtil{

	private Agendamento 		agendamento = new Agendamento();
	private List<Agendamento> 	lista;
	private Agendamento 		agendamentoSelecionado;
	private Integer 			qtdEventos;
	private Integer[]			diasSemanaVetor;
	private int					gravados;
	private List<Agendamento>	agendamentosGravados = new ArrayList<Agendamento>();
	
	
	/**
	 * metodo chamado ao acionar a página novo agendamento
	 * @return
	 */
	public String novo(){
		this.agendamento = new Agendamento();  	//inicia a página com uma instância para Ambiente
		
		return "manterAgendamento";        	    //chama tela de cadastro de ambientes
	}
	
	public void sy(){
		System.out.println("foiiiiiiiiiiiiiiiiiiiiii");
	}
	
//	public void testarIterator(){
//		int u = 0;
//		
//		while(u < 10){
//			Iterator<String> it = lisDias.iterator();
//			
//			while(it.hasNext() && u < 10){
//				//String num = it.next().toString();
//				//int oi =  Integer.parseInt(num);
//				String oui = it.next();
////				System.out.println(it.next());
//				
//				System.out.println("valor de Inteiro: "+ Integer.parseInt(oui));
//				System.out.println("valor de u: "+u);
//				
//				u++;
//			}	
//		}	
//	}
	
	/**
	 * Método responsável por chamar o método realizarAgendamento, com os parâmetros agendamento, qtdEventos e diasSemana
	 */
	public String agendarEvento(){
		AgendamentoRN agendamentoRN = new AgendamentoRN();
		agendamentoRN.realizarAgendamento(agendamento, qtdEventos, diasSemanaVetor);
		mensagemAtencao("gravacao_agendamento", agendamentoRN.getGravados());
		agendamentosGravados = agendamentoRN.getAgendadosGravados();
		return "manterAgendamento";
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}
	
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	/**
	 * Retorna a lista com todos os Agendamentos do Banco
	 * @return
	 */
	public List<Agendamento> getLista() {
		if(this.lista == null){
			AgendamentoRN agendamentoRN = new AgendamentoRN();
			this.lista = agendamentoRN.listar();
		}
		return this.lista;
	}

	public Integer getQtdEventos() {
		return qtdEventos;
	}

	public void setQtdEventos(Integer qtdEventos) {
		this.qtdEventos = qtdEventos;
	}

	public Agendamento getAgendamentoSelecionado() {
		return agendamentoSelecionado;
	}

	public void setAgendamentoSelecionado(Agendamento agendamentoSelecionado) {
		this.agendamentoSelecionado = agendamentoSelecionado;
	}

	public Integer[] getDiasSemanaVetor() {
		return diasSemanaVetor;
	}

	public void setDiasSemanaVetor(Integer[] diasSemanaVetor) {
		this.diasSemanaVetor = diasSemanaVetor;
	}

	public int getGravados() {
		return gravados;
	}

	public void setGravados(int gravados) {
		this.gravados = gravados;
	}

	public List<Agendamento> getAgendamentosGravados() {
		return agendamentosGravados;
	}

	public void setAgendamentosGravados(List<Agendamento> agendamentosGravados) {
		this.agendamentosGravados = agendamentosGravados;
	}

	
	
}
