package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class DiariaReservada extends Diaria {
	private static final long serialVersionUID = 1L;
	
	public DiariaReservada() {
		
		super();
	}
	
	public DiariaReservada(Date data){
		super(data);
	}

	
}
