package com.elo7.marte.domain.model.sonda;

@FunctionalInterface
interface Acao {

	PosicaoDirecional executar(PosicaoDirecional posicaoDirecional);
}
