package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;

@Stateless
public class PessoaJuridicaDAO extends BaseDAO<PessoaJuridica, Long> {

	private static final long serialVersionUID = -5816640691145539236L;

	public PessoaJuridica buscaPorCnpj(String cnpj){
		
		if(cnpj == null){
			return null;
		}
		
		List<PessoaJuridica> pjs = em.createQuery(
				"SELECT pj FROM PessoaJuridica pj WHERE pj.cnpj = :cnpj", PessoaJuridica.class
				).setParameter("cnpj", cnpj)
				.getResultList();
		
		
		return (pjs.size() == 0) ? null : pjs.get(0);
	}
	
}
