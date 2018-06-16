package br.edu.ifrs.canoas.jee.webapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarQuartoService;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarReservaService;

@Named
@RequestScoped
public class GerenciarReservaMB {

	@Inject
    private GerenciarReservaService gerenciarReservaService;
	
	@Inject
	private PessoaFisicaDAO pfDAO;
	
	@Inject
	private PessoaJuridicaDAO pjDAO;
	
	@Inject
	private DiariaReservadaDAO drDAO;
	
	@Inject
	private QuartoDAO qDAO;
	
	@Inject
	private Reserva reserva;
		
	private List<Reserva> reservas;
	
	private List<Quarto> quartos = new ArrayList<Quarto>();
	
	private List<PessoaFisica> pf = new ArrayList<PessoaFisica>();
	private List<PessoaJuridica> pj = new ArrayList<PessoaJuridica>();
	
	private String tipo;
	private boolean rendered = true;
	
	public String salva() {
		
		Quarto quarto = qDAO.busca(reserva.getDiariaReservada().getQuarto().getId());	
		reserva.getDiariaReservada().setQuarto(quarto);
		
		DiariaReservada dr = reserva.getDiariaReservada();
		drDAO.insere(dr);
		reserva.setDiariaReservada(dr);
		
		PessoaFisica cliente = pfDAO.busca(reserva.getCliente().getId());
		reserva.setCliente(cliente);
		
		PessoaJuridica empresa = pjDAO.busca(reserva.getEmpresa().getId());
		reserva.setEmpresa(empresa);
		
		gerenciarReservaService.salvaReserva(reserva);
	
		this.init();
		return limpa();
	}
		
	@PostConstruct
    public void init() {
		reservas = gerenciarReservaService.busca();	
		quartos = qDAO.lista();
		pf = pfDAO.lista();
		pj = pjDAO.lista();
		
    }

	public void exclui() {
		gerenciarReservaService.exclui(reserva);
		this.init();
	}
	
	public void edita(Reserva reserva) {
		this.reserva = reserva;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public List<Quarto> getQuartos(){
		return quartos;
	}

	public void setReservas (List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public void setQuartos (List<Quarto> quartos){
		this.quartos = quartos;
	}
	
	public void selectListener(){
		setRendered(getTipo().contains("PJ"));
	}
	
	public String limpa() {
		
		reserva = new Reserva();
		return "/public/reserva.jsf?facesRedirect=true";
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

	public List<PessoaFisica> getPf() {
		return pf;
	}

	public void setPf(List<PessoaFisica> pf) {
		this.pf = pf;
	}

	public List<PessoaJuridica> getPj() {
		return pj;
	}

	public void setPj(List<PessoaJuridica> pj) {
		this.pj = pj;
	}
	
	

	
}

	

