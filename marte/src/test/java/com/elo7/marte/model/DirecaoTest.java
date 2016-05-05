package com.elo7.marte.model;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DirecaoTest {

	@Test
	public void deveVirarDirecaoNorteParaDireita(){
		Direcao direcao = Direcao.N;

		assertThat(direcao.getDireita(), equalTo(Direcao.E));
	}

	@Test
	public void deveVirarDirecaoNorteParaEsquerda(){
		Direcao direcao = Direcao.N;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.W));
	}

	@Test
	public void deveVirarDirecaoSulParaDireita(){
		Direcao direcao = Direcao.S;

		assertThat(direcao.getDireita(), equalTo(Direcao.W));
	}

	@Test
	public void deveVirarDirecaoSulParaEsquerda(){
		Direcao direcao = Direcao.S;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.E));
	}
}
