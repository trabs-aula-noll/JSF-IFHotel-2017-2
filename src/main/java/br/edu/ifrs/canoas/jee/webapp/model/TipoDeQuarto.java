package br.edu.ifrs.canoas.jee.webapp.model;


public enum TipoDeQuarto {
	STANDARD("Standard"), DUPLO("Duplo"), TRIPLO("Triplo"), MASTER("Master"), PRESIDENCIAL("Presidencial"), MEGA_ULTRA("Mega ultra");
	private String descricao;
	
	
	private TipoDeQuarto(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
