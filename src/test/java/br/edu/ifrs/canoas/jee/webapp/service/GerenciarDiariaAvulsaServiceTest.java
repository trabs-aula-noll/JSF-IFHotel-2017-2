package br.edu.ifrs.canoas.jee.webapp.service;

import static org.junit.Assert.*;

import java.util.List;
import java.io.File;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.canoas.jee.webapp.model.Sexo;
import br.edu.ifrs.canoas.jee.webapp.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarDiariaAvulsaServiceTest {
	@Inject
	private GerenciarDiariaAvulsaService gerenciarDiariaAvulsaService;
	@Inject
	private QuartoDAO qDAO;
	@Inject
	private PessoaFisicaDAO pfDAO;
	@Inject
	private PessoaJuridicaDAO pjDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarDiariaAvulsaService.class, QuartoDAO.class, PessoaFisicaDAO.class, PessoaJuridicaDAO.class, StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@SuppressWarnings("deprecation")
	@Test
	public void testSalvaDiaria() {
		List<DiariaAvulsa> diarias = gerenciarDiariaAvulsaService.busca();
		
		DiariaAvulsa d = criaDiaria();
		d.setData(new Date(117, 11, 25));
		
		assertFalse(diarias.contains(d));
		
		gerenciarDiariaAvulsaService.salvaDiaria(d);
		
		diarias = gerenciarDiariaAvulsaService.busca();
		
		assertNotNull(d.getId());
		
		assertTrue(diarias.contains(d));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAtualizaDiaria() {
		List<DiariaAvulsa> diarias = gerenciarDiariaAvulsaService.busca();
		
		DiariaAvulsa d = criaDiaria();
		d.setData(new Date(120, 1, 1));
		
		assertFalse(diarias.contains(d));
		
		gerenciarDiariaAvulsaService.salvaDiaria(d);
		
		diarias = gerenciarDiariaAvulsaService.busca();
		
		assertNotNull(d.getId());
		assertTrue(diarias.contains(d));
		
		d.setQntdDias(12);
		gerenciarDiariaAvulsaService.salvaDiaria(d);
		
		DiariaAvulsa busca = gerenciarDiariaAvulsaService.get(d.getId());
		assertEquals(busca.getQntdDias(), 12);
	}

	@Test
	public void testBusca() {
		gerenciarDiariaAvulsaService.salvaDiaria(criaDiaria());
		gerenciarDiariaAvulsaService.salvaDiaria(criaDiaria());
		gerenciarDiariaAvulsaService.salvaDiaria(criaDiaria());
		
		List<DiariaAvulsa> diarias = gerenciarDiariaAvulsaService.busca();
		
		assertTrue(diarias.size() >= 3);
	}

	@Test
	public void testExclui() {
		DiariaAvulsa d = criaDiaria();
		d.setData(new Date(118, 0, 1));
		
		gerenciarDiariaAvulsaService.salvaDiaria(d);
		
		List<DiariaAvulsa> diarias = gerenciarDiariaAvulsaService.busca();
		
		assertTrue(diarias.contains(d));
		
		gerenciarDiariaAvulsaService.exclui(d);
		
		diarias = gerenciarDiariaAvulsaService.busca();
		
		assertFalse(diarias.contains(d));
	}

	@Test
	public void testGet() {
		DiariaAvulsa d = criaDiaria();
		d.setData(new Date(117, 11, 24));
		
		gerenciarDiariaAvulsaService.salvaDiaria(d);
		
		List<DiariaAvulsa> diarias = gerenciarDiariaAvulsaService.busca();		
		assertTrue(diarias.contains(d));
		
		DiariaAvulsa busca = gerenciarDiariaAvulsaService.get(d.getId());		
		assertEquals(d, busca);
	}
	
	public DiariaAvulsa criaDiaria() {
		Quarto q = criaQuarto();
		PessoaFisica pf = criaPf();
		PessoaJuridica pj = criaPj();
		
		qDAO.insere(q);
		pfDAO.insere(pf);
		pjDAO.insere(pj);
		
		DiariaAvulsa d = new DiariaAvulsa();
		d.setQuarto(q);
		d.setCliente(pj);
		d.getHospedes().add(pf);
		d.setQntdDias(5);
		d.setData(new Date());
		
		return d;
	}
	
	private PessoaJuridica criaPj() {
		PessoaJuridica pj = new PessoaJuridica();
		pj.setNome("PF");
		pj.setTelefone("51999999999");
		pj.setEmail("pf@pf.com");
		pj.setCnpj("11111111111111");
		pj.setRazaoSocial("PJ SA");
		pj.setInscricaoEstadual("12345");
		pj.setInscricaoMunicipal("123");
		
		return pj;
	}

	public PessoaFisica criaPf() {
		PessoaFisica pf = new PessoaFisica();
		pf.setNome("PF");
		pf.setTelefone("51999999999");
		pf.setCpf("11111111111");
		pf.setRg("1111111111");
		pf.setDataNascimento(new Date());
		pf.setEmail("pf@pf.com");
		pf.setSexo(Sexo.M);
		
		return pf;
	}
	
	public Quarto criaQuarto() {
		Quarto q = new Quarto();
		q.setNumero("100");
		q.setDescricao("Quarto");
		q.setSituacao("Disponível");
		q.setTipo(TipoDeQuarto.STANDARD);
		
		return q;
	}
}
