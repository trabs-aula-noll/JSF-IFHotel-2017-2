package br.edu.ifrs.canoas.jee.webapp.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarDiariaAvulsaService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaFisicaService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaJuridicaService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarQuartoService;

@Named
@RequestScoped
public class GerenciarDiariaAvulsaMB {	
	@Inject
    private GerenciarDiariaAvulsaService gerenciarDiariaAvulsaService;	
	@Inject
	private GerenciarQuartoService gerenciarQuartoService;	
	@Inject
	private GerenciarPessoaJuridicaService gerenciarPJService;
	@Inject
	private GerenciarPessoaFisicaService gerenciarPFService;
	@Inject
	private DiariaAvulsa diariaAvulsa;
	@Inject
	private PessoaFisica pf;
	@Inject
	private PessoaJuridica pj;
	
	private List<DiariaAvulsa> diariasAvulsas;
	private List<Quarto> quartos;
	private List<PessoaJuridica> pessoasJuridicas;
	private List<PessoaFisica> pessoasFisicas;
	
	private String tipo = "PJ";
	private boolean rendered;
	
	public String salva() {
		if(tipo.equals("PF")) {
			diariaAvulsa.setCliente(pf);
		}else {
			diariaAvulsa.setCliente(pj);
		}
		
		diariaAvulsa.setHospedes(pessoasFisicas);
		gerenciarDiariaAvulsaService.salvaDiaria(diariaAvulsa);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		diariasAvulsas = gerenciarDiariaAvulsaService.busca();	
		quartos = gerenciarQuartoService.buscaDisponiveis();
		pessoasJuridicas = gerenciarPJService.lista();
		pessoasFisicas = gerenciarPFService.busca(null);
		selectListener();
    }
	
	
	public void exclui() {
		gerenciarDiariaAvulsaService.exclui(diariaAvulsa);
		this.init();
	}
	
	public void edita(DiariaAvulsa d) {
		this.diariaAvulsa = d;
		if (this.diariaAvulsa.getCliente() instanceof PessoaFisica) {
			pf = (PessoaFisica) this.diariaAvulsa.getCliente();
			tipo = "PF";
		}else {
			pj = (PessoaJuridica) this.diariaAvulsa.getCliente();
			tipo = "PJ";
		}
	}

	public DiariaAvulsa getDiariaAvulsa() {
		return diariaAvulsa;
	}

	public void setDiariaAvulsa(DiariaAvulsa diariaAvulsa) {
		this.diariaAvulsa = diariaAvulsa;
	}
	
	public List<DiariaAvulsa> getDiariasAvulsas() {
		return diariasAvulsas;
	}
	
	public List<Quarto> getQuartos(){
		return quartos;
	}

	public void setDiariasAvulsas (List<DiariaAvulsa> diariasAvulsas) {
		this.diariasAvulsas = diariasAvulsas;
	}
	
	public void setQuartos (List<Quarto> quartos){
		this.quartos = quartos;
	}
	
	public List<PessoaJuridica> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

	public void setPessoasJuridicas(List<PessoaJuridica> pessoasJuridicas) {
		this.pessoasJuridicas = pessoasJuridicas;
	}

	public void selectListener(){
		setRendered(getTipo().contains("PJ"));
	}
	
	public String limpa() {
		diariaAvulsa = new DiariaAvulsa();
		tipo = "PJ";
		return "/public/diaria-avulsa.jsf?facesRedirect=true";
	}

	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	
	public PessoaFisica getPf() {
		return pf;
	}

	public void setPf(PessoaFisica pf) {
		this.pf = pf;
	}

	public PessoaJuridica getPj() {
		return pj;
	}

	public void setPj(PessoaJuridica pj) {
		this.pj = pj;
	}

	public List<PessoaFisica> getPessoasFisicas(){
		return pessoasFisicas;
	}
	
	public void setPessoasFisicas(List<PessoaFisica> pessoasFisicas){
		this.pessoasFisicas = pessoasFisicas;
	}
}
