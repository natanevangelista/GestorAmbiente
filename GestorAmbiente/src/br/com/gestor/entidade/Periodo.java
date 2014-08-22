package br.com.gestor.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Indica o Periodo da Turma
 * 
 * Ex: 1º Período, 2º Período.....
 * 
 * @author Natan
 *
 */
@Entity
@Table(name="GATB008_PERIODO")
public class Periodo {

	@Id
	@GeneratedValue
	@Column(name="COD_PERIODO")
	private Long id;
	
	@Column(name="DESC_PERIODO")
	private String periodo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
