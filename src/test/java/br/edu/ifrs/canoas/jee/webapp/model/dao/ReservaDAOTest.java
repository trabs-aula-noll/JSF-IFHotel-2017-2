package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;

@RunWith(Arquillian.class)
public class ReservaDAOTest{

		@Inject
		ReservaDAO rDAO;
		
		@Inject
		PessoaFisicaDAO pfDAO;
		
		@Inject
		PessoaJuridicaDAO pjDAO;
		
		@Inject
		QuartoDAO qDAO;
		
		@Inject
		DiariaReservadaDAO drDAO;
		
		@Deployment
	    public static Archive<?> createTestArchive() {
			 return ShrinkWrap.create(WebArchive.class, "test.war")
		                .addClasses(ReservaDAO.class)
		                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
		                .addPackages(true, "org.apache.commons")
		                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
		                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	    }
		
		@Test
		public void testa_a_persistencia_da_reserva_em_branco () {	
			Reserva reserva = new Reserva();
			reserva.setValor(198.90);
			
			PessoaFisica pf = new PessoaFisica();
			pfDAO.insere(pf);
			
			PessoaJuridica pj = new PessoaJuridica();
			pjDAO.insere(pj);
			
			reserva.setCliente(pf);
			reserva.setEmpresa(pj);
			
			Quarto q1 = new Quarto();
			q1.setDescricao("sdadas");
			q1.setNumero("555");
			q1.setSituacao("Disponível");
			q1.setTipo(TipoDeQuarto.PRESIDENCIAL);
			qDAO.insere(q1);
			
			DiariaReservada dr = new DiariaReservada();
			dr.setQntdDias(4);
			dr.setQuarto(q1);
			drDAO.insere(dr);
			
			
			reserva.setDiariaReservada(dr);
			
			rDAO.insere(reserva);
			assertNotNull(reserva.getId());
		
		}
		
		@Test
		public void busca_por_data() throws ParseException{
			
			Reserva r1 = new Reserva();
			SimpleDateFormat dt = new SimpleDateFormat( "dd/MM/yyyy" );
			r1.setData(dt.parse("20/12/2017"));
			r1.setValor(198);
			
			PessoaFisica pf = new PessoaFisica();
			pfDAO.insere(pf);
			
			PessoaJuridica pj = new PessoaJuridica();
			pjDAO.insere(pj);
			
			r1.setCliente(pf);
			r1.setEmpresa(pj);
			
			
			Quarto q1 = new Quarto();
			q1.setDescricao("sdadas");
			q1.setNumero("555");
			q1.setSituacao("Disponível");
			q1.setTipo(TipoDeQuarto.PRESIDENCIAL);
			qDAO.insere(q1);
			
			DiariaReservada dr = new DiariaReservada();
			dr.setQntdDias(4);
			dr.setQuarto(q1);
			drDAO.insere(dr);
			
			
			r1.setDiariaReservada(dr);
			rDAO.insere(r1);
			
			assertNotNull(rDAO.buscaPorData(dt.parse("20/12/2017")));
			
		}
		
		@Test
		public void busca_por_data_vazia(){
			
			assertTrue(rDAO.buscaPorData(null).isEmpty());
		}
		
}
