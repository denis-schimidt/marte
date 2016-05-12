package com.elo7.marte.model;

import java.util.List;

public class Sonda {
	private PosicaoAtual posicaoAtual;

	public Sonda(PosicaoAtual posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public void iniciarExploracao(List<Comando> comandos) {

		for (Comando comando : comandos) {
			posicaoAtual.atualizarPosicao(comando);
		}
	}
}
