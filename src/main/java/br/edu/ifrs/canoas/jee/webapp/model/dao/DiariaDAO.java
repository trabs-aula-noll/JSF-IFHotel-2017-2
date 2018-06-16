package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Diaria;

@Stateless
public class DiariaDAO extends BaseDAO<Diaria, Long>{
	
	private static final long serialVersionUID = 1L;

	public List<Diaria> buscaPorData(Date data){
		List<Diaria> query = em.createQuery(
				"SELECT diaria FROM Diaria diaria WHERE diaria.data = :data", Diaria.class
				).setParameter("data", data)
				.getResultList();
		
		return query;
	}
}
