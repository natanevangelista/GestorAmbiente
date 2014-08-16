package br.com.gestor.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.gestor.RN.ResponsavelRN;
import br.com.gestor.entidade.Responsavel;


@FacesConverter(forClass = Responsavel.class) //sempre que a propriedade de destino ou origem for Pessoa utilizar� esse conversor
public class ResponsavelConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {   //em String value vem a informa��o digitada pelo usu�rio
		if(value != null && value.trim().length() > 0){
			Long codigo = Long.valueOf(value);                     //transforma o c�digo em integer
			try{
				ResponsavelRN responsavelRN = new ResponsavelRN();
				return responsavelRN.carregar(codigo);                     //retorna busca o objeto completo da categoria
			}catch(Exception e){
				throw new ConverterException("N�o foi possivel encontrar o c�digo "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Responsavel responsavel = (Responsavel) value;             //faz um Cast do value recebido para o tipo Pessoa
			return responsavel.getId().toString(); 	       //retorna o c�digo da categoria em formato texto
		}
		return "";
	}
}
