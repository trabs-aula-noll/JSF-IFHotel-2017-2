package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.util.Collection;
import java.util.Date;
import java.lang.NullPointerException;
import javax.persistence.*;

import br.edu.ifrs.canoas.jee.webapp.model.Sexo;

@Entity
public class PessoaFisica extends Pessoa{
	
	private static final long serialVersionUID = 4037841475829873186L;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@ManyToMany (mappedBy="hospedes")
	private Collection<Diaria> diarias;
	
	@OneToMany(mappedBy="cliente")
	private Collection<Reserva> reservas;
	
	public Collection<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reserva> reservas) {
		this.reservas = reservas;
	}

	public PessoaFisica(){
		super();
	}
	
	public PessoaFisica(String nome, String telefone, String email, String cpf, String rg, Date dataNascimento, Sexo sexo) {
		super(nome, telefone, email);
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		if(nome==null) {
			throw new IllegalArgumentException("Nome vazio.");
		}
		if(cpf==null) {
			throw new IllegalArgumentException("CPF vazio.");
		}
		if(rg==null) {
			throw new IllegalArgumentException("RG vazio.");
		}
		if(cpf.length()!=11) {
			throw new IllegalArgumentException("CPF deve possuir 11 caracteres.");
		}
		if(rg.length()!=10) {
			throw new IllegalArgumentException("RG deve possuir 10 caracteres.");
		}
		for (int i = 0; i < cpf.length(); i++) {
			if (!Character.isDigit(cpf.charAt(i))) {
				throw new IllegalArgumentException("CPF deve possuir apenas caracteres numericos.");
			}
		}
		for (int i = 0; i < rg.length(); i++) {
			if (!Character.isDigit(rg.charAt(i))) {
				throw new IllegalArgumentException("RG deve possuir apenas caracteres numericos.");
			}
		}
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		if(rg==null) {
			throw new IllegalArgumentException("RG vazio.");
		}
		if(rg.length()!=10) {
			throw new IllegalArgumentException("RG deve possuir 10 caracteres.");
		}
		for (int i = 0; i < rg.length(); i++) {
			if (!Character.isDigit(rg.charAt(i))) {
				throw new IllegalArgumentException("RG deve possuir apenas caracteres numericos.");
			}
		}
		this.rg = rg;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Collection<Diaria> getDiarias() {
		return diarias;
	}
	
	public void setDiarias(Collection<Diaria> diarias) {
		this.diarias = diarias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
