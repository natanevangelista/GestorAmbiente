package br.com.gestor.entidade.fake;

import br.com.gestor.entidade.Evento;

public class EventoFake {

	public String nome;
	public String modalidade;
	
	public EventoFake(Evento evento) {
		this.nome = evento.getNome();
		this.modalidade = evento.getModalidade();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	
}
