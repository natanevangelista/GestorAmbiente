package br.com.gestor.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gestor.RN.TurmaRN;
import br.com.gestor.entidade.Turma;
import br.com.gestor.web.util.MensagemUtil;
import br.com.gestor.web.util.RNException;

@ManagedBean(name="turmaPesquisaBean")
@ViewScoped
public class TurmaPesquisaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Turma> lista;
	private TurmaRN turmaRN;
	private Turma turma = new Turma();
	private Turma turmaSelecionada;
	
	public String pesquisarTurma(Turma turma){
		turmaRN = new TurmaRN();
		lista = turmaRN.findByParametros(turma);
		this.turma = new Turma();
		if(lista.isEmpty()){
			MensagemUtil.mensagemAtencao("nenhum_registro");
			return null;
		}
		return null;
	}

	public String excluir(){
		turmaRN = new TurmaRN();
		try{
			turmaRN.excluir(this.turmaSelecionada);
			MensagemUtil.mensagemAtencao("operacao_excluir_sucesso");
			this.turmaSelecionada = null;
			this.lista = null;
			return null;
		}catch(RNException e){
			MensagemUtil.mensagemWarm(e.getMessage());
			this.turma = null;
			return null;
		}
	}
	
	public List<Turma> getLista() {
		if(this.lista == null){
			turmaRN = new TurmaRN();
			this.lista = turmaRN.listar();
		}
		return this.lista;
	}
	
	public List<Turma> getListaPesquisa() {
		return this.lista;
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
