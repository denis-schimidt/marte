package com.elo7.marte.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;

@Table(name="registro_de_bordo")
@Entity
public class RegistroDeBordo implements PosicaoIdentificavel {
	
	@Id
	@GeneratedValue
	@Column(updatable=false,name="registro_bordo_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false,name="sonda_id")
	private Sonda sonda;
	
	private Coordenada coordenada;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, updatable=false, length=1)
	private Direcao direcao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora", updatable=false,nullable=false)
	private Calendar dataHora;

	RegistroDeBordo() {}
	
	private RegistroDeBordo(Builder builder){
		this.id = builder.id;
		this.sonda = builder.sonda;
		this.coordenada = builder.coordenada;
		this.direcao = builder.direcao;
		this.dataHora = builder.dataHora;
	}

	@Override
	public Coordenada getCoordenada() {
		return coordenada;
	}

	@Override
	public Direcao getDirecao() {
		return direcao;
	}

	@Override
	public Calendar getDataHora() {
		return (Calendar) dataHora.clone();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(coordenada,dataHora,direcao,sonda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroDeBordo other = (RegistroDeBordo) obj;
		
		return Objects.equal(coordenada, other.coordenada) &&
				Objects.equal(dataHora, other.dataHora) &&
				Objects.equal(direcao, other.direcao) &&
				Objects.equal(sonda, other.sonda);
	}

	@Override
	public String toString() {
		return String.format("RegistroDeBordo [id=%s, sondaId=%s, coordenada=%s, direcao=%s, dataHora=%5$td/%5$tm/%5$tY às %5$tH:%5$tM:%5$tS]", 
				id, sonda.getId(), coordenada, direcao, dataHora);
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
		
		public Builder naPosicao(PosicaoIdentificavel posicaoIdentificavel) {
			this.coordenada = posicaoIdentificavel.getCoordenada();
			this.dataHora = posicaoIdentificavel.getDataHora();
			this.direcao = posicaoIdentificavel.getDirecao();
			
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
		
		public RegistroDeBordo build(){
			
			if(this.dataHora==null){
				naDataEHora(Calendar.getInstance());
			}
			
			return new RegistroDeBordo(this);
		}
	}
}
