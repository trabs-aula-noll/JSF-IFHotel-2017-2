package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarQuartoService {
	
	@Inject
	private QuartoDAO quartoDAO;
	
	@Inject
	private Logger log;
	
	@SuppressWarnings("unchecked")
	public List<Quarto> buscaNumero(String numero) {
		if (numero != null && numero.length() > 0) 
			return quartoDAO.buscaPorNumero(numero);
		else
			return quartoDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quarto> buscaTipo(TipoDeQuarto tipo) {
		if (tipo != null) 
			return quartoDAO.buscaPorTipo(tipo);
		else
			return quartoDAO.lista();
	}
	
	public List<Quarto> buscaDisponiveis(){
		return quartoDAO.buscaQuartosDisponiveis();
	}
	
	public boolean salva(Quarto quarto){
		
		log.info("Salvando " + quarto.getNumero());
		
		if (quarto.getId() != null) {
			quartoDAO.atualiza(quarto);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Quarto.atualizado",quarto.getNumero());
			return true;
			
		    }else{
			    quartoDAO.insere(quarto);
				Mensagens.define(FacesMessage.SEVERITY_INFO, "Quarto.cadastrado",quarto.getNumero());
				log.info("Salvo " + quarto.getNumero() + " com id " + quarto.getId());
				return true;
			}
		} 

	
	public void exclui(Quarto quarto) {
		quartoDAO.exclui(quarto.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Quarto.excluido",quarto.getNumero());
		log.info("Excluido " + quarto.getNumero() + " com id " + quarto.getId());
	}


	public Quarto get(Long id) {
		return quartoDAO.busca(id);
	}

}
	
