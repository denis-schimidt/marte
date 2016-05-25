package com.elo7.marte.model;

import java.util.Calendar;

public interface PosicaoIdentificavel {

	Coordenada getCoordenada();

	Direcao getDirecao();

	Calendar getDataHora();

}