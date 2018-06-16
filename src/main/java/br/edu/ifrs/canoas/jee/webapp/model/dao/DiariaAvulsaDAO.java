package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;

@Stateless
public class DiariaAvulsaDAO extends BaseDAO<DiariaAvulsa, Long> {
	
	private static final long serialVersionUID = 1L;

	public List<DiariaAvulsa> listaDiariasAvulsas(){
		List<DiariaAvulsa> query = em.createQuery(
				"SELECT diaria FROM DiariaAvulsa diaria",
				DiariaAvulsa.class)
				.getResultList();
		
		return query;
	}
	
	public List<DiariaAvulsa> buscaFiltrada(){ 
		List<DiariaAvulsa> query = em.createQuery(
				"SELECT diaria FROM DiariaAvulsa diaria WHERE diaria.data >= :data", DiariaAvulsa.class)
				.setParameter("data", new Date(), TemporalType.DATE)
				.getResultList(); 
		
		Collections.sort(query);
		
		return query;
	}
}
