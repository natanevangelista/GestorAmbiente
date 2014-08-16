package br.com.gestor.web.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * classe que utiliza as mensagens no sistema
 * @author Natan
 *
 */
public class MensagemUtil {

	//localização do arquivo de mensagens
//	private static final String PACOTE_MENSAGENS = "br.com.gestor.mensagens.mensagens";

//	public void sendInfoMessageToUser(String message) {
//		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
//		addMessageToJsfContext(facesMessage);
//	}
//
//	public void sendErrorMessageToUser(String message) {
//		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
//		addMessageToJsfContext(facesMessage);
//	}

//	private FacesMessage createMessage(Severity severity, String mensagemErro) {
//		return new FacesMessage(severity, mensagemErro, mensagemErro);
//	}
//
//	private void addMessageToJsfContext(FacesMessage facesMessage) {
//		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//	}
	
	//daqui para baixo é para utilização do arquivomensagens.properties
	public static String getMensagem(String propriedade) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "bundle");
		return bundle.getString(propriedade);
	}
	
	public static String getMensagem(String propriedade, Object...parametros) {
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}


	public static void mensagemAtencao(String mensagem, Object...parametros){
		FacesContext context = FacesContext.getCurrentInstance();
		String texto = MensagemUtil.getMensagem(mensagem, parametros);
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, msg);
	}
	
	public static void mensagemWarm(String mensagem){
		FacesContext context = FacesContext.getCurrentInstance();
		String texto = MensagemUtil.getMensagem(mensagem);
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		context.addMessage(null, msg);
	}
	
}
