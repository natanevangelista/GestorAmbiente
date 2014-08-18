package br.com.gestor.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestor.RN.TurmaRN;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean(name="turmaBean")
@SessionScoped
public class TurmaBean extends AbstractBean {

	private static final String SELECTED_TURMA = "selectedTurma";
	private static final String TITULO_CADASTRAR = "Cadastrar Turma";
	private static final String TITULO_EDITAR = "Editar Turma";

	private Turma turma = new Turma();
	private Turma turmaPesquisa = new Turma();
	private Turma turmaSelecionada;
	private List<Turma> lista = new ArrayList<Turma>();
	private TurmaRN turmaRN;
	
	private static final HashMap<Character, String> status = new HashMap<Character, String>();
	private static final Character INICIALIZADO_KEY = 'I';
	private static final Character NAO_INICIALIZADO_KEY = 'N';
	private static final Character FINALIZADO_KEY = 'F';
	private static final Character SEM_AGENDA__KEY = 'S';
	
	private static final String INICIALIZADO = "INICIALIZADA";
	private static final String NAO_INICIALIZADO = "NÃO INICIALIZADA";
	private static final String FINALIZADO = "FINALIZADA";
	private static final String SEM_AGENDA = "SEM AGENDAMENTO";
	
	
	public TurmaBean() {
		status.put(INICIALIZADO_KEY, INICIALIZADO); //azul
		status.put(NAO_INICIALIZADO_KEY, NAO_INICIALIZADO); //preta
		status.put(FINALIZADO_KEY, FINALIZADO); // preta
		status.put(SEM_AGENDA__KEY, SEM_AGENDA); //vermelha
	}
	
	public String novo(){
		this.turma = new Turma();
		this.turma.setStatus(SEM_AGENDA__KEY);
		criarNomeTurma(this.turma);
		ELFlash.getFlash().put(SELECTED_TURMA, turma);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterTurma";
	}
	
	public String salvar(){
		turmaRN = new TurmaRN();
		turmaRN.salvar(this.turma);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.turma = null;
		this.lista = null;
		return "turmaPesquisa";
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
		return this.lista;
	}
	
	public String editar(String pagina){
		return pagina;
	}

	/**
	 * Chama a regra que cria o Nome da Turma
	 * 
	 * @param turma
	 */
	private void criarNomeTurma(Turma turma){
		turmaRN = new TurmaRN();
		turma.setNome(turmaRN.criarNomeTurma());
	}
	
	public void pesquisarTurma(Turma turma){
		turmaRN = new TurmaRN();
		lista = turmaRN.findByParametros(turma);
		turmaPesquisa = new Turma();
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getStatusNome(Character key) {
		String c= null;
		try{
			 c = status.get(key).toString();
		} catch(NullPointerException e){
			return status.get('S').toString();
		}
		return c;
	}

	public Turma getTurmaPesquisa() {
		return turmaPesquisa;
	}

	public void setTurmaPesquisa(Turma turmaPesquisa) {
		this.turmaPesquisa = turmaPesquisa;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}
    
}
