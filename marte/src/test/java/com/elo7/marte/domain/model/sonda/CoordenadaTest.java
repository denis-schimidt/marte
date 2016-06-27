/*package com.elo7.marte.domain.model.sonda;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.elo7.marte.domain.model.sonda.Coordenada;
import com.elo7.marte.domain.sonda.MudancaCoordenada;

public class CoordenadaTest {

	@Test
	public void deveConsiderarDuasCoordenadasIguais(){
		Coordenada coordenada1 = new Coordenada(5, 5);
		Coordenada coordenada2 = new Coordenada(5, 5);
		
		assertEquals(coordenada1, coordenada2);
		assertEquals(coordenada1, coordenada1);
	}
	
	@Test
	public void deveConsiderarDoisHashcodesIguais(){
		Coordenada coordenada1 = new Coordenada(5, 5);
		Coordenada coordenada2 = new Coordenada(5, 5);
		
		assertEquals(coordenada1.hashCode(), coordenada2.hashCode());
	}
	
	@Test
	public void deveConsiderarDoisHashcodesDiferentes(){
		Coordenada coordenada1 = new Coordenada(2, 5);
		Coordenada coordenada2 = new Coordenada(5, 2);
		
		assertNotEquals(coordenada1.hashCode(), coordenada2.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaEixoXNegativo(){
		new Coordenada(-1, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaEixoYNegativo(){
		new Coordenada(0, -1);
	}
	
	@Test
	public void deveAdicionarUmAoEixoXMantendoEixoY(){
		Coordenada novaCoordenada = new Coordenada(1, 2).mudarCoordenada(MudancaCoordenada.X_POSITIVO);
		
		assertThat(novaCoordenada.getEixoX(), equalTo(2));
		assertThat(novaCoordenada.getEixoY(), equalTo(2));
	}
	
	@Test
	public void deveAdicionarUmAoEixoYMantendoEixoX(){
		Coordenada novaCoordenada = new Coordenada(1, 2).mudarCoordenada(MudancaCoordenada.Y_POSITIVO);
		
		assertThat(novaCoordenada.getEixoX(), equalTo(1));
		assertThat(novaCoordenada.getEixoY(), equalTo(3));
	}
	
	@Test
	public void deveSubtrairUmAoEixoXMantendoEixoY(){
		Coordenada novaCoordenada = new Coordenada(1, 2).mudarCoordenada(MudancaCoordenada.X_NEGATIVO);
		
		assertThat(novaCoordenada.getEixoX(), equalTo(0));
		assertThat(novaCoordenada.getEixoY(), equalTo(2));
	}
	
	@Test
	public void deveSubtrairUmAoEixoYMantendoEixoX(){
		Coordenada novaCoordenada = new Coordenada(1, 2).mudarCoordenada(MudancaCoordenada.Y_NEGATIVO);
		
		assertThat(novaCoordenada.getEixoX(), equalTo(1));
		assertThat(novaCoordenada.getEixoY(), equalTo(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoAoMudarEixoXParaMenosDeZero(){
		new Coordenada(0, 2).mudarCoordenada(MudancaCoordenada.X_NEGATIVO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoAoMudarEixoYParaMenosDeZero(){
		new Coordenada(2, 0).mudarCoordenada(MudancaCoordenada.Y_NEGATIVO);
	}
}
*/