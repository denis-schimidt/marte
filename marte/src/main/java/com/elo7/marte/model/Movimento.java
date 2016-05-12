package com.elo7.marte.model;

public enum Movimento {
	X_POSITIVO(1,Eixo.X, Direcao.E), X_NEGATIVO(-1, Eixo.X, Direcao.W), Y_POSITIVO(1, Eixo.Y, Direcao.N), Y_NEGATIVO(-1, Eixo.Y, Direcao.S);
	
	private int valor;
	private Eixo eixo;
	private Direcao direcao;

	private Movimento(int valor, Eixo eixo, Direcao direcao) {
		this.valor = valor;
		this.eixo = eixo;
		this.direcao = direcao;
	}

	public static Movimento irParaFrente(Direcao direcaoAtual){
		
		for (Movimento movimento : values()) {
			if(movimento.direcao == direcaoAtual){
				return movimento;
			}
		}  
		
		throw new IllegalArgumentException( String.format("Direção %s não possui movimento associado.", direcaoAtual));
	}

	public int getValor() {
		return valor;
	}
	
	public Eixo getEixo() {
		return eixo;
	}
}
