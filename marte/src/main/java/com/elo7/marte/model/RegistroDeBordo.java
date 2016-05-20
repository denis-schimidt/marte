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
public class RegistroDeBordo {
	
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
	
	RegistroDeBordo(Sonda sonda, PosicaoAtual posicao){
		this.sonda = sonda;
		this.coordenada = posicao.getCoordenada();
		this.direcao = posicao.getDirecao();
		this.dataHora = posicao.getDataHora();
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public Direcao getDirecao() {
		return direcao;
	}

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
}
