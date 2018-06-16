package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Pessoa;

public class PessoaDAO extends BaseDAO<Pessoa, Long> {
	
	private static final long serialVersionUID = 1440129756486784807L;

	public List<Pessoa> listaTodasPessoas(){
		List<Pessoa> pessoas = em.createQuery(
				"SELECT p FROM Pessoa p", Pessoa.class
				).getResultList();
		em.close();
		
		return pessoas;
	}
}
