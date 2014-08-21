package br.com.gestor.bean.lazyDataModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Responsavel;

public class ResponsavelLazySorter implements Comparator<Responsavel> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public ResponsavelLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Responsavel responsavel, Responsavel responsavel2) {
        try {
            Object value1 = Responsavel.class.getField(this.sortField).get(responsavel);
            Object value2 = Responsavel.class.getField(this.sortField).get(responsavel2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}