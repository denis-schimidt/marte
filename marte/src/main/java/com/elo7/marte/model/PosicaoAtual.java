package com.elo7.marte.model;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.elo7.marte.model.exception.ComandoInvalidoException;
import com.elo7.marte.model.exception.CoordenadaInvalidaException;
import com.google.common.base.Preconditions;

@Table(name="posicao")
@Entity
public class PosicaoAtual implements PosicaoIdentificavel, Clonavel<PosicaoAtual>{

	@Id
	@GeneratedValue
	@Column(name="id_posicao", updatable=false)
	private Long id;
	
	@OneToOne(cascade=CascadeType.PERSIST, optional=false)
	@JoinColumn(updatable=false,nullable=false, name="id_sonda")
	private Sonda sonda;
	
	private Coordenada coordenada;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, updatable=false, length=1)
	private Direcao direcao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora", updatable=false,nullable=false)
	private Calendar dataHora;

	PosicaoAtual() {}
	
	private PosicaoAtual(Builder builder){
		Preconditions.checkArgument(builder.coordenada!= null, "A coordenada não pode ser nula.");
		Preconditions.checkArgument(builder.direcao!= null, "A direção não pode ser nula.");
		
		this.id = builder.id;
		this.sonda = builder.sonda;
		this.coordenada = builder.coordenada;
		this.direcao = builder.direcao;
		this.dataHora = builder.dataHora;
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
		Preconditions.checkArgument(planalto!=null, "O planalto não pode ser nulo para a posição.");
		
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

	@Override
	public Coordenada getCoordenada() {
		return coordenada;
	}

	@Override
	public Direcao getDirecao() {
		return direcao;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public Calendar getDataHora() {
		return (Calendar) dataHora.clone();
	}
	
	@Override
	public PosicaoAtual clonar() {
		return builder().comId(id)
				.daSonda(sonda)
				.naCoordenada(coordenada)
				.naDirecao(direcao)
				.naDataEHora(dataHora)
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
		PosicaoAtual other = (PosicaoAtual) obj;
		
		return Objects.equals(coordenada, other.coordenada) &&
				Objects.equals(direcao, other.direcao);
	}

	@Override
	public String toString() {
		return String.format("Posicao [id=%s, coordenada=%s, direcao=%s, dataHora=%4$td/%4$tm/%4$tY às %4$tH:%4$tM:%4$tS]", id, coordenada, direcao, dataHora);
	}

	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private Long id;
		private Sonda sonda;
		private Coordenada coordenada;
		private Direcao direcao;
		private Calendar dataHora;
		
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
		
		public Builder naDataEHora(Calendar dataHora) {
			this.dataHora = dataHora;
			
			return this;
		}
		
		public Builder naPosicao(PosicaoIdentificavel posicaoIdentificavel) {
			this.coordenada = posicaoIdentificavel.getCoordenada();
			this.dataHora = posicaoIdentificavel.getDataHora();
			this.direcao = posicaoIdentificavel.getDirecao();
			
			return this;
		}
		
		public PosicaoAtual build(){
			
			if(this.dataHora==null){
				naDataEHora(Calendar.getInstance());
			}
			
			return new PosicaoAtual(this);
		}
	}
}
