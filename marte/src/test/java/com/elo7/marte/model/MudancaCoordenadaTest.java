package com.elo7.marte.model;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MudancaCoordenadaTest {

	@Test
	public void deveMoverEixoXPositivamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.E);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.X));
		assertThat(mudancaCoordenada.getValor(), equalTo(1));
	}
	
	@Test
	public void deveMoverEixoXNegativamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.W);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.X));
		assertThat(mudancaCoordenada.getValor(), equalTo(-1));
	}
	
	@Test
	public void deveMoverEixoYPositivamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.N);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.Y));
		assertThat(mudancaCoordenada.getValor(), equalTo(1));
	}
	
	@Test
	public void deveMoverEixoYNegativamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.S);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.Y));
		assertThat(mudancaCoordenada.getValor(), equalTo(-1));
	}
}
