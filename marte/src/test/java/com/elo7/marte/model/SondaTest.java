package com.elo7.marte.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;

public class SondaTest {

	@Test(timeout=20)
	public void deveExecutarPrimeiraSequenciaComandosSonda() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Planalto planalto = new Planalto(5, 5);
		Coordenada inicio = new Coordenada(1, 2);
		Direcao direcao = Direcao.N;
		List<Comando> comandos = Arrays.asList(Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.M);
		
		Sonda sonda = new Sonda(Posicao.builder().naCoordenada(inicio).naDirecao(direcao).build(), planalto);
		sonda.iniciarExploracao(comandos);
		
		assertThat(sonda.getPosicaoAtual().getCoordenada(), equalTo(new Coordenada(1, 3)));
		assertThat(sonda.getPosicaoAtual().getDirecao(), equalTo(Direcao.N));
	}
	
	@Test(timeout=20)
	public void deveExecutarSegundaSequenciaComandosSonda() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Planalto planalto = new Planalto(5, 5);
		Coordenada inicio = new Coordenada(3, 3);
		Direcao direcao = Direcao.E;
		List<Comando> comandos = Arrays.asList(Comando.M, Comando.M, Comando.R, Comando.M, Comando.M, Comando.R, Comando.M, Comando.R, 
				Comando.R, Comando.M);		
		
		Sonda sonda = new Sonda(Posicao.builder().naCoordenada(inicio).naDirecao(direcao).build(), planalto);
		sonda.iniciarExploracao(comandos);
		
		assertThat(sonda.getPosicaoAtual().getCoordenada(), equalTo(new Coordenada(5, 1)));
		assertThat(sonda.getPosicaoAtual().getDirecao(), equalTo(Direcao.E));
	}
	
	@Test
	public void deveConsiderarDuasSondasIguais(){
		Sonda sonda1 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(3,3)).naDirecao(Direcao.E).build(), new Planalto(5, 5));
		Sonda sonda2 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(3,3)).naDirecao(Direcao.E).build(), new Planalto(5, 5));
		
		assertThat(sonda1, equalTo(sonda2));
		assertThat(sonda1.hashCode(), equalTo(sonda2.hashCode()));
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaPosicao(){
		Sonda sonda1 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(1,3)).naDirecao(Direcao.E).build(), new Planalto(5, 5));
		Sonda sonda2 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(3,1)).naDirecao(Direcao.E).build(), new Planalto(5, 5));
		
		assertThat(sonda1, not(equalTo(sonda2)));
		assertThat(sonda1.hashCode(), not(equalTo(sonda2.hashCode())));
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaDirecaoInicial(){
		Sonda sonda1 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(1,3)).naDirecao(Direcao.E).build(), new Planalto(5, 5));
		Sonda sonda2 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(3,1)).naDirecao(Direcao.W).build(), new Planalto(5, 5));
		
		assertThat(sonda1, not(equalTo(sonda2)));
		assertThat(sonda1.hashCode(), not(equalTo(sonda2.hashCode())));
	}
	
	@Test
	public void deveConsiderarDuasSondasDiferentesPelaPlanalto(){
		Sonda sonda1 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(1,3)).naDirecao(Direcao.E).build(), new Planalto(5, 1));
		Sonda sonda2 = new Sonda(Posicao.builder().naCoordenada(new Coordenada(3,1)).naDirecao(Direcao.W).build(), new Planalto(1, 5));
		
		assertThat(sonda1, not(equalTo(sonda2)));
		assertThat(sonda1.hashCode(), not(equalTo(sonda2.hashCode())));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoPlanaltoNulo(){
		new Sonda(Posicao.builder().naCoordenada(new Coordenada(1,3)).naDirecao(Direcao.E).build(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoPosicaoNulo(){
		new Sonda(null, new Planalto(1, 5));
	}
	
	@Test
	public void deveExecucaoComandosParandoComLancamentoDeComandoInvalidoException() throws CoordenadaInvalidaException{
		Sonda sonda = null;
		
		try{
			List<Comando> comandos = Arrays.asList(Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.M, null);
			sonda = new Sonda(Posicao.builder().naCoordenada(new Coordenada(1, 2)).naDirecao(Direcao.N).build(), new Planalto(5, 5));
			sonda.iniciarExploracao(comandos);
			fail();
			
		}catch(ComandoInvalidoException e){
			assertThat(sonda.getPosicaoAtual().getCoordenada(), equalTo(new Coordenada(1, 3)));
			assertThat(sonda.getPosicaoAtual().getDirecao(), equalTo(Direcao.N));
		}
	}
	
	@Test
	public void deveExecucaoComandosParandoComLancamentoDeCoordenadaInvalidaException() throws ComandoInvalidoException{
		Sonda sonda = null;
		
		try{
			List<Comando> comandos = Arrays.asList(Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.M);
			sonda = new Sonda(Posicao.builder().naCoordenada(new Coordenada(1, 2)).naDirecao(Direcao.N).build(), new Planalto(1, 2));
			sonda.iniciarExploracao(comandos);
			fail();
			
		}catch(CoordenadaInvalidaException e){
			assertThat(sonda.getPosicaoAtual().getCoordenada(), equalTo(new Coordenada(1, 2)));
			assertThat(sonda.getPosicaoAtual().getDirecao(), equalTo(Direcao.N));
		}
	}
}
