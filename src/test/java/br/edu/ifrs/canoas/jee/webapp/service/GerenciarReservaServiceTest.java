package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.*;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.canoas.jee.webapp.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.ReservaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarReservaServiceTest {

	@Inject
	GerenciarReservaService gerenciarReservaService;
	
	@Inject
	ReservaDAO rDAO;
	
	@Inject
	DiariaReservadaDAO drDAO;
	
	@Inject
	QuartoDAO qDAO;
	
	@Inject
	PessoaFisicaDAO pfDAO;
	
	@Inject
	PessoaJuridicaDAO pjDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarReservaService.class, ReservaDAO.class, DiariaReservadaDAO.class, QuartoDAO.class, PessoaFisicaDAO.class, PessoaJuridicaDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
    }

	@Test
	public void salvaReserva() {
			
			Reserva reserva = criaReserva();
			gerenciarReservaService.salvaReserva(reserva);
		}
	
	public Reserva criaReserva(){
		Quarto q1 = new Quarto();
		q1.setDescricao("Quarto que não tinha paredes.");
		q1.setNumero("78");
		q1.setSituacao("Disponível");
		q1.setTipo(TipoDeQuarto.STANDARD);
		qDAO.insere(q1);
		
		
		DiariaReservada dr = new DiariaReservada();
		dr.setQntdDias(5);
		dr.setQuarto(q1);
		drDAO.insere(dr);
		
		PessoaFisica pf = new PessoaFisica();
		pf.setCpf("02158012563");
		pfDAO.insere(pf);
		
		PessoaJuridica pj = new PessoaJuridica();
		pj.setCnpj("1236546548");
		pjDAO.insere(pj);
		
		Reserva r = new Reserva();
		r.setDiariaReservada(dr);
		r.setValor(199);
		r.setCliente(pf);
		r.setEmpresa(pj);
		
		return r;
		
		
	}

}
