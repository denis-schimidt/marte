package com.elo7.marte.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade=CascadeType.PERSIST, mappedBy="sonda", orphanRemoval=true)
	private PosicaoAtual posicaoAtual;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="sonda")
	private Set<RegistroDeBordo> registrosDeBordo = new HashSet<>();
	
	Sonda(){}
	
	public Sonda(PosicaoAtual posicaoInicial, Planalto planalto) {
		Preconditions.checkArgument(posicaoInicial!=null, "A posição inicial não pode ser nula para a sonda.");
		Preconditions.checkArgument(planalto!=null, "O planalto não pode ser nulo para a sonda.");
		
		this.posicaoAtual = posicaoInicial;
		this.planalto = planalto;
		
		posicaoInicial.setSonda(this);
		
		incluirRegistroDeBordo(posicaoInicial);
	}

	public void iniciarExploracao(List<Comando> comandos) throws CoordenadaInvalidaException, ComandoInvalidoException {
		
		for (Comando comando : comandos) {
			PosicaoAtual possivelNovaPosicao = posicaoAtual.clonar();
			possivelNovaPosicao.atualizarPosicao(comando, planalto);
			
			this.posicaoAtual = possivelNovaPosicao;
			incluirRegistroDeBordo(possivelNovaPosicao);
		}	
	}

	private void incluirRegistroDeBordo(PosicaoIdentificavel possivelNovaPosicao) {
		RegistroDeBordo registroDeBordo = RegistroDeBordo.builder()
			.naPosicao(possivelNovaPosicao)
			.daSonda(this)
			.build();
		
		this.registrosDeBordo.add(registroDeBordo);
	}

	public Long getId() {
		return id;
	}
	
	public PosicaoIdentificavel getPosicaoAtual() {
		return posicaoAtual;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(planalto,posicaoAtual);
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
				Objects.equals(posicaoAtual, other.posicaoAtual);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("Sonda [id=%s, planalto=%s, posicaoAtual=%s, registrosDeBordo=%s]", id, planalto,
				posicaoAtual, registrosDeBordo != null ? toString(registrosDeBordo, maxLen) : null);
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
}
