package com.elo7.marte.domain.planalto;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IdPosicaoPlanaltoTest {

	@Test
	public void deveConsiderarDoisIdsIguais(){
		IdPosicaoPlanalto idPosicaoPlanalto1 = new IdPosicaoPlanalto(1L, 2); 
		IdPosicaoPlanalto idPosicaoPlanalto2 = new IdPosicaoPlanalto(1L, 2);
		
		assertThat(idPosicaoPlanalto1.equals(idPosicaoPlanalto2), equalTo(true));
		assertThat(idPosicaoPlanalto1.hashCode() == idPosicaoPlanalto2.hashCode(), equalTo(true));
	}
	
	@Test
	public void deveConsiderarDoisIdsDiferentes(){
		IdPosicaoPlanalto idPosicaoPlanalto1 = new IdPosicaoPlanalto(1L, 2); 
		IdPosicaoPlanalto idPosicaoPlanalto2 = new IdPosicaoPlanalto(2L, 1);
		
		assertThat(idPosicaoPlanalto1.equals(idPosicaoPlanalto2), equalTo(false));
		assertThat(idPosicaoPlanalto1.hashCode() == idPosicaoPlanalto2.hashCode(), equalTo(false));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoParaAmbosCamposNulos(){
		new IdPosicaoPlanalto(null, null); 
	}
}
