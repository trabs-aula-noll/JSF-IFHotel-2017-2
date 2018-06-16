package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;

@Stateless
public class ReservaDAO extends BaseDAO<Reserva, Long>{

	private static final long serialVersionUID = 8759031555217807195L;

	public List<Reserva> buscaPorData(Date data){
		List<Reserva> query = em.createQuery(
				"SELECT reserva FROM Reserva reserva WHERE reserva.data = :data", Reserva.class
				).setParameter("data", data)
				.getResultList();

		return query;
	}
}
