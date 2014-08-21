package br.com.gestor.bean.lazyDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Responsavel;
import br.com.gestor.entidade.fake.ResponsavelFake;

public class ResponsavelLazyList extends LazyDataModel<Responsavel>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Responsavel> responsavelLista;
	
	public ResponsavelLazyList(List<Responsavel> responsavelLista) {
		this.responsavelLista = responsavelLista;
	}
	
	@Override
    public Responsavel getRowData(String rowKey) {
        for(Responsavel responsavel : responsavelLista) {
            if(responsavel.getId().equals(Long.parseLong(rowKey)))
                return responsavel;
        }

        return null;
    }

    @Override
    public Object getRowKey(Responsavel responsavel) {
        return responsavel.getId();
    }

    @Override
    public List<Responsavel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Responsavel> data = new ArrayList<Responsavel>();

        //filter
        for(Responsavel responsavel : responsavelLista) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        String filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(new ResponsavelFake(responsavel).getClass().getField(filterProperty).get(new ResponsavelFake(responsavel)));
                        if(filterProperty.equals("status")){
                        	fieldValue = fieldValue == "true" ? "Ativo" : "Inativo";
                        }
                        	
                        if(filterValue == null || fieldValue.toUpperCase().contains(filterValue.toString().toUpperCase())) {
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
                data.add(responsavel);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new ResponsavelLazySorter(sortField, sortOrder));
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
