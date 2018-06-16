package br.edu.ifrs.canoas.jee.webapp.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

public class GerenciarPessoaFisicaService {

	@Inject
	private PessoaFisicaDAO pfDAO;
	
	@Inject
	private Logger log;

	public boolean salvaPF(PessoaFisica pf) {

		log.info("Salvando " + pf.getNome());
		
		if (pf.getId() != null) {
			pfDAO.atualiza(pf);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "PessoaFisica.atualizado.sucesso",pf.getEmail());
			return true;
		}
		
		int qtdEmailCadastrado = this.validaEmail(pf);
		
		if (qtdEmailCadastrado == 0) {
				
				pfDAO.insere(pf);
				Mensagens.define(FacesMessage.SEVERITY_INFO, "PessoaFisica.cadastro.sucesso",pf.getEmail());
				log.info("Salvo " + pf.getNome() + " com id " + pf.getId());
				return true;
		} 
		
		log.info("Problema com email duplicado do usuário " + pf.getNome() + " - email " + pf.getEmail());
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "PessoaFisica.email.erro.cadastrado",pf.getEmail());
		return false;
	}


	/**
	 * retorna a quantidade de e-mails cadastrados no banco iguais ao informado.
	 * @param pf
	 * @return int
	 */
	private int validaEmail(PessoaFisica pf) {
		if (pf == null || StringUtils.isBlank(pf.getEmail()))
			return -1;

		return pfDAO
				.buscaPorEmail(pf.getEmail().trim().toLowerCase())
				.size();
	}

	public List<PessoaFisica> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return pfDAO.buscaPorCriterio(criterio);
		else
			return pfDAO.lista();
	}


	public void exclui(PessoaFisica pf) {
		pfDAO.exclui(pf.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "PessoaFisica.excluido.sucesso",pf.getNome());
		log.info("Excluido " + pf.getNome() + " com id " + pf.getId());
	}


	public PessoaFisica get(Long id) {
		return pfDAO.busca(id);
	}
}
