package br.edu.ifrs.canoas.jee.webapp.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarQuartoService;

@Named
@RequestScoped
public class GerenciarQuartoMB {
	
	@Inject
    private GerenciarQuartoService gerenciarQuartoService;	
	
	@Inject
	private Quarto quarto;
	
	private List<Quarto> quartos;
		
	public String salva() {
		gerenciarQuartoService.salva(quarto);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		quartos = gerenciarQuartoService.buscaNumero(null);	
    }
	
	public void exclui() {
		gerenciarQuartoService.exclui(quarto);
		this.init();
	}
	
	public void edita(Quarto q) {
		this.quarto = q;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}

	public void setQuarto(List<Quarto> quartos) {
		this.quartos = quartos;
	}
	public String limpa() {
		quarto = new Quarto();
		return "/webapp/public/quarto.jsf";
	}

}
