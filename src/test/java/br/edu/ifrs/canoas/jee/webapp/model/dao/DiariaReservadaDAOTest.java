package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;

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
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;

@RunWith(Arquillian.class)
public class DiariaReservadaDAOTest {

	@Inject
	DiariaReservadaDAO drDAO;
	
	@Inject
	QuartoDAO qDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(DiariaReservadaDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@Test
	public void lista_diaria_reservada() {
		
		DiariaReservada dr = new DiariaReservada();
		DiariaReservada dr1 = new DiariaReservada();
		dr.setQntdDias(4);
		dr1.setQntdDias(4);
		
		Quarto q1 = new Quarto();
		q1.setDescricao("sdadas");
		q1.setNumero("555");
		q1.setSituacao("Disponível");
		q1.setTipo(TipoDeQuarto.PRESIDENCIAL);
		
		qDAO.insere(q1);
		dr.setQuarto(q1);
		dr1.setQuarto(q1);
		
		drDAO.insere(dr);
		drDAO.insere(dr1);
		
		assertNotNull(drDAO.listaDiariasReservadas());
	}

}
