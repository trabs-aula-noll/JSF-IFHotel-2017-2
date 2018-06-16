package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 6262524988798723388L;

	@NotNull @Email 
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String nome;
	
	private String sobrenome;
	
	private String telefone;
	
	public Usuario() {
		super();
	}
		
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
