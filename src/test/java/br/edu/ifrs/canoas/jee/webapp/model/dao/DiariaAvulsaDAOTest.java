package br.edu.ifrs.canoas.jee.webapp.model.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;
import java.util.List;

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
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;


@RunWith(Arquillian.class)
public class DiariaAvulsaDAOTest {
	@Inject
	private DiariaAvulsaDAO daDAO;
	@Inject
	private QuartoDAO qDAO;
	@Inject
	private PessoaFisicaDAO pfDAO;
	@Inject
	private PessoaJuridicaDAO pjDAO;
	
	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(DiariaAvulsaDAO.class, QuartoDAO.class, PessoaFisicaDAO.class, PessoaJuridicaDAO.class, StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	

	@Test
	public void listaDiariaAvulsaTest() {
		daDAO.insere(criaDiaria());
		daDAO.insere(criaDiaria());
		daDAO.insere(criaDiaria());
		
		List<DiariaAvulsa> diarias = daDAO.lista();
		
		assertTrue(diarias.size() >= 3);
	}
	
	@Test
	public void buscaFiltradaTest() {
		DiariaAvulsa d1 = criaDiaria();
		DiariaAvulsa d2 = criaDiaria();
		DiariaAvulsa d3 = criaDiaria();
		d1.setData(new Date(117, 11, 24));
		d2.setData(new Date(118, 0, 1));
		d3.setData(new Date(117, 11, 1));
		
		daDAO.insere(d1);
		daDAO.insere(d2);
		daDAO.insere(d3);
		
		List<DiariaAvulsa> diarias = daDAO.lista();		
		assertTrue(diarias.size() >= 2);
		
		diarias = daDAO.buscaFiltrada();
		assertTrue(diarias.size() == 2);
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
		d.setData(new Date(117, 10, 10));
		
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
