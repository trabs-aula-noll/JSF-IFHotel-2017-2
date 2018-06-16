package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Endereco;

@Stateless
public class EnderecoDAO extends BaseDAO<Endereco, Long> {
	public List<Endereco> buscaPorCep(String cep){
		List<Endereco> query = em.createQuery(
				"SELECT endereco FROM Endereco endereco WHERE endereco.cep = :cep", Endereco.class
				).setParameter("cep", cep)
				.getResultList();
		return query;
	}
}