package br.com.gestor.entidade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entidade que define os usu�rios do sistema e respons�veis pela utiliza��o de um ambiente
 * @author nata.evangelista
 *
 */
@Entity
@Table(name="GATB001_RESPONSAVEL")
public class Responsavel {
	
	@Id
	@GeneratedValue
	@Column(name="COD_RESP")
	private Long id;
	
	@Column(name="NOME_RESP", length=50)
	private String nome;
	
	@Column(name="TEL_RESP", length=50)
	private String telefone;
	
	@Column(name="EMAIL_RESP", length=50)
	private String email;
	
	@Column(name="SENHA_RESP", length=20)
	private String senha;
	
	@Column(name="SMS_RESP")
	private boolean sms;
	
	@Column(name="STATUS_RESP")
	private boolean status;
	
	@ElementCollection(targetClass = String.class)
	@JoinTable(
			name="GATB004USUARIO_PERMISSAO",
			uniqueConstraints={@UniqueConstraint(columnNames={"USUARIO","PERMISSAO_RESP"})},
			joinColumns= @JoinColumn(name="USUARIO"))
	@Column(name="PERMISSAO_RESP", length=50)
	private Set<String> permissao = new HashSet<String>();

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<String> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<String> permissao) {
		this.permissao = permissao;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + (sms ? 1231 : 1237);
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (sms != other.sms)
			return false;
		if (status != other.status)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	
}
