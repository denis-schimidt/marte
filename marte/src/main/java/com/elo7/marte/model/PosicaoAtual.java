package com.elo7.marte.model;

public class PosicaoAtual {
	private Coordenada coordenada;
	private Direcao direcao;
	private Planalto planalto;
	
	public PosicaoAtual(Coordenada coordenada, Direcao direcao, Planalto planalto) {
		this.coordenada = coordenada;
		this.direcao = direcao;
		this.planalto = planalto;
	}
	
	public void atualizarPosicao(Comando comando){
		
		if(comando==Comando.M){
			Coordenada possivelNovaCoordenada = coordenada.clonar();
			possivelNovaCoordenada.mudarCoordenada(direcao.getMudancaEixo());
			
			if(planalto.isCoordenadaDentroPlanalto(possivelNovaCoordenada)){
				coordenada = possivelNovaCoordenada;				
			}else{
				// Lanca excecao
			}
		
		}else if(comando==Comando.L){
			direcao = direcao.getEsquerda();
		
		}else{
			direcao = direcao.getDireita();
		}
	}

	public Coordenada getCoordenada() {
		return coordenada.clonar();
	}

	public Direcao getDirecao() {
		return direcao;
	}
}
