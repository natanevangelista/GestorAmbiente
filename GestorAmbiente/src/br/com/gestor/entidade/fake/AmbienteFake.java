package br.com.gestor.entidade.fake;

import br.com.gestor.entidade.Ambiente;

public class AmbienteFake {

	public AmbienteFake(Ambiente ambiente) {
		descricao = ambiente.getDescricao();
    	identificacao = ambiente.getIdentificacao();
    	status = ambiente.isStatus();
    	categoria = ambiente.getCategoria().getDescricao();
	}
	
	public Long id;
	
	public String identificacao;

	public int qtdVagas;
	
	public String descricao;
	
	public String observacao;

	public boolean status;

	public String categoria;
}
