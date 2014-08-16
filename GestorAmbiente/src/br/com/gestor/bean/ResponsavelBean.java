package br.com.gestor.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.gestor.RN.ResponsavelRN;
import br.com.gestor.entidade.Responsavel;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

@ManagedBean(name="responsavelBean")
@RequestScoped
public class ResponsavelBean extends AbstractBean{
	
	private Responsavel responsavel = new Responsavel();
	private List<Responsavel> lista;
	
	public String novo(){
		this.responsavel = new Responsavel();
		this.responsavel.setStatus(true);
		return "manterResponsavel";
	}
	
	public String salvar(Responsavel responsavel){
		ResponsavelRN pessoaRN = new ResponsavelRN();
		pessoaRN.salvar(responsavel);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		responsavel = null;
		return "responsavel";
	}

	public String excluir(){
		ResponsavelRN responsavelRN = new ResponsavelRN();
		try{
			responsavelRN.excluir(this.responsavel);
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.responsavel = null;
			this.lista = null;
			return null;
		} catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.responsavel = null;
			return null;
		}
	}
	
	public String ativar(){
		if(this.responsavel.isStatus()){
			this.responsavel.setStatus(true);
		} else {
			this.responsavel.setStatus(false);
		}
		
		ResponsavelRN responsavelRN = new ResponsavelRN();
		responsavelRN.salvar(this.responsavel);
		return null;
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getLista() {
		if(this.lista == null){
			ResponsavelRN responsavelRN = new ResponsavelRN();
			this.lista = responsavelRN.listar();
		}
		return this.lista;
	}
	
}
