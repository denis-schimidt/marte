package com.elo7.marte.infra;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.elo7.marte.model.Comando;
import com.elo7.marte.model.Coordenada;
import com.elo7.marte.model.Direcao;
import com.elo7.marte.model.Planalto;
import com.elo7.marte.model.PosicaoAtual;
import com.elo7.marte.model.Sonda;
import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SondaRepositoryTest {
	
	private static EntityManager em = Persistence.createEntityManagerFactory("JPA_PU").createEntityManager();

	private SondaRepository repository = new SondaRepositoryImpl(em);
	
	@Test
	public void gravarSonda() throws CoordenadaInvalidaException, ComandoInvalidoException{
		Planalto planalto = new Planalto(5, 5);
		Coordenada inicio = new Coordenada(1, 2);
		Direcao direcao = Direcao.N;
		List<Comando> comandos = Arrays.asList(Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.M, Comando.M);
		
		Sonda sonda = new Sonda(PosicaoAtual.builder().naCoordenada(inicio).naDirecao(direcao).build(), planalto);
		sonda.iniciarExploracao(comandos);
		
		repository.salvar(sonda);
		
		System.out.println(sonda.getPosicaoAtual());
	}
	
	@Test
	public void recuperarSonda() throws CoordenadaInvalidaException, ComandoInvalidoException{
		TypedQuery<Sonda> query = em.createQuery("SELECT s FROM Sonda s", Sonda.class); 
		
		List<Sonda> list = query.getResultList();
		
		System.out.println(list.get(0));
	}
}
