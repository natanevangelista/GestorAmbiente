package br.com.gestor.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import br.com.gestor.RN.EventoRN;
import br.com.gestor.bean.lazyDataModel.EventoLazyList;
import br.com.gestor.entidade.Evento;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="eventoBean")
@ViewScoped
public class EventoBean extends AbstractBean{

	private Evento evento;
	private static final String SELECTED_EVENTO = "selectedEvento";
	private static final String TITULO_CADASTRAR = "Cadastrar Evento";
	private static final String TITULO_EDITAR = "Editar Evento";
	
	private List<Evento> lista;
	private LazyDataModel<Evento> lazyEvento = null;

	private List<Evento> allEventos;
	
	EventoRN eventoRN;
	
	@PostConstruct
	private void init() {
		if(lazyEvento == null){
			eventoRN = new EventoRN();
			lazyEvento = new EventoLazyList(eventoRN.listarOrderByNome());
		}
	}
	
	public LazyDataModel<Evento> getAllEventos(){
		if(lazyEvento == null){
			eventoRN = new EventoRN();
			lazyEvento = new EventoLazyList(eventoRN.listarOrderByNome());
		}
		return lazyEvento;
	}
	
	public String novo(){
		this.evento = new Evento();
		ELFlash.getFlash().put(SELECTED_EVENTO, evento);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterEvento";
	}
	
	public String salvar(){
		lazyEvento = null;
		eventoRN = new EventoRN();
		eventoRN.salvar(this.evento);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.evento = null;
		return "evento";
	}

	public String excluir(){
		eventoRN = new EventoRN();
		try{
			eventoRN.excluir(this.evento);
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.evento = null;
			this.lista = null;
			lazyEvento = null;
			return null;
		}catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.evento = null;
			return null;
		}
	}
	
	public String editar(String pagina){
		ELFlash.getFlash().put(SELECTED_EVENTO, evento);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_EDITAR);
		return pagina;
	}
	
	public List<Evento> getLista(){
		if(this.lista == null){
			eventoRN = new EventoRN();
			this.lista = eventoRN.listar();
		}
		return this.lista;
	}
	
	public void consultarEvento(){
		lista = new ArrayList<Evento>();
		eventoRN = new EventoRN();
		this.lista = eventoRN.listar();
//		this.lista.add((Evento) eventoRN.listar());
		System.out.println("consultar evento");
	}
	
	/**
	 * Método utilizado para AutoCompletar campo na Tela de manterTurma
	 * 
	 * @param query
	 * @return
	 */
	public List<Evento> completeEvento(String query) {
		if(allEventos == null || allEventos.isEmpty()){
			eventoRN = new EventoRN();
			allEventos = eventoRN.listar();
		}
        List<Evento> filteredEventos = new ArrayList<Evento>();
        for (int i = 0; i < allEventos.size(); i++) {
            Evento skin = allEventos.get(i);
            if(skin.getNome().toLowerCase().contains(query)) {
            	filteredEventos.add(skin);
            }
        }
        return filteredEventos;
    }
	
	public Evento getEvento() {
		if(evento == null)
			evento = (Evento)ELFlash.getFlash().get(SELECTED_EVENTO);
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
