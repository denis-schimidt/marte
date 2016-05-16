package com.elo7.marte.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlanaltoTest {

	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExceptionParaEixoXNegativo(){
		new Planalto(-1, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExceptionParaEixoYNegativo(){
		new Planalto(0, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExceptionParaCoordenadaNula(){
		new Planalto(5, 5).isCoordenadaDentroPlanalto(null);
	}
	
	@Test
	public void deveConsiderarQueCoordenadaEstaDentroDoPlanalto(){
		Planalto planalto = new Planalto(5, 5);
		
		assertTrue(planalto.isCoordenadaDentroPlanalto(new Coordenada(5,5)));
	}
	
	@Test
	public void deveConsiderarQueCoordenadaEstaForaDoPlanaltoPeloEixoX(){
		Planalto planalto = new Planalto(5, 5);
		
		assertFalse(planalto.isCoordenadaDentroPlanalto(new Coordenada(6,5)));
	}
	
	@Test
	public void deveConsiderarQueCoordenadaEstaForaDoPlanaltoPeloEixoY(){
		Planalto planalto = new Planalto(5, 5);
		
		assertFalse(planalto.isCoordenadaDentroPlanalto(new Coordenada(5,6)));
	}
	
	@Test
	public void deveConsiderarDoisPlanaltosIguais(){
		Planalto planalto1 = new Planalto(5, 5);
		Planalto planalto2 = new Planalto(5, 5);
		
		assertEquals(planalto1, planalto2);
		assertEquals(planalto1.hashCode(), planalto2.hashCode());
	}
	
	@Test
	public void deveConsiderarDoisPlanaltosDiferentes(){
		Planalto planalto1 = new Planalto(2, 5);
		Planalto planalto2 = new Planalto(5, 2);
		
		assertNotEquals(planalto1, planalto2);
		assertNotEquals(planalto1.hashCode(), planalto2.hashCode());
	}
}
