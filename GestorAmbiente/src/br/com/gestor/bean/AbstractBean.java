package br.com.gestor.bean;

import com.sun.faces.context.flash.ELFlash;


public abstract class AbstractBean {

	public static final String TITULO_PAGINA = "tituloPagina";
	private String acaoTituloPagina;
	
	/**
	 * M�todo acionado para chamar a P�gina de edi��o de um determinado Bean.
	 * 
	 * @param pagina = valor referente a p�gina que ser� chamada.
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
