package com.elo7.marte.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;
import com.google.common.base.Preconditions;

@Table(name="posicao")
@Entity
public class Posicao implements Clonavel<Posicao>{

	@Id
	@GeneratedValue
	@Column(name="id_posicao", updatable=false)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST, optional=false)
	@JoinColumn(updatable=false,nullable=false, name="id_sonda")
	private Sonda sonda;
	
	private Coordenada coordenada;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, updatable=false, length=1)
	private Direcao direcao;

	Posicao() {}
	
	private Posicao(Builder builder){
		Preconditions.checkArgument(builder.coordenada!=null, "A coordenada da posição não pode ser nula.");
		Preconditions.checkArgument(builder.direcao!=null, "A direção da posição não pode ser nula.");
		
		this.coordenada = builder.coordenada;
		this.direcao = builder.direcao;
		this.id = builder.id;
		this.sonda = builder.sonda;
	}
	
	public void atualizarPosicao(Comando comando, Planalto planalto) throws CoordenadaInvalidaException, ComandoInvalidoException {

		if (comando == Comando.M) {
			coordenada = moverParaFrente(planalto);

		} else if (comando == Comando.L) {
			direcao = direcao.getEsquerda();

		} else if (comando == Comando.R){
			direcao = direcao.getDireita();
		
		}else{
			throw new ComandoInvalidoException("Comando %s é inválido." , comando);
		}
	}

	private Coordenada moverParaFrente(Planalto planalto) throws CoordenadaInvalidaException {
		Preconditions.checkArgument(planalto!=null, "O planalto não pode ser nulo.");
		
		Coordenada novaCoordenada = coordenada.mudarCoordenada(MudancaCoordenada.irParaFrente(direcao));
		
		if (!planalto.isCoordenadaDentroPlanalto(novaCoordenada)) {
			throw new CoordenadaInvalidaException("A %s ultrapassou os limites do %s", novaCoordenada, planalto);
		}

		return novaCoordenada;
	}
	
	void setSonda(Sonda sonda) {
		Preconditions.checkArgument(sonda!=null, "A sonda não pode ser nula para a posicão.");
		
		this.sonda = sonda;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public Direcao getDirecao() {
		return direcao;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public Posicao clonar() {
		return builder().comId(id)
				.daSonda(sonda)
				.naCoordenada(coordenada)
				.naDirecao(direcao)
				.build();
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenada,direcao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		
		return Objects.equals(coordenada, other.coordenada) &&
				Objects.equals(direcao, other.direcao);
	}

	@Override
	public String toString() {
		return String.format("Posicao [id=%s, coordenada=%s, direcao=%s]", id, coordenada, direcao);
	}

	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private Long id;
		private Sonda sonda;
		private Coordenada coordenada;
		private Direcao direcao;
		
		public Builder comId(Long id) {
			this.id = id;
			
			return this;
		}
		
		public Builder daSonda(Sonda sonda) {
			this.sonda = sonda;
			
			return this;
		}
		
		public Builder naCoordenada(Coordenada coordenada) {
			this.coordenada = coordenada;
			
			return this;
		}
		
		
		public Builder naDirecao(Direcao direcao) {
			this.direcao = direcao;
			
			return this;
		}
		
		public Posicao build(){
			return new Posicao(this);
		}
	}
}
