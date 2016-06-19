package com.elo7.marte.domain.sonda;

public enum Direcao {
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W"); 
	
	private Direcao direita;
	private Direcao esquerda;
	private String codigo;

	static{
		NORTH.setDireita(EAST);
		NORTH.setEsquerda(WEST);
		
		EAST.setDireita(SOUTH);
		EAST.setEsquerda(NORTH);
		
		SOUTH.setDireita(WEST);
		SOUTH.setEsquerda(EAST);
		
		WEST.setDireita(NORTH);
		WEST.setEsquerda(SOUTH);
	}
	
	private Direcao(String direcaoAsCode) {
		this.codigo = direcaoAsCode;
	}

	private void setDireita(Direcao direita) {
		this.direita = direita;
	}
	
	private void setEsquerda(Direcao esquerda) {
		this.esquerda = esquerda;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public Direcao getDireita() {
		return direita;
	}
	
	public Direcao getEsquerda() {
		return esquerda;
	}
	
	public static Direcao getInstance(String direcaoAsCode){
		
		for(Direcao direcao : Direcao.values()){
			if(direcao.codigo.equals(direcaoAsCode)){
				return direcao;
			}
		}
		
		throw new IllegalArgumentException(String.format("Direcao(%s) não mapeado.", direcaoAsCode));
	}
}
