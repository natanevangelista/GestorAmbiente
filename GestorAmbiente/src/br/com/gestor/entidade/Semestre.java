package br.com.gestor.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Indica o Semestre a quem a turma pertence.
 * 
 * Exemplo, 1º 2014, 2º 2014, 1º 2015
 * @author Natan
 *
 */
@Entity
@Table(name="GATB019_SEMESTRE")
public class Semestre {

	@Id
	@GeneratedValue
	@Column(name="COD_SEMESTRE")
	private Long id;
	
	@Column(name="NUMER_SEMESTRE")
	private int numero;
	
	@Column(name="ANO_SEMESTRE")
	private int ano;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
