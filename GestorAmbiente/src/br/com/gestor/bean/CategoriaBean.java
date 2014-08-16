package br.com.gestor.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import br.com.gestor.RN.CategoriaRN;
import br.com.gestor.bean.lazyDataModel.CategoriaLazyList;
import br.com.gestor.entidade.Categoria;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="categoriaBean")
@ViewScoped
public class CategoriaBean extends AbstractBean{

	private Categoria categoria;
	private static final String SELECTED_CATEGORIA = "selectedCategoria";
	private static final String TITULO_CADASTRAR = "Cadastrar Categoria";
	private static final String TITULO_EDITAR = "Editar Categoria";
	private static final String TITULO_PAGINA = "tituloPagina";
	private String acaoTituloPagina;
	
	private List<Categoria> lista;
	private LazyDataModel<Categoria> lazyCategoria = null;
	
	@PostConstruct
	private void init() {
		if(lazyCategoria == null){
			CategoriaRN categoriaRN = new CategoriaRN();
			lazyCategoria = new CategoriaLazyList(categoriaRN.listarOrderByDescricao());
		}
	}
	
	public LazyDataModel<Categoria> getAllCategorias(){
		if(lazyCategoria == null){
			CategoriaRN categoriaRN = new CategoriaRN();
			lazyCategoria = new CategoriaLazyList(categoriaRN.listarOrderByDescricao());
		}
		return lazyCategoria;
	}
	
	public String novo(){
		this.categoria = new Categoria();
		ELFlash.getFlash().put(SELECTED_CATEGORIA, categoria);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterCategoria";
	}
	
	public String salvar(){
		lazyCategoria = null;
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.salvar(this.categoria);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.categoria = null;
		return "categoria";
	}
	
	public String excluir(){
		CategoriaRN categoriaRN = new CategoriaRN();		
		try{
			categoriaRN.excluir(this.categoria);                
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.categoria = null;
			this.lista = null;
			lazyCategoria = null;
			return null;
		}catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.categoria = null;
			return null;
		}
	}
	
	public String editar(String pagina){
		ELFlash.getFlash().put(SELECTED_CATEGORIA, categoria);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_EDITAR);
		return pagina;
	}
	
	public List<Categoria> getLista(){    					
		if(this.lista == null){ 
			CategoriaRN categoriaRN = new CategoriaRN();
			this.lista = categoriaRN.listar();
		}	
		return this.lista;
	}

	public Categoria getCategoria() {
		if(categoria == null)
			categoria = (Categoria)ELFlash.getFlash().get(SELECTED_CATEGORIA);
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAcaoTituloPagina() {
		return (String) ELFlash.getFlash().get(TITULO_PAGINA);
	}

	
}
