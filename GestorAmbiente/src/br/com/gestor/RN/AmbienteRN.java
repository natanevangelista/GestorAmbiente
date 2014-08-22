package br.com.gestor.RN;

import java.util.List;

import br.com.gestor.DAO.DAOFactory;
import br.com.gestor.DAOHibernate.AmbienteDAOHibernate;
import br.com.gestor.entidade.Ambiente;
import br.com.gestor.web.util.DAOException;
import br.com.gestor.web.util.RNException;

/**
 * Regras de negocio para ambiente
 * @author Natan
 *
 */
public class AmbienteRN {

	private AmbienteDAOHibernate ambienteDAO;
	
	public AmbienteRN(){
		this.ambienteDAO = (AmbienteDAOHibernate) DAOFactory.criarAmbienteDAO();
	}
	
	/**
	 * metodo para inclusão de Ambiente no dependendo das regras aqui constantes
	 * @param ambiente
	 * @throws DAOException 
	 */
	public void salvar(Ambiente ambiente) throws DAOException{
		Long id = ambiente.getId();           //pega o código oculto na página
		if(id == null || id == 0){               //se código nullo quer dizer inserção de novo ambiente
			this.ambienteDAO.salvar(ambiente);   //chama o salvar no AmbienteDAO
			
			//cria no banco as datas para agendamento do ambiente cadastrado
			AgendamentoRN agendamentoRN = new AgendamentoRN();		
			agendamentoRN.salvaAmbienteVazio(ambiente);
		
		} else {
			this.ambienteDAO.atualizar(ambiente);//aqui somente atualiza o ambiente
		}
	}
	
	public void excluir(Ambiente ambiente) throws RNException{     
		this.ambienteDAO.excluir(ambiente);     
	}
	
	public List<Ambiente> listar(){
		return (List<Ambiente>) this.ambienteDAO.listar();
	}

	public List<Ambiente> listarOrderByIdentificao(){
		return (List<Ambiente>) this.ambienteDAO.listarOrderByIdentificao();
	}
	
	public Ambiente carregar(Long ambiente){
		return (Ambiente) this.ambienteDAO.carregar(ambiente);
	}

}
