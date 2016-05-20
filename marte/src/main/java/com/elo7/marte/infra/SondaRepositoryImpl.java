package com.elo7.marte.infra;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elo7.marte.model.RegistroDeBordo;
import com.elo7.marte.model.Sonda;

@Repository
public class SondaRepositoryImpl implements SondaRepository {

	private EntityManager em;

	@Autowired
	public SondaRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void salvar(Sonda sonda){
		em.getTransaction().begin();
		em.persist(sonda);
		em.getTransaction().commit();
	}
	
	@Override
	@Transactional
	public void salvar(RegistroDeBordo historicoPosicao){
		em.getTransaction().begin();
		em.persist(historicoPosicao);
		em.getTransaction().commit();
	}
}
