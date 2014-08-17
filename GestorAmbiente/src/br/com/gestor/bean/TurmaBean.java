package br.com.gestor.bean;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.gestor.RN.TurmaRN;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="turmaBean")
@RequestScoped
public class TurmaBean extends AbstractBean {

	private static final String SELECTED_TURMA = "selectedTurma";
	private static final String TITULO_CADASTRAR = "Cadastrar Turma";
	private static final String TITULO_EDITAR = "Editar Turma";

	private Turma turma = new Turma();
	private List<Turma> lista;
	private TurmaRN turmaRN;
	
	private static final HashMap<Character, String> status = new HashMap<Character, String>();
	private static final Character INICIALIZADO_KEY = 'I';
	private static final Character NAO_INICIALIZADO_KEY = 'N';
	private static final Character FINALIZADO_KEY = 'F';
	
	private static final String INICIALIZADO = "INICIALIZADO";
	private static final String NAO_INICIALIZADO = "NÃO INICIALIZADO";
	private static final String FINALIZADO = "FINALIZADO";
	
	public TurmaBean() {
		status.put(INICIALIZADO_KEY, INICIALIZADO);
		status.put(NAO_INICIALIZADO_KEY, NAO_INICIALIZADO);
		status.put(FINALIZADO_KEY, FINALIZADO);
	}
	
	public String novo(){
		this.turma = new Turma();
		this.turma.setStatus(NAO_INICIALIZADO_KEY);
		ELFlash.getFlash().put(SELECTED_TURMA, turma);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterTurma";
	}
	
	public String salvar(){
		turmaRN = new TurmaRN();
		if(turma.getId() == null){
			turma.setStatus(NAO_INICIALIZADO_KEY);
		}
		turmaRN.salvar(this.turma);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.turma = null;
		return "turma";
	}
	
	public String excluir(){
		turmaRN = new TurmaRN();
		try{
			turmaRN.excluir(this.turma);
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.turma = null;
			this.lista = null;
			return null;
		}catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.turma = null;
			return null;
		}
	}

	public List<Turma> getLista(){
		if(this.lista == null){
			turmaRN = new TurmaRN();
			this.lista = turmaRN.listar();
		}
		return this.lista;
	}
	
	public String editar(String pagina){
		return pagina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getStatusNome(Character key) {
		return status.get(key).toString();
	}
	
}
