package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa extends BaseEntity<Long> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String telefone;
	private String email;
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	@OneToMany (mappedBy="cliente")
    private Collection<DiariaAvulsa> diariasAvulsas;
	
	public Pessoa(){
		
	}
	
	public Pessoa(String nome, String telefone, String email){
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null) {
			throw new IllegalArgumentException("Nome vazio.");
		}
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Collection<DiariaAvulsa> getDiariasAvulsas() {
		return diariasAvulsas;
	}

	public void setDiariasAvulsas(Collection<DiariaAvulsa> diariasAvulsas) {
		this.diariasAvulsas = diariasAvulsas;
	}

}
