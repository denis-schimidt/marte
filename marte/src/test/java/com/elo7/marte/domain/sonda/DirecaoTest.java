package com.elo7.marte.domain.sonda;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.elo7.marte.domain.sonda.Direcao;

public class DirecaoTest {

	@Test
	public void deveVirarDirecaoNorteParaDireita(){
		Direcao direcao = Direcao.NORTH;

		assertThat(direcao.getDireita(), equalTo(Direcao.EAST));
	}

	@Test
	public void deveVirarDirecaoNorteParaEsquerda(){
		Direcao direcao = Direcao.NORTH;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.WEST));
	}

	@Test
	public void deveVirarDirecaoSulParaDireita(){
		Direcao direcao = Direcao.SOUTH;

		assertThat(direcao.getDireita(), equalTo(Direcao.WEST));
	}

	@Test
	public void deveVirarDirecaoSulParaEsquerda(){
		Direcao direcao = Direcao.SOUTH;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.EAST));
	}
	
	@Test
	public void deveVirarDirecaoLesteParaDireita(){
		Direcao direcao = Direcao.EAST;

		assertThat(direcao.getDireita(), equalTo(Direcao.SOUTH));
	}

	@Test
	public void deveVirarDirecaoLesteParaEsquerda(){
		Direcao direcao = Direcao.EAST;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.NORTH));
	}
	
	@Test
	public void deveVirarDirecaoOesteParaDireita(){
		Direcao direcao = Direcao.WEST;

		assertThat(direcao.getDireita(), equalTo(Direcao.NORTH));
	}

	@Test
	public void deveVirarDirecaoOesteParaEsquerda(){
		Direcao direcao = Direcao.WEST;

		assertThat(direcao.getEsquerda(), equalTo(Direcao.SOUTH));
	}
}
