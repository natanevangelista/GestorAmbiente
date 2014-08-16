package br.com.gestor.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
/**
 * Entidade Agendamento: armazena todas as possibilidades de agendamento para os ambientes
 * @author nata.evangelista
 *
 */
@Entity
@Table(name="GATB002_AGENDAMENTO")
public class Agendamento {
	
	@Id
	@GeneratedValue
	@Column(name="COD_AGENDAMENTO")
	private Long id;
	
	@Column(name="DT_AGENDAMENTO")
	@Temporal(TemporalType.DATE)       //define a precisão para um campo tipo data(DATE=armazena somente a data)
	private Date data;
	
	@Column(name="TURNO_AGENDAMENTO")
	private char turno;

	@ManyToOne                             
	@JoinColumn(name="COD_AMBIENTE")    
	@ForeignKey(name="FK_AMBIENTE_AGENDAMENTO")              
	private Ambiente ambiente;
	
	@ManyToOne                             
	@JoinColumn(name="COD_RESP")    
	@ForeignKey(name="FK_RESPONSAVEL_AGENDAMENTO")
	private Responsavel responsavel;
	
	@ManyToOne                             
	@JoinColumn(name="COD_TURMA")    
	@ForeignKey(name="FK_TURMA_AGENDAMENTO")
	private Turma turma;
	
	
	public Agendamento() {
		
	}

	/**
	 * construtor usado para gravar um aagendamento com parametros
	 * @param ambiente
	 * @param responsavel
	 * @param evento
	 * @param turno
	 * @param reservado
	 */
	public Agendamento(Ambiente ambiente, Responsavel responsavel, Turma turma, Date data, char turno, boolean feriado, boolean reservado) {
		this.ambiente = ambiente;
		this.responsavel = responsavel;
		this.turma = turma;
		this.data = data;
		this.turno = turno;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
