package br.com.gestor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import br.com.gestor.RN.AmbienteRN;
import br.com.gestor.bean.lazyDataModel.AmbienteLazyList;
import br.com.gestor.entidade.Ambiente;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="ambienteBean")
@ViewScoped
public class AmbienteBean extends AbstractBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SELECTED_AMBIENTE = "selectedAmbiente";
	private static final String TITULO_CADASTRAR = "Cadastrar Ambiente";
	private static final String TITULO_EDITAR = "Editar Ambiente";
	private static final String TITULO_PAGINA = "tituloPagina";
	private Ambiente ambiente;
	private List<Ambiente> lista;
	private LazyDataModel<Ambiente>  lazyAmbientes = null;

	@PostConstruct
	private void init() {
		if(lazyAmbientes == null){
			AmbienteRN ambienteRN = new AmbienteRN();
			lazyAmbientes = new AmbienteLazyList(ambienteRN.listarOrderByIdentificao());
		}
	}
	
	public LazyDataModel<Ambiente> getAllAmbientes(){
		if(lazyAmbientes == null){
			AmbienteRN ambienteRN = new AmbienteRN();
			lazyAmbientes = new AmbienteLazyList(ambienteRN.listarOrderByIdentificao());
		}
		return lazyAmbientes;
	}
	
	public String novo(){
		ambiente = new Ambiente();  	
		ambiente.setStatus(true);
		ELFlash.getFlash().put(SELECTED_AMBIENTE, ambiente);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterAmbiente";        	 
	}
	
	public String salvar(){
		AmbienteRN ambienteRN = new AmbienteRN();	
		ambienteRN.salvar(this.ambiente);           
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.ambiente = null;
		lazyAmbientes = null;
		return "ambiente";							
	}

	public String excluir(){
		AmbienteRN ambienteRN = new AmbienteRN();   
		try{
			ambienteRN.excluir(this.ambiente);         
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.ambiente = null;
			this.lista = null;
			lazyAmbientes = null;
			return null;                               
		}catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.ambiente = null;
			return null;
		}
	}
	
	public String editar(String pagina){
		ELFlash.getFlash().put(SELECTED_AMBIENTE, ambiente);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_EDITAR);
		return pagina;
	}
	
	public String ativar(){
		if(this.ambiente.isStatus()){
			this.ambiente.setStatus(false);
		} else {
			this.ambiente.setStatus(true);
		}
		
		AmbienteRN ambienteRN = new AmbienteRN();
		ambienteRN.salvar(this.ambiente);
		return null;
	}
	
	public String visualizar(){
		return "modalAmbiente";
	}
	
	public void setAmbiente(Ambiente ambiente){
		this.ambiente = ambiente;
	}
	
	public Ambiente getAmbiente(){
		if(ambiente == null)
		ambiente = (Ambiente)ELFlash.getFlash().get(SELECTED_AMBIENTE);
		return this.ambiente;
	}
	
	public List<Ambiente> getLista(){
		if(this.lista == null){
			AmbienteRN ambienteRN = new AmbienteRN();
			this.lista = ambienteRN.listarOrderByIdentificao();
		}
		return this.lista;
	}
	
	public String getAcaoTituloPagina() {
		return (String) ELFlash.getFlash().get(TITULO_PAGINA);
	}

	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Ambiente Selecionado", ((Ambiente)event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
