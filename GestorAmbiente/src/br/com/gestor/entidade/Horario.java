package br.com.gestor.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Indica o Horario de acontecimento do evento, em certo turno.
 * 
 * Exemplo: 1º Horário do Matutino, 2º Horário do Matutino, 1º Horário do Vespertino, 2º Horário do Vespertino .....
 * 
 * Deve ser inserido na base Manualmente estes valores. Não necessita de Tela para Isto
 * 
 * @author Natan
 *
 */
@Entity
@Table(name="GATB018_HORARIO")
public class Horario {
	
	@Id
	@GeneratedValue
	@Column(name="COD_HORARIO")
	private Long id;
	
	@Column(name="TURNO_HORARIO")
	private char turno;
	
	@Column(name="NOME_HORARIO")
	private int nome;
	
	@Column(name="HORA_INICIO")
	private Date horaInicio;
	
	@Column(name="HORA_FIM")
	private Date horaFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}
	
}
