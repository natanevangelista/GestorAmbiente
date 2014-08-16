package br.com.gestor.RN;

import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.CategoriaDAOHibernate;
import br.com.gestor.entidade.Categoria;
import br.com.gestor.web.util.RNException;

public class CategoriaRN {
	
	private CategoriaDAOHibernate categoriaDAO;
	
	public CategoriaRN() {
		this.categoriaDAO = (CategoriaDAOHibernate) DAOFactory.criarCategoriaDAO();
	}
	
	public void salvar(Categoria categoria){
		Long id = categoria.getId();				//pega o código oculto na página
		if(id == null || id == 0){					//se código nullo quer dizer inserção de novo ambiente
			this.categoriaDAO.salvar(categoria);	//chama o salvar no AmbienteDAO	
		} else { 
			this.categoriaDAO.atualizar(categoria);
		}	
	}
	
	public void excluir(Categoria categoria) throws RNException{
		this.categoriaDAO.excluir(categoria);
	}
	
	public List<Categoria> listar(){
		return (List<Categoria>) this.categoriaDAO.listar();
	}

	public Categoria carregar(Long categoria){
		return (Categoria) this.categoriaDAO.carregar(categoria);
	}
	
	public List<Categoria> listarOrderByDescricao(){
		return (List<Categoria>) this.categoriaDAO.listarOrderByDescricao();
	}
}
