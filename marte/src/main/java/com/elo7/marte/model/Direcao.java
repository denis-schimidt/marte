package com.elo7.marte.model;

public enum Direcao {
	N(MudancaEixo.X_POSITIVO), E(MudancaEixo.Y_POSITIVO), S(MudancaEixo.Y_NEGATIVO), W(MudancaEixo.X_NEGATIVO); 
	
	private Direcao direita;
	private Direcao esquerda;
	private MudancaEixo mudancaEixo;
	
	private Direcao(MudancaEixo mudancaEixo) {
		this.mudancaEixo = mudancaEixo;
	}

	static{
		N.setDireita(E);
		N.setEsquerda(W);
		
		E.setDireita(S);
		E.setEsquerda(N);
		
		S.setDireita(W);
		S.setEsquerda(E);
		
		W.setDireita(N);
		W.setEsquerda(S);
	}
	
	private void setDireita(Direcao direita) {
		this.direita = direita;
	}
	
	private void setEsquerda(Direcao esquerda) {
		this.esquerda = esquerda;
	}
	
	public Direcao getDireita() {
		return direita;
	}
	
	public Direcao getEsquerda() {
		return esquerda;
	}
	
	public MudancaEixo getMudancaEixo() {
		return mudancaEixo;
	}
}
