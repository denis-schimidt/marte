package com.elo7.marte.domain.model.sonda;

interface Eixo {

	Eixo moverParaFrente(Direcao direcao);
	
	int getValor();
}
