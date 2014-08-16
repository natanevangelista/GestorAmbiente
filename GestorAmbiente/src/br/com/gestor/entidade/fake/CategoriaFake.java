package br.com.gestor.entidade.fake;

import br.com.gestor.entidade.Categoria;

public class CategoriaFake {

	public CategoriaFake(Categoria categoria) {
		descricao = categoria.getDescricao();
	}
	
	public String descricao;
}
