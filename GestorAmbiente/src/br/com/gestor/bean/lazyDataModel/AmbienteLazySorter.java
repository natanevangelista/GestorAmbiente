package br.com.gestor.bean.lazyDataModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Ambiente;

public class AmbienteLazySorter implements Comparator<Ambiente> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public AmbienteLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Ambiente ambiente, Ambiente ambiente2) {
        try {
            Object value1 = Ambiente.class.getField(this.sortField).get(ambiente);
            Object value2 = Ambiente.class.getField(this.sortField).get(ambiente2);

			int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}