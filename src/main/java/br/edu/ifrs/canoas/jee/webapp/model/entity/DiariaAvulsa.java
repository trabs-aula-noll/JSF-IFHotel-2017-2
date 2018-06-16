package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class DiariaAvulsa extends Diaria implements Comparable<DiariaAvulsa> {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name="pessoa_id")
	private Pessoa cliente;

	public DiariaAvulsa() {
		super();
	}
	
	public DiariaAvulsa(Date data){
		super(data);
	}
	
	public Pessoa getCliente(){
		return cliente;
	}
	
	public void setCliente(Pessoa cliente){
		this.cliente = cliente;
	}

	@Override
	public int compareTo(DiariaAvulsa d) {
		return Integer.parseInt(super.getQuarto().getNumero()) < 
				Integer.parseInt(d.getQuarto().getNumero()) ? -1 : 
					Integer.parseInt(super.getQuarto().getNumero()) > 
					Integer.parseInt(d.getQuarto().getNumero()) ? 1 : 0;
	}
}
