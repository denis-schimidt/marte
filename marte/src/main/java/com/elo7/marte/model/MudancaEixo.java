package com.elo7.marte.model;

public enum MudancaEixo {
	X_POSITIVO(1,Eixo.X), X_NEGATIVO(-1, Eixo.X), Y_POSITIVO(1, Eixo.Y), Y_NEGATIVO(-1, Eixo.Y);
	
	private int valor;
	private Eixo eixo;

	private MudancaEixo(int valor, Eixo eixo) {
		this.valor = valor;
		this.eixo = eixo;
	}

	public int getValor() {
		return valor;
	}
	
	public Eixo getEixo() {
		return eixo;
	}
}
