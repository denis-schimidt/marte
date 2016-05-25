package com.elo7.marte.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class RegistroDeBordoTest {
	
	private PosicaoAtual posicaoInicial1;
	
	private Sonda sonda1;
	
	@Before
	public void setUp(){
		posicaoInicial1 = PosicaoAtual.builder().comId(1L)
			.naCoordenada(new Coordenada(10,10))
			.naDataEHora(Calendar.getInstance())
			.naDirecao(Direcao.N)
			.build();

		sonda1 = new Sonda(posicaoInicial1, new Planalto(10, 100));
		posicaoInicial1.setSonda(sonda1);
	}

	@Test
	public void deveConsiderarDoisRegistrosDeBordoIguais(){
		PosicaoAtual posicaoInicial2 = posicaoInicial1.clonar();
		Sonda sonda2 = new Sonda(posicaoInicial2, new Planalto(10, 100));
		posicaoInicial2.setSonda(sonda2);
		
		RegistroDeBordo registroDeBordo1 = RegistroDeBordo.builder().naPosicao(posicaoInicial1).daSonda(sonda1).build();
		RegistroDeBordo registroDeBordo2 = RegistroDeBordo.builder().naPosicao(posicaoInicial2).daSonda(sonda2).build();
		
		assertThat(registroDeBordo1, equalTo(registroDeBordo2));
		assertThat(registroDeBordo1.hashCode(), equalTo(registroDeBordo2.hashCode()));
	}
	
	@Test
	public void deveConsiderarDoisRegistrosDeBordoDiferentes(){
		PosicaoAtual posicaoInicial2 = PosicaoAtual.builder()
			.comId(2L)
			.naCoordenada(new Coordenada(10,10))
			.naDataEHora(Calendar.getInstance())
			.naDirecao(Direcao.N)
			.build();
		
		Sonda sonda2 = new Sonda(posicaoInicial2, new Planalto(100, 100));
		posicaoInicial2.setSonda(sonda2);
		
		RegistroDeBordo registroDeBordo1 = RegistroDeBordo.builder().naPosicao(posicaoInicial1).daSonda(sonda1).build();
		RegistroDeBordo registroDeBordo2 = RegistroDeBordo.builder().naPosicao(posicaoInicial2).daSonda(sonda2).build();
		
		assertThat(registroDeBordo1, not(equalTo(registroDeBordo2)));
		assertThat(registroDeBordo1.hashCode(), not(equalTo(registroDeBordo2.hashCode())));
	}
}
