package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;

@Stateless
public class DiariaReservadaDAO extends BaseDAO<DiariaReservada, Long> {

	private static final long serialVersionUID = 1L;

	public List<DiariaReservada> listaDiariasReservadas(){
		List<DiariaReservada> query = em.createQuery(
				"SELECT diaria FROM DiariaReservada diaria",
				DiariaReservada.class)
				.getResultList();
		
		return query;
	}
}
