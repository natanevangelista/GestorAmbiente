package br.com.gestor.bean.lazyDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Categoria;
import br.com.gestor.entidade.fake.CategoriaFake;

public class CategoriaLazyList extends LazyDataModel<Categoria>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> categoriaLista;
	
	public CategoriaLazyList(List<Categoria> categoriaLista) {
		this.categoriaLista = categoriaLista;
	}
	
	@Override
    public Categoria getRowData(String rowKey) {
        for(Categoria categoria : categoriaLista) {
            if(categoria.getId().equals(Long.parseLong(rowKey)))
                return categoria;
        }

        return null;
    }

    @Override
    public Object getRowKey(Categoria categoria) {
        return categoria.getId();
    }

    @Override
    public List<Categoria> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Categoria> data = new ArrayList<Categoria>();

        //filter
        for(Categoria categoria : categoriaLista) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        String filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(new CategoriaFake(categoria).getClass().getField(filterProperty).get(new CategoriaFake(categoria)));
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
                data.add(categoria);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new CategoriaLazySorter(sortField, sortOrder));
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
