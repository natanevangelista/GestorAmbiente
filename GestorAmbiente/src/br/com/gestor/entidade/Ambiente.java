package br.com.gestor.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Entidade que armazena os tipos de ambientes(ex: laboratórios, salas, oficinas)
 * @author nata.evangelista
 *
 */
@Entity
@Table(name="GATB011_AMBIENTE")
public class Ambiente {
	
	@Id
	@GeneratedValue
	@Column(name="COD_AMBIENTE")
	private Long id;
	
	@Column(name="IDENTIFICACAO_AMBIENTE", length=20, unique=true)    //unique = não permite dados repetidos
	private String identificacao;

	@Column(name="QTD_VAGAS_AMBIENTE")
	private int qtdVagas;
	
	@Column(name="DESCRICAO_AMBIENTE", length=500)
	private String descricao;
	
	@Column(name="OBS_AMBIENTE", length=500)
	private String observacao;

	@Column(name="STATUS_AMBIENTE")
	private boolean status;

	@ManyToOne                                               //terá uma única categoria atrelada
	@JoinColumn(name="COD_CATEGORIA", nullable=false)     //nome da coluna no banco que vai armazenar este objeto estrangeiro
	@ForeignKey(name="FK_CATEGORIA_AMBIENTE")                         //nome atribuido a chave estrangeira
	private Categoria categoria;
	
	//metodos acessores
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getQtdVagas() {
		return qtdVagas;
	}
	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((identificacao == null) ? 0 : identificacao.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + qtdVagas;
		result = prime * result + (status ? 1231 : 1237);
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
		Ambiente other = (Ambiente) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificacao == null) {
			if (other.identificacao != null)
				return false;
		} else if (!identificacao.equals(other.identificacao))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (qtdVagas != other.qtdVagas)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	
	
		
}
