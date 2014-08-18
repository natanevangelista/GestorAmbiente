package br.com.gestor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gestor.RN.TurmaRN;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.MensagemUtil;

@ManagedBean(name="turmaPesquisaBean")
@ViewScoped
public class TurmaPesquisaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Turma> lista = new ArrayList<Turma>();
	private TurmaRN turmaRN;
	private Turma turma = new Turma();
	private Turma turmaSelecionada;
	
	public String pesquisarTurma(Turma turma){
		turmaRN = new TurmaRN();
		lista = turmaRN.findByParametros(turma);
		this.turma = new Turma();
		if(lista.isEmpty()){
			MensagemUtil.mensagemAtencao("operacao_sucesso");
			return null;
		}
		return null;
	}

	public List<Turma> getLista() {
		return lista;
	}

	public void setLista(List<Turma> lista) {
		this.lista = lista;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}
	
	
}
