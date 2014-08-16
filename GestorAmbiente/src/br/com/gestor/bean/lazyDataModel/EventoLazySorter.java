package br.com.gestor.bean.lazyDataModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.gestor.entidade.Evento;

public class EventoLazySorter implements Comparator<Evento> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public EventoLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Evento evento, Evento evento2) {
        try {
            Object value1 = Evento.class.getField(this.sortField).get(evento);
            Object value2 = Evento.class.getField(this.sortField).get(evento2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}