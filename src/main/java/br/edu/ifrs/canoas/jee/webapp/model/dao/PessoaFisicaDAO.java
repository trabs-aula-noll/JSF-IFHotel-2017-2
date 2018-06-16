package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;

@Stateless
public class PessoaFisicaDAO extends BaseDAO<PessoaFisica, Long> {
	
	private static final long serialVersionUID = -1691949096247206266L;

	public PessoaFisica buscaPorDocumento(String documento){
		List<PessoaFisica> pfs;
		
		if(documento == null){
			return null;
		}
		
		if(documento.length() == 11){
			pfs = em.createQuery(
					"SELECT pf FROM PessoaFisica pf WHERE pf.cpf = :cpf", PessoaFisica.class
					).setParameter("cpf", documento)
					.getResultList();
		}else{
			pfs = em.createQuery(
					"SELECT pf FROM PessoaFisica pf WHERE pf.rg = :rg", PessoaFisica.class
					).setParameter("rg", documento)
					.getResultList();
		}
			
		return (pfs.size() == 0) ? null : pfs.get(0);
	}
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> buscaPorEmail(String email) {
		return em.createQuery("SELECT pf " + "FROM PessoaFisica pf " + "WHERE lower(pf.email) = :email ")
				.setParameter("email", email).getResultList();
	}
	public List<PessoaFisica> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT pf "
		         + "FROM PessoaFisica pf "
		         + "WHERE lower(pf.email) = :email "
		         + " or lower (pf.nome) = :nome ")
		         .setParameter("email", criterio.trim().toLowerCase())
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .getResultList();
	}
	
}
