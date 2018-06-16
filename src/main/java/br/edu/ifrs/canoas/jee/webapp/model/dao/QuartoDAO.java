package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import br.edu.ifrs.canoas.jee.webapp.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;

@SuppressWarnings("serial")
@Stateless
public class QuartoDAO extends BaseDAO<Quarto, Long> {

	public List<Quarto> buscaPorNumero(String numero){
		List<Quarto> query = em.createQuery(
				"SELECT quarto FROM Quarto quarto WHERE quarto.numero = :numero", Quarto.class
				).setParameter("numero", numero)
				.getResultList();
		return query;
	}
	
	public List<Quarto> buscaPorTipo(TipoDeQuarto tipo){
		List<Quarto> query = em.createQuery(
				"SELECT quarto FROM Quarto quarto WHERE quarto.tipo = :tipo", Quarto.class
				).setParameter("tipo", tipo)
				.getResultList();
		return query;
	}
	
	public List<Quarto> buscaQuartosDisponiveis(){
		List<Quarto> query = em.createQuery(
				"SELECT quarto FROM Quarto quarto WHERE quarto.id NOT IN ("+
						"SELECT diaria.quarto.id FROM DiariaAvulsa diaria WHERE diaria.data LIKE CONCAT('%',:data,'%')"
						+") AND quarto.id NOT IN ("+
						"SELECT diaria.quarto.id FROM DiariaReservada diaria WHERE diaria.data LIKE CONCAT('%',:data,'%')"+")", Quarto.class
				).setParameter("data", new Date(), TemporalType.DATE)
				.getResultList();
		return query;
	}
}
