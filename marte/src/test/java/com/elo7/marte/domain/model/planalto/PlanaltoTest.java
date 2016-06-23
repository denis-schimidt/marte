package com.elo7.marte.domain.model.planalto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.elo7.marte.domain.model.sonda.Coordenada;
import com.elo7.marte.domain.model.sonda.CoordenadaForaDoPlanaltoException;

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
	public void deveLancarExceptionParaCoordenadaNula() throws CoordenadaForaDoPlanaltoException{
		new Planalto(5, 5).validarCoordenadaDentroPlanalto(null);
	}
	
	@Test
	public void deveConsiderarQueCoordenadaEstaDentroDoPlanalto() throws CoordenadaForaDoPlanaltoException{
		Planalto planalto = new Planalto(5, 5);
		
		planalto.validarCoordenadaDentroPlanalto(new Coordenada(5,5));
	}
	
	@Test(expected=CoordenadaForaDoPlanaltoException.class)
	public void deveConsiderarQueCoordenadaEstaForaDoPlanaltoPeloEixoX() throws CoordenadaForaDoPlanaltoException{
		Planalto planalto = new Planalto(5, 5);
		
		planalto.validarCoordenadaDentroPlanalto(new Coordenada(6,5));
	}
	
	@Test(expected=CoordenadaForaDoPlanaltoException.class)
	public void deveConsiderarQueCoordenadaEstaForaDoPlanaltoPeloEixoY() throws CoordenadaForaDoPlanaltoException{
		Planalto planalto = new Planalto(5, 5);
		
		planalto.validarCoordenadaDentroPlanalto(new Coordenada(5,6));
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
