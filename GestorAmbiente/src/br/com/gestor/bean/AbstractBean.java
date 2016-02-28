package br.com.gestor.bean;

import java.io.Serializable;

import com.sun.faces.context.flash.ELFlash;

/**
 * Classe abstrata que representa todas as a��es dos Beans.
 * @author Natan    sd
 *
 */
public abstract class AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TITULO_PAGINA = "tituloPagina";
	private String acaoTituloPagina;asd
	
	/**
	 * M�todo acionado para chamar a P�gina de edi��o de um determinado Bean.
	 * 
	 * @param pagina = valor referente a p�gina que ser� chamada.
	 * @return
	 */
	public String editar(String pagina){
		return pagina;
	}
	
	/**
	 * M�todo utilizado para retornar o T�tulo da P�gina que ser� carregada.
	 * @return
	 */
	public String getAcaoTituloPagina() {
		return (String) ELFlash.getFlash().get(TITULO_PAGINA);
	}
	
	/**
	 * M�todo Utilizada para iniciar o Fluxo de Inser��o de uma Entidade
	 * @return
	 */
	public String novo(){
		return null;
	}
	
	/**
	 * M�todo utilizado para persist�ncia de uma Entidade
	 * @return
	 */
	public String salvar(){
		return null;
	}
	
	/**
	 * M�todo utilizado para chamar a exclus�o de uma Entidade.
	 * @return
	 */
	public String excluir(){
		return null;
	}
}
