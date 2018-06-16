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

import br.edu.ifrs.canoas.jee.webapp.model.entity.Endereco;

@RunWith(Arquillian.class)
public class EnderecoDAOTest {

	@Inject
	EnderecoDAO eDAO;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(EnderecoDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Test
	public void busca_endereco_por_cep() {
		
		Endereco e1 = new Endereco();
		e1.setCep("92032-360");
		
		eDAO.insere(e1);
		assertNotNull (eDAO.buscaPorCep("92032-360"));
	}
	
	@Test
	public void busca_endereco_por_cep_vazio(){
		
		assertTrue (eDAO.buscaPorCep(null).isEmpty());
	}

}
