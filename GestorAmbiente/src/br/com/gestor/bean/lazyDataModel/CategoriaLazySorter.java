package br.com.gestor.bean.lazyDataModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Categoria;

public class CategoriaLazySorter implements Comparator<Categoria> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public CategoriaLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Categoria categoria, Categoria categoria2) {
        try {
            Object value1 = Categoria.class.getField(this.sortField).get(categoria);
            Object value2 = Categoria.class.getField(this.sortField).get(categoria2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}