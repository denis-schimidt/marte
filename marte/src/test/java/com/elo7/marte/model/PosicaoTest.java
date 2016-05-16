package com.elo7.marte.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;

public class PosicaoTest {

	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaTodosParametrosNulos(){
		Posicao.builder().build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaCoordenadaNula(){
		Posicao.builder().comId(1L).daSonda(new Sonda()).naDirecao(Direcao.E).build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaDirecaoNula(){
		Posicao.builder().comId(1L).daSonda(new Sonda()).naCoordenada(new Coordenada()).build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaSondaNula(){
		Posicao posicao = Posicao.builder().comId(1L).daSonda(new Sonda()).naDirecao(Direcao.E).naCoordenada(new Coordenada()).build();
		assertNotNull(posicao);
		posicao.setSonda(null);
	}
	
	@Test
	public void deveConsiderarDuasSondasIguais(){
		Posicao posicao1 = Posicao.builder().naDirecao(Direcao.E).naCoordenada(new Coordenada(5,5)).build();
		Posicao posicao2 = Posicao.builder().naDirecao(Direcao.E).naCoordenada(new Coordenada(5,5)).build();
		
		assertEquals(posicao1, posicao2);
		assertEquals(posicao1.hashCode(), posicao2.hashCode());
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaDirecao(){
		Posicao posicao1 = Posicao.builder().naDirecao(Direcao.E).naCoordenada(new Coordenada(5,5)).build();
		Posicao posicao2 = Posicao.builder().naDirecao(Direcao.W).naCoordenada(new Coordenada(5,5)).build();
		
		assertThat(posicao1, not(equalTo(posicao2)));
		assertThat(posicao1.hashCode(), not(equalTo(posicao2.hashCode())));
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaEixoXDaCoordenada(){
		Posicao posicao1 = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(0,5)).build();
		Posicao posicao2 = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(1,5)).build();
		
		assertThat(posicao1, not(equalTo(posicao2)));
		assertThat(posicao1.hashCode(), not(equalTo(posicao2.hashCode())));
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaEixoYDaCoordenada(){
		Posicao posicao1 = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,1)).build();
		Posicao posicao2 = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,2)).build();
		
		assertThat(posicao1, not(equalTo(posicao2)));
		assertThat(posicao1.hashCode(), not(equalTo(posicao2.hashCode())));
	}
	
	@Test
	public void deveMoverParaFrente() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,10)).build();
		
		posicao.atualizarPosicao(Comando.M, new Planalto(11, 11));
		
		assertThat(posicao.getCoordenada(), equalTo(new Coordenada(10, 11)));
		assertThat(posicao.getDirecao(), equalTo(Direcao.N));
	}
	
	@Test
	public void deveVirarEsquerda() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,10)).build();
		
		posicao.atualizarPosicao(Comando.L, new Planalto(11, 11));
		
		assertThat(posicao.getCoordenada(), equalTo(new Coordenada(10,10)));
		assertThat(posicao.getDirecao(), equalTo(Direcao.W));
	}
	
	@Test
	public void deveVirarDireita() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,10)).build();
		
		posicao.atualizarPosicao(Comando.R, new Planalto(11, 11));
		
		assertThat(posicao.getCoordenada(), equalTo(new Coordenada(10,10)));
		assertThat(posicao.getDirecao(), equalTo(Direcao.E));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaPlanaltoNulo() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().comId(1L).daSonda(new Sonda()).naDirecao(Direcao.E).naCoordenada(new Coordenada()).build();
		
		posicao.atualizarPosicao(Comando.M, null);
	}
	
	@Test(expected=CoordenadaInvalidaException.class)
	public void deveMoverLancarExcecaoPorUltrapassarLimitesPlanalto() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,10)).build();
		
		posicao.atualizarPosicao(Comando.M, new Planalto(10, 10));
	}
	
	@Test(expected=ComandoInvalidoException.class)
	public void deveMoverLancarExcecaoPorComandoInvalido() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Posicao posicao = Posicao.builder().naDirecao(Direcao.N).naCoordenada(new Coordenada(10,10)).build();
		
		posicao.atualizarPosicao(null, new Planalto(10, 10));
	}
}
