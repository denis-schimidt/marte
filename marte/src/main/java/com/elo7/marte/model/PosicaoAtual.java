package com.elo7.marte.model;

public class PosicaoAtual {
	private final Planalto planalto;
	private Coordenada coordenada;
	private Direcao direcao;

	public PosicaoAtual(Coordenada coordenada, Direcao direcao, Planalto planalto) {
		this.coordenada = coordenada;
		this.direcao = direcao;
		this.planalto = planalto;
	}

	public void atualizarPosicao(Comando comando) {

		if (comando == Comando.M) {
			coordenada = moverParaFrente();

		} else if (comando == Comando.L) {
			direcao = direcao.getEsquerda();

		} else {
			direcao = direcao.getDireita();
		}
	}

	private Coordenada moverParaFrente() {
		Movimento movimento = Movimento.irParaFrente(direcao);
		Coordenada novaCoordenada = coordenada.mudarCoordenada(movimento);

		if (!planalto.isCoordenadaDentroPlanalto(novaCoordenada)) {
			String mensagemErro = String.format("A %s ultrapassou os limites do %s", novaCoordenada, planalto);
			throw new IllegalArgumentException(mensagemErro);
		}

		return novaCoordenada;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public Direcao getDirecao() {
		return direcao;
	}
}
