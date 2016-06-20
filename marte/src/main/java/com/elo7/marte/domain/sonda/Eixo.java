package com.elo7.marte.domain.sonda;

interface Eixo {

	Eixo moverParaFrente(Direcao direcao);
	
	int getValor();
}
