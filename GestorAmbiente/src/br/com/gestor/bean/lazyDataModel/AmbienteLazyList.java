package br.com.gestor.bean.lazyDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Ambiente;
import br.com.gestor.entidade.fake.AmbienteFake;

public class AmbienteLazyList extends LazyDataModel<Ambiente>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ambiente> ambientesLista;
	
	public AmbienteLazyList(List<Ambiente> ambientesLista) {
		this.ambientesLista = ambientesLista;
	}
	
	@Override
    public Ambiente getRowData(String rowKey) {
        for(Ambiente ambiente : ambientesLista) {
            if(ambiente.getId().equals(Long.parseLong(rowKey)))
                return ambiente;
        }

        return null;
    }

    @Override
    public Object getRowKey(Ambiente ambiente) {
        return ambiente.getId();
    }

    @Override
    public List<Ambiente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Ambiente> data = new ArrayList<Ambiente>();

        //filter
        for(Ambiente ambiente : ambientesLista) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        String filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(new AmbienteFake(ambiente).getClass().getField(filterProperty).get(new AmbienteFake(ambiente)));
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
                data.add(ambiente);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new AmbienteLazySorter(sortField, sortOrder));
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


    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public List<Ambiente> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
//		System.out.println(startingAt +"  "+ maxPerPage +"  "+ sortField +"  "+ sortOrder);
//		AmbienteRN ambienteRN = new AmbienteRN();
//		ambientesLista = ambienteRN.carregarAmbientes(startingAt, maxPerPage);
//
//		
//		if(getRowCount() <= 0){
//			setRowCount(ambienteRN.countAmbientesTotal());
//		}
//		
//		// set the page dize
//		setPageSize(maxPerPage);
//		return ambientesLista;
//	}
//	
//	@Override
//	public Object getRowKey(Ambiente ambiente) {
//		return ambiente.getId();
//	}
//	
//	@Override
//	public Ambiente getRowData(String ambienteId) {
//		Integer id = Integer.valueOf(ambienteId);
//		
//		for (Ambiente ambiente : ambientesLista) {
//			if(id.equals(ambiente.getId())){
//				return ambiente;
//			}
//		}
//		
//		return null;
//	}

}
