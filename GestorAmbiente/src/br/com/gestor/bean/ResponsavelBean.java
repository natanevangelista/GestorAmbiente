package br.com.gestor.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import br.com.gestor.RN.ResponsavelRN;
import br.com.gestor.bean.lazyDataModel.ResponsavelLazyList;
import br.com.gestor.entidade.Responsavel;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="responsavelBean")
@ViewScoped
public class ResponsavelBean extends AbstractBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SELECTED_RESPONSAVEL = "selectedResponsavel";
	private static final String TITULO_CADASTRAR = "Cadastrar Professor";
	private static final String TITULO_EDITAR = "Editar Professor";
	private Responsavel responsavel;
	ResponsavelRN responsavelRN;
	private List<Responsavel> lista;
	private LazyDataModel<Responsavel>  lazyResponsavel = null;
	
	@PostConstruct
	private void init() {
		if(lazyResponsavel == null){
			responsavelRN = new ResponsavelRN();
			lazyResponsavel = new ResponsavelLazyList(responsavelRN.listarOrderByNome());
		}
	}
	
	public LazyDataModel<Responsavel> getAllResponsaveis(){
		if(lazyResponsavel == null){
			responsavelRN = new ResponsavelRN();
			lazyResponsavel = new ResponsavelLazyList(responsavelRN.listarOrderByNome());
		}
		return lazyResponsavel;
	}
	
	public String novo(){
		this.responsavel = new Responsavel();
		this.responsavel.setStatus(true);
		ELFlash.getFlash().put(SELECTED_RESPONSAVEL, responsavel);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterResponsavel";
	}
	
	public String salvar(){
		responsavelRN = new ResponsavelRN();
		responsavelRN.salvar(this.responsavel);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.responsavel = null;
		lazyResponsavel = null;
		return "responsavel";
	}

	public String excluir(){
		responsavelRN = new ResponsavelRN();
		try{
			responsavelRN.excluir(this.responsavel);
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.responsavel = null;
			this.lista = null;
			this.lazyResponsavel = null;
			return null;
		} catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.responsavel = null;
			return null;
		}
	}
	
	public String editar(String pagina){
		ELFlash.getFlash().put(SELECTED_RESPONSAVEL, responsavel);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_EDITAR);
		return pagina;
	}
	
	public String ativar(){
		if(this.responsavel.isStatus()){
			this.responsavel.setStatus(false);
		} else {
			this.responsavel.setStatus(true);
		}
		
		responsavelRN = new ResponsavelRN();
		responsavelRN.salvar(this.responsavel);
		return null;
	}
	
	public Responsavel getResponsavel() {
		if(responsavel == null)
			responsavel = (Responsavel)ELFlash.getFlash().get(SELECTED_RESPONSAVEL);
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getLista() {
		if(this.lista == null){
			responsavelRN = new ResponsavelRN();
			this.lista = responsavelRN.listar();
		}
		return this.lista;
	}
	
}
