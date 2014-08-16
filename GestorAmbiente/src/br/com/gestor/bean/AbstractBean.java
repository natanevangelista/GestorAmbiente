package br.com.gestor.bean;

import com.sun.faces.context.flash.ELFlash;


public abstract class AbstractBean {

	public static final String TITULO_PAGINA = "tituloPagina";
	private String acaoTituloPagina;
	
	/**
	 * Método acionado para chamar a Página de edição de um determinado Bean.
	 * 
	 * @param pagina = valor referente a página que será chamada.
	 * @return
	 */
	public String editar(String pagina){
		return pagina;
	}
	
	public String getAcaoTituloPagina() {
		return (String) ELFlash.getFlash().get(TITULO_PAGINA);
	}
	
	public String novo(){
		return null;
	}
	
	public String salvar(){
		return null;
	}
}
