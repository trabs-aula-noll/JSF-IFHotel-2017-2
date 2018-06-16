package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.logging.Logger;

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
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarQuartoServiceTest {

	@Inject
	GerenciarQuartoService gerenciarQuartoService;
	
	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarQuartoService.class, QuartoDAO.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
    }

	@Test
	public void salvaQuarto() {
		
		Quarto quarto = criaQuarto();
		assertTrue(gerenciarQuartoService.salva(quarto));
		assertNotNull(quarto.getId());		
	}
	
	@Test(expected = Exception.class)
	public void salvaQuartoNull(){
		
		assertFalse(gerenciarQuartoService.salva(null));
		
	}
	
	@Test
	public void buscaPorNumero(){
		
		Quarto q2 = criaQuarto();
		q2.setNumero("556");
		gerenciarQuartoService.salva(q2);
		assertFalse(gerenciarQuartoService.buscaNumero("556").isEmpty());
	}
	
	@Test
	public void salvaQuartoNumeroIncorreto(){
		Quarto q2 = criaQuarto();
		
		q2.setNumero("abc");
		try{
			gerenciarQuartoService.salva(q2);
		}catch(Exception e){
			assertTrue(gerenciarQuartoService.buscaNumero("abc").isEmpty());
		}
		
	}
	
	@Test(expected = Exception.class)
	public void salvaQuartoNumeroVazio(){
		
		Quarto q2 = criaQuarto();
		q2.setNumero(null);
		gerenciarQuartoService.salva(q2);
		
	}

	@Test
	public void salvaQuartoTipoNull(){
		Quarto q2 = criaQuarto();
		q2.setTipo(null);
		q2.setNumero("666");
		try{
			gerenciarQuartoService.salva(q2);
		}catch(Exception e){
			assertTrue(gerenciarQuartoService.buscaNumero("666").isEmpty());
		}
	}
	
	@Test
	public void salvaQuartoDescricaoNull(){
		Quarto q2 = criaQuarto();
		q2.setNumero("666");
		q2.setDescricao(null);
		try{
			gerenciarQuartoService.salva(q2);
		}catch(Exception e){
			assertTrue(gerenciarQuartoService.buscaNumero("666").isEmpty());
		}
	}
	
	@Test
	public void salvaQuartoSituacaoNull(){
		
		Quarto q2 = criaQuarto();
		q2.setNumero("666");
		q2.setSituacao(null);
		
		try{
			gerenciarQuartoService.salva(q2);
		}catch(Exception e){
			assertTrue(gerenciarQuartoService.buscaNumero("666").isEmpty());
		}
		
	}
	
	@Test
	public void atualizaQuarto(){
		Quarto q2 = criaQuarto();
		q2.setNumero("111");
		gerenciarQuartoService.salva(q2);
		assertEquals(gerenciarQuartoService.get(q2.getId()).getDescricao(), "Quarto muito bom");
		q2.setDescricao("Quarto onde paredes tem ouvidos");
		gerenciarQuartoService.salva(q2);
		assertTrue(gerenciarQuartoService.buscaNumero("111").size() == 1);
		assertEquals(gerenciarQuartoService.get(q2.getId()).getDescricao(), "Quarto onde paredes tem ouvidos");
	}
	
	@Test
	public void excluiQuarto(){
		Quarto q1 = criaQuarto();
		q1.setNumero("222");
		gerenciarQuartoService.salva(q1);
		assertFalse(gerenciarQuartoService.buscaNumero("222").isEmpty());
		gerenciarQuartoService.exclui(q1);
		assertTrue(gerenciarQuartoService.buscaNumero("222").isEmpty());
	}
		
	@Test(expected = Exception.class)
	public void salvaQuartoNumeroComEspaco(){
		Quarto q2 = criaQuarto();
		q2.setNumero("1 45");
		gerenciarQuartoService.salva(q2);
		assertFalse(gerenciarQuartoService.buscaNumero("1 45").isEmpty());
	}
	
	@Test
	public void getQuarto(){
		
		Quarto q2 = criaQuarto();
		q2.setNumero("777");
		gerenciarQuartoService.salva(q2);
		
		Quarto q3 = gerenciarQuartoService.get(q2.getId());
		assertEquals(q3.getNumero(), q2.getNumero());
		assertEquals(q3.getId(), q2.getId());
	}
	
	
	public Quarto criaQuarto(){
		
		Quarto q1 = new Quarto();
		q1.setNumero("401");
		q1.setSituacao("Disponivel");
		q1.setTipo(TipoDeQuarto.PRESIDENCIAL);
		q1.setDescricao("Quarto muito bom");
		
		return q1;
	}

}
