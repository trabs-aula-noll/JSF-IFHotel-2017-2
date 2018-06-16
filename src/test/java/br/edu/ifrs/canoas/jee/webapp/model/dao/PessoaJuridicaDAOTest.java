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

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;

@RunWith(Arquillian.class)
public class PessoaJuridicaDAOTest {
	
	@Inject
	PessoaJuridicaDAO pjDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PessoaJuridicaDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Test
	public void busca_por_cnpj(){
		
		PessoaJuridica pj = new PessoaJuridica();
		pj.setCnpj("123456789");
		
		pjDAO.insere(pj);
		
		assertNotNull(pjDAO.buscaPorCnpj("123456789"));
		
	}
	
	@Test
	public void busca_por_cnpj_invalido(){
		assertNull(pjDAO.buscaPorCnpj("5834832"));
	}
	
	@Test
	public void busca_por_cnpj_null(){
		assertNull(pjDAO.buscaPorCnpj(null));
	}

}
