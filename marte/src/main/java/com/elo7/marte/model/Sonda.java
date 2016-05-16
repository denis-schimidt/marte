package com.elo7.marte.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;
import com.google.common.base.Preconditions;

@Table(name="sonda")
@Entity
public class Sonda{

	@Id
	@GeneratedValue
	private Long id;
	
	private Planalto planalto;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="sonda", orphanRemoval=true)
	private List<Posicao> posicoes;

	Sonda(){}
	
	public Sonda(Posicao posicao, Planalto planalto) {
		Preconditions.checkArgument(planalto!=null, "O Planalto não pode ser nulo.");
		
		this.posicoes = new ArrayList<>();
		this.planalto = planalto;

		adicionarPosicao(posicao);
	}

	public void iniciarExploracao(List<Comando> comandos) throws CoordenadaInvalidaException, ComandoInvalidoException {
		
		for (Comando comando : comandos) {
			Posicao possivelNovaPosicao = getPosicaoAtual().clonar();
			possivelNovaPosicao.atualizarPosicao(comando, planalto);
			
			adicionarPosicao(possivelNovaPosicao);
		}	
	}
	
	public Posicao getPosicaoAtual() {
		return posicoes.get(posicoes.size()-1);
	}
	
	private void adicionarPosicao(Posicao posicao){
		Preconditions.checkArgument(posicao!=null, "A Posição não pode ser nula.");
		
		posicoes.add(posicao);
		posicao.setSonda(this);
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(planalto,posicoes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Sonda other = (Sonda) obj;
		return Objects.equals(planalto, other.planalto) &&
				Objects.equals(posicoes, other.posicoes);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("Sonda [id=%s, planalto=%s, posicoes=%s]", id, planalto,
				posicoes != null ? posicoes.subList(0, Math.min(posicoes.size(), maxLen)) : null);
	}
}
