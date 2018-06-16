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

import br.edu.ifrs.canoas.jee.webapp.model.dao.UsuarioDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Usuario;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@RunWith(Arquillian.class)
public class GerenciarUsuarioServiceTest {

	@Inject
	GerenciarUsuarioService gerenciarUsuarioService;
	
	@Inject
    Logger log;

	@Deployment
    public static Archive<?> createTestArchive() {
	    return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GerenciarUsuarioService.class, UsuarioDAO.class, org.apache.commons.lang3.StringUtils.class, Mensagens.class)
                .addPackages(true, "br.edu.ifrs.canoas.jee.webapp")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/faces-config.xml"))
                .addAsResource(new File("src/main/resources/ValidationMessages.properties"), "ValidationMessages.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;
    }
	
	@Test
	public void salva_usuario() {
		Usuario usuario = criaUsuario();
		assertTrue(gerenciarUsuarioService.salvaUsario(usuario));
		assertNotNull(usuario.getId());
		log.info(usuario.getNome() + " foi persistido com o id " + usuario.getId());
		
		usuario = criaUsuario();
		assertFalse(gerenciarUsuarioService.salvaUsario(usuario));
		log.info("não permite criar usuario com mesmo email");
	}

	private Usuario criaUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("email@email.com");
		usuario.setNome("Rodrigo");
		usuario.setSenha("senha");
		usuario.setSobrenome("Noll");
		return usuario;
	}

}
