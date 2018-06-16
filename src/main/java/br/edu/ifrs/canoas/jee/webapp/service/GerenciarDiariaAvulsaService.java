package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarDiariaAvulsaService {
	@Inject
	private DiariaAvulsaDAO diariaDAO;
	
	@Inject
	private Logger log;
	
	public boolean salvaDiaria(DiariaAvulsa d){
		log.info("Salvando diária em "+d.getData());
	
		if(d.getId() != null){
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Diaria.atualizada.sucesso", d.getData());
			diariaDAO.atualiza(d);
			return true;
		}
		
		diariaDAO.insere(d);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Diaria.cadastrada.sucesso", d.getData());
		log.info("Salva diária em " + d.getData() + " com id " + d.getId());
		return true;
	}
	
	public List<DiariaAvulsa> busca(){
		return diariaDAO.buscaFiltrada();
	}
	
	public void exclui(DiariaAvulsa d){
		diariaDAO.exclui(d.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Diaria.excluida.sucesso",d.getData());
		log.info("excluída diária do dia "+d.getData()+" com id "+d.getId());
	}
	
	public DiariaAvulsa get(long id){
		return (DiariaAvulsa) diariaDAO.busca(id);
	}
}
