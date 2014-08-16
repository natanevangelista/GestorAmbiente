package br.com.gestor.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * Classe responsável por realizar a contagem dos Caracteres digitados
 * @author Natan
 *
 */
@ManagedBean(name="contarCaracteresUtil")
public class ContarCaracteresUtil implements Serializable{

	private int contador = 0;

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	
	public void incrementa(){
		contador = contador + 1;
	}

}
