package br.com.gestor.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.gestor.RN.EventoRN;
import br.com.gestor.entidade.Evento;


@FacesConverter(forClass = Evento.class) //sempre que a propriedade de destino ou origem for Evento utilizará esse conversor
public class EventoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {   //em String value vem a informação digitada pelo usuário
		if(value != null && value.trim().length() > 0){
			Long codigo = Long.valueOf(value);                     //transforma o código em integer
			try{
				EventoRN eventoRN = new EventoRN();
				return eventoRN.carregar(codigo);                     //retorna busca o objeto completo da categoria
			}catch(Exception e){
				throw new ConverterException("Não foi possivel encontrar o código "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Evento evento = (Evento) value;             //faz um Cast do value recebido para o tipo Evento
			return evento.getId().toString(); 	       //retorna o código da categoria em formato texto
		}
		return "";
	}
}
