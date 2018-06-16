package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;

@RunWith(Arquillian.class)
public class BaseDAOTest {

	@Inject
	PessoaFisicaDAO pfDAO;
	
	@Inject
	EntityManager em;
	
	@Deployment
	public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PessoaFisicaDAO.class, BaseDAO.class, EntityManager.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addPackages(true, "org.apache.commons")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	
	@Test
	public void testa_salva_pessoaFisica() {
		PessoaFisica pf = criaPessoa();
		
		pfDAO.insere(pf);
		
		assertNotNull(pf.getId());
	}
	
	@Test
	public void testa_encontra_pessoa(){
		
		PessoaFisica pf = criaPessoa();
		
		pfDAO.insere(pf);
		
		PessoaFisica pf2 = pfDAO.busca(pf.getId());
		
		assertNotNull(pf2);
		
	}
	
	@Test
	public void testa_edita_pessoa(){
		
		PessoaFisica pf = criaPessoa();
		
		pfDAO.insere(pf);
		
		assertNotNull(pf.getId());
		
		pf.setNome("Rafael");
		
		pfDAO.atualiza(pf);
		
		PessoaFisica pf2 = pfDAO.busca(pf.getId());
		
		assertEquals("Rafael", pf2.getNome());
	}
	
	@Test
	public void testa_exclui_Pessoa(){
		
		PessoaFisica pf = criaPessoa();
		
		pfDAO.insere(pf);
		
		assertNotNull(pf.getId());
		
		pfDAO.exclui(pf.getId());
		
		assertNull(pfDAO.busca(pf.getId()));
		
		
	}
	
	
	public PessoaFisica criaPessoa(){
		
		PessoaFisica pf = new PessoaFisica();
		
		pf.setNome("Felipe");
		
		return pf;
	}

}
