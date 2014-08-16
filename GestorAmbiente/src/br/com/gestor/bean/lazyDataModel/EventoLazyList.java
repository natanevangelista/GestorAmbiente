package br.com.gestor.bean.lazyDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Evento;
import br.com.gestor.entidade.fake.EventoFake;

public class EventoLazyList extends LazyDataModel<Evento>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Evento> eventoLista;
	
	public EventoLazyList(List<Evento> eventoLista) {
		this.eventoLista = eventoLista;
	}
	
	@Override
    public Evento getRowData(String rowKey) {
        for(Evento evento : eventoLista) {
            if(evento.getId().equals(Long.parseLong(rowKey)))
                return evento;
        }

        return null;
    }

    @Override
    public Object getRowKey(Evento evento) {
        return evento.getId();
    }

    @Override
    public List<Evento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Evento> data = new ArrayList<Evento>();

        //filter
        for(Evento evento : eventoLista) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        String filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(new EventoFake(evento).getClass().getField(filterProperty).get(new EventoFake(evento)));
                        if(filterProperty.equals("status")){
                        	fieldValue = fieldValue == "true" ? "Ativo" : "Inativo";
                        }
                        	
                        if(filterValue == null || fieldValue.toUpperCase().startsWith(filterValue.toString().toUpperCase())) {
                            match = true;
                        } else {
                        	match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
            if(match) {
                data.add(evento);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new EventoLazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

}
