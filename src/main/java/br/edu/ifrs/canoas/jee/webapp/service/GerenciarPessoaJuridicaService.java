package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;

@Stateless
public class GerenciarPessoaJuridicaService {
	@Inject
	private PessoaJuridicaDAO pfDAO;
	
	public List<PessoaJuridica> lista() {
		return pfDAO.lista();
	}
}
