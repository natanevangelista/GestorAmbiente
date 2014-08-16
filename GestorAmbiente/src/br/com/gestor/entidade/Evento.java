package br.com.gestor.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade Evento.
 * Está entidade se refere ao evento que ocupa determinado ambiente.
 * @author nata.evangelista
 *
 */
@Entity
@Table(name="GATB003_EVENTO")
public class Evento {
	
	@Id
	@GeneratedValue
	@Column(name="COD_EVENTO")
	private Long id;
	
	@Column(name="NOME_EVENTO", length=50)
	private String nome;         //nome do evento (ex: Curso Java, Curso Administração)
	
	@Column(name="MODALIDADE_EVENTO", length=50)
	private String modalidade;   //modalidade do evento (ex: Qualificação, 1° Bimestre 2012)
	
	@Column(name="OBS_EVENTO", length=250)
	private String observacao;   //detalhes do evento

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

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modalidade == null) {
			if (other.modalidade != null)
				return false;
		} else if (!modalidade.equals(other.modalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}
	
	
}
