package br.com.gestor.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sun.faces.context.flash.ELFlash;

import br.com.gestor.RN.EventoRN;
import br.com.gestor.RN.TurmaRN;
import br.com.gestor.entidade.Evento;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

@ManagedBean(name="turmaBean")
@RequestScoped
public class TurmaBean extends AbstractBean {

	private static final String SELECTED_TURMA = "selectedTurma";
	private static final String TITULO_CADASTRAR = "Cadastrar Turma";
	private static final String TITULO_EDITAR = "Editar Turma";

	private Turma turma = new Turma();
	private List<Turma> lista;
	private TurmaRN turmaRN;
	
	public String novo(){
		this.turma = new Turma();
		ELFlash.getFlash().put(SELECTED_TURMA, turma);
		ELFlash.getFlash().put(TITULO_PAGINA, TITULO_CADASTRAR);
		return "manterTurma";
	}
	
	public String salvar(){
		turmaRN = new TurmaRN();
		turmaRN.salvar(this.turma);
		MensagemUtil.mensagemAtencao("operacao_sucesso");
		this.turma = null;
		return "Turma";
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
	
}
