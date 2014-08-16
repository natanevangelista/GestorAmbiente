package br.com.gestor.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.gestor.RN.CategoriaRN;
import br.com.gestor.entidade.Categoria;

@FacesConverter(forClass = Categoria.class)     //sempre que a propriedade de destino ou origem for Categoria utilizar� esse conversor
public class CategoriaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {   //em String value vem a informa��o digitada pelo usu�rio
		if(value != null && value.trim().length() > 0){
			Long codigo = Long.valueOf(value);                     //transforma o c�digo em integer
			try{
				CategoriaRN categoriaRN = new CategoriaRN();
				return categoriaRN.carregar(codigo);                     //retorna busca o objeto completo da categoria
			}catch(Exception e){
				throw new ConverterException("N�o foi possivel encontrar o c�digo "+value+". "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Categoria categoria = (Categoria) value;       //faz um Cast do value recebido para o tipo Categoria
			return categoria.getId().toString(); 	       //retorna o c�digo da categoria em formato texto
		}
		return "";
	}

}
