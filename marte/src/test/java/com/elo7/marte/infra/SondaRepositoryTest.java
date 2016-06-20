package com.elo7.marte.infra;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.elo7.marte.MarteApplication;
import com.elo7.marte.domain.planalto.Planalto;
import com.elo7.marte.domain.planalto.PossivelColisaoSondasNoPlanaltoException;
import com.elo7.marte.domain.sonda.Comando;
import com.elo7.marte.domain.sonda.CoordenadaForaDoPlanaltoException;
import com.elo7.marte.domain.sonda.Direcao;
import com.elo7.marte.domain.sonda.PosicaoDirecional;
import com.elo7.marte.domain.sonda.Sonda;
import com.elo7.marte.domain.sonda.SondaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarteApplication.class)
@Transactional
@IntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SondaRepositoryTest {
	
	@Autowired
	private SondaRepository repository;
	
	@Test
	@Commit
	public void gravarSonda() throws CoordenadaForaDoPlanaltoException, PossivelColisaoSondasNoPlanaltoException{
		Planalto planalto = new Planalto(5, 5);
		List<Comando> comandos = Arrays.asList(Comando.LEFT, Comando.MOVES_FOWARD, Comando.LEFT, Comando.MOVES_FOWARD, Comando.LEFT, Comando.MOVES_FOWARD, Comando.LEFT, Comando.MOVES_FOWARD, Comando.MOVES_FOWARD);
		
		Sonda sonda = new Sonda(PosicaoDirecional.builder().noEixoX(1).noEixoY(2).apontandoPara(Direcao.NORTH).build(), planalto);
		
		Set<PosicaoDirecional> posicoesOutraSonda = new HashSet<>();
		posicoesOutraSonda.add(PosicaoDirecional.builder().noEixoX(1).noEixoY(8).apontandoPara(Direcao.NORTH).build());
		
		sonda.iniciarExploracao(comandos, posicoesOutraSonda);
		
		repository.save(sonda);
		
		System.out.println(sonda);
	}
	
	@Test
	public void recuperarSonda(){
		repository.findAll().forEach(System.out::println);
	}
}
