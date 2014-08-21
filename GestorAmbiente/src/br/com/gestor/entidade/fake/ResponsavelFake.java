package br.com.gestor.entidade.fake;

import br.com.gestor.entidade.Responsavel;

public class ResponsavelFake {

	public String nome;
	public boolean status;
	
	public ResponsavelFake(Responsavel responsavel) {
		nome = responsavel.getNome();
		status = responsavel.isStatus();
	}
}
