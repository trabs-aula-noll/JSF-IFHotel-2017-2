package br.edu.ifrs.canoas.jee.webapp.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaFisicaService;

public class GerenciarPessoaFisicaMB {
	@Inject
    private GerenciarPessoaFisicaService gerenciarPFService;	
	@Inject
	private PessoaFisica pf;
	
	private List<PessoaFisica> pfs;
		
	public String salva() {
		gerenciarPFService.salvaPF(pf);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		pfs = gerenciarPFService.busca(null);	
    }
	
	public void exclui() {
		gerenciarPFService.exclui(pf);
		this.init();
	}
	
	public void edita(PessoaFisica pf) {
		this.pf = pf;
	}

	public PessoaFisica getUsuario() {
		return pf;
	}

	public void setPEssoasFisicas(PessoaFisica pf) {
		this.pf = pf;
	}

	public List<PessoaFisica> getPessoasFisicas() {
		return pfs;
	}

	public void setPessoasFisicas(List<PessoaFisica> pfs) {
		this.pfs = pfs;
	}
	public String limpa() {
		pf = new PessoaFisica();
		return "/public/usuario.jsf?facesRedirect=true";
	}

}
