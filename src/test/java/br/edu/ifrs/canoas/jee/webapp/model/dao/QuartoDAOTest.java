package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.canoas.jee.webapp.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;

@RunWith(Arquillian.class)
public class QuartoDAOTest {

	@Inject
	QuartoDAO qDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(QuartoDAO.class, TipoDeQuarto.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@Test
	public void busca_por_numero() {
		
		Quarto q1 = new Quarto();
		q1.setNumero("201");
		q1.setDescricao("sdadas");
		q1.setNumero("555");
		q1.setSituacao("Disponível");
		q1.setTipo(TipoDeQuarto.PRESIDENCIAL);
		qDAO.insere(q1);
		
		assertNotNull(qDAO.buscaPorNumero("201"));
		
	}
	
	@Test
	public void busca_por_numero_vazio(){
		
		assertTrue(qDAO.buscaPorNumero(null).isEmpty());
		
	}

	@Test 
	public void busca_por_tipo (){
		
		Quarto q2 = new Quarto();
		q2.setTipo(TipoDeQuarto.STANDARD);
		q2.setDescricao("sdadas");
		q2.setNumero("555");
		q2.setSituacao("Disponível");
		qDAO.insere(q2);
		
		assertNotNull(qDAO.buscaPorTipo(TipoDeQuarto.STANDARD));
	}
	
	@Test
	public void busca_por_tipo_vazio(){
		
		assertTrue(qDAO.buscaPorTipo(null).isEmpty());
	}
}
