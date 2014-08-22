package br.com.gestor.RN;

import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.ResponsavelDAOHibernate;
import br.com.gestor.entidade.Responsavel;
import br.com.gestor.web.util.DAOException;
import br.com.gestor.web.util.RNException;

public class ResponsavelRN {

	private ResponsavelDAOHibernate responsavelDAO;
	
	public ResponsavelRN() {
		this.responsavelDAO = (ResponsavelDAOHibernate) DAOFactory.criarResponsavelDAO();
	}
	
	public void salvar(Responsavel responsavel) throws DAOException{
		Long id = responsavel.getId();
		if(id == null || id == 0){
			this.responsavelDAO.salvar(responsavel);
		}else{
			this.responsavelDAO.atualizar(responsavel);
		}
	}
	
	public void excluir(Responsavel responsavel) throws RNException{
		this.responsavelDAO.excluir(responsavel);
	}
	
	public List<Responsavel> listar(){
		return (List<Responsavel>) this.responsavelDAO.listar();
	}

	public List<Responsavel> listarOrderByNome(){
		return (List<Responsavel>) this.responsavelDAO.listarOrderByNome();
	}
	
	public Responsavel carregar(Long responsavel) {
		return (Responsavel) this.responsavelDAO.carregar(responsavel);
	}
	
}
