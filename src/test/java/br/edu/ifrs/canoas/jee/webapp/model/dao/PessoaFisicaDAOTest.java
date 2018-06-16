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

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;

@RunWith(Arquillian.class)
public class PessoaFisicaDAOTest {
	
	
	@Inject
	PessoaFisicaDAO pfDAO;
	

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PessoaFisicaDAO.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@Test
	public void testa_busca_por_documento_cpf() {
		
		PessoaFisica pf = new PessoaFisica();
		pf.setCpf("04071489567");
		
		pfDAO.insere(pf);
		
		assertNotNull(pfDAO.buscaPorDocumento("04071489567"));
	}
	
	@Test
	public void testa_busca_por_documento_rg(){
		
		PessoaFisica pf = new PessoaFisica();
		pf.setRg("0123456789");
		
		pfDAO.insere(pf);
		
		assertNotNull(pfDAO.buscaPorDocumento("0123456789"));
	}
	
	@Test
	public void testa_busca_por_documento_invalido(){
		
		assertNull(pfDAO.buscaPorDocumento("97653454"));
	}
	
	@Test
	public void testa_busca_por_documento_null(){
			
			assertNull(pfDAO.buscaPorDocumento(null));							
	}
}
