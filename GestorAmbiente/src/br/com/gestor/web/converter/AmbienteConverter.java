package br.com.gestor.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.gestor.RN.AmbienteRN;
import br.com.gestor.entidade.Ambiente;


@FacesConverter(forClass = Ambiente.class) //sempre que a propriedade de destino ou origem for Ambiente utilizará esse conversor
public class AmbienteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {   //em String value vem a informação digitada pelo usuário
		if(value != null && value.trim().length() > 0){
			Long codigo = Long.valueOf(value);                     //transforma o código em integer
			try{
				AmbienteRN ambienteRN = new AmbienteRN();
				return ambienteRN.carregar(codigo);                     //retorna busca o objeto completo da categoria
			}catch(Exception e){
				throw new ConverterException("Não foi possivel encontrar o código "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Ambiente ambiente = (Ambiente) value;             //faz um Cast do value recebido para o tipo Ambiente
			return ambiente.getId().toString(); 	       //retorna o código do ambiente em formato texto
		}
		return "";
	}
}
