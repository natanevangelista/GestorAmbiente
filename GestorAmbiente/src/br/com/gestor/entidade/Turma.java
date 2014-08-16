package br.com.gestor.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="GATB005_TURMA")
public class Turma {

	@Id
	@GeneratedValue
	@Column(name="COD_TURMA")
	private Long id;

	/**
	 * Será gerado automaticamente, conforme regra de negócio (número + ano = 12014, 22014, 32014...)
	 */
	@Column(name="NOME_TURMA")
	private String nome;
	
	@Column(name="QTD_VAGAS_TURMA")
	private int qtdVagas;
	
	@Column(name="DESC_TURMA")
	private String descricao;
	
	@Column(name="OBS_TURMA")
	private String observacao;
	
	@Column(name="STATUS_TURMA")
	private char status;
	
	@ManyToOne                                               
	@JoinColumn(name="COD_EVENTO", nullable=false)    		 
	@ForeignKey(name="FK_EVENTO_TURMA")
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
