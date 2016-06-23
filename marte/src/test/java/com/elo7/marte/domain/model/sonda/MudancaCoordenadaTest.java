package com.elo7.marte.domain.model.sonda;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.elo7.marte.domain.model.sonda.Direcao;
import com.elo7.marte.domain.model.sonda.Eixo;
import com.elo7.marte.domain.sonda.MudancaCoordenada;

public class MudancaCoordenadaTest {

	@Test
	public void deveMoverEixoXPositivamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.EAST);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.X));
		assertThat(mudancaCoordenada.getValor(), equalTo(1));
	}
	
	@Test
	public void deveMoverEixoXNegativamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.WEST);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.X));
		assertThat(mudancaCoordenada.getValor(), equalTo(-1));
	}
	
	@Test
	public void deveMoverEixoYPositivamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.NORTH);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.Y));
		assertThat(mudancaCoordenada.getValor(), equalTo(1));
	}
	
	@Test
	public void deveMoverEixoYNegativamente(){
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(Direcao.SOUTH);
		
		assertThat(mudancaCoordenada.getEixo(), equalTo(Eixo.Y));
		assertThat(mudancaCoordenada.getValor(), equalTo(-1));
	}
}
