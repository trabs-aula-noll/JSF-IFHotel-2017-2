package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Diaria extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date data;
	@ManyToOne
	@JoinColumn(name="quarto_id")
	private Quarto quarto;
	@ManyToMany(cascade=CascadeType.MERGE)
	private Collection<PessoaFisica> hospedes;
	@Min(value=1)
	private int qntdDias;

	public Diaria() {
		quarto = new Quarto();
		data = new Date();
		hospedes = new ArrayList<>();
	}

	public Diaria(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Collection<PessoaFisica> getHospedes() {
		return hospedes;
	}

	public void setHospedes(Collection<PessoaFisica> hospedes) {
		this.hospedes = hospedes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
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
		Diaria other = (Diaria) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (quarto == null) {
			if (other.quarto != null)
				return false;
		} else if (!quarto.equals(other.quarto))
			return false;
		return true;
	}

	public int getQntdDias() {
		return qntdDias;
	}

	public void setQntdDias(int qntdDias) {
		this.qntdDias = qntdDias;
	}
}
