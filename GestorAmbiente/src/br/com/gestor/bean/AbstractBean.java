package br.com.gestor.bean;

import java.io.Serializable;

import com.sun.faces.context.flash.ELFlash;

/**
 * Classe abstrata que representa todas as ações dos Beans.
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
	 * Método acionado para chamar a Página de edição de um determinado Bean.
	 * 
	 * @param pagina = valor referente a página que será chamada.
	 * @return
	 */
	public String editar(String pagina){
		return pagina;
	}
	
	/**
	 * Método utilizado para retornar o Título da Página que será carregada.
	 * @return
	 */
	public String getAcaoTituloPagina() {
		return (String) ELFlash.getFlash().get(TITULO_PAGINA);
	}
	
	/**
	 * Método Utilizada para iniciar o Fluxo de Inserção de uma Entidade
	 * @return
	 */
	public String novo(){
		return null;
	}
	
	/**
	 * Método utilizado para persistência de uma Entidade
	 * @return
	 */
	public String salvar(){
		return null;
	}
	
	/**
	 * Método utilizado para chamar a exclusão de uma Entidade.
	 * @return
	 */
	public String excluir(){
		return null;
	}
}
