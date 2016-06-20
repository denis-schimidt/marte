package com.elo7.marte.domain.sonda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

@Embeddable
public class PosicaoDirecional implements Serializable{
	private static final long serialVersionUID = 1L;

	private Coordenada coordenada;
	
	@Column(name="direcao",nullable=false, updatable=false, length=1)
	private Direcao direcao;

	PosicaoDirecional() {}
	
	private PosicaoDirecional(Builder builder) {
		Preconditions.checkArgument(builder.direcao!= null, "A direção não pode ser nula.");
		
		this.coordenada = new Coordenada(new EixoX(builder.eixoX), new EixoY(builder.eixoY));
		this.direcao = builder.direcao;
	}
	
	public PosicaoDirecional atualizarPosicao(Comando comando){
		return comando.getAcao().executar(this);
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public Direcao getDirecao() {
		return direcao;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(coordenada,direcao);
	}
	
	public static Builder builder(){
		return new Builder();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicaoDirecional other = (PosicaoDirecional) obj;
		
		return Objects.equal(coordenada, other.coordenada) &&
				Objects.equal(direcao, other.direcao);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public static class Builder{
		private int eixoX;
		private int eixoY;
		private Direcao direcao;
		
		public Builder noEixoX(int x){
			this.eixoX = x;
			
			return this;
		}
		
		public Builder noEixoY(int y){
			this.eixoY = y;
			
			return this;
		}
		
		public Builder naCoordenada(Coordenada coordenada){
			this.eixoX = coordenada.getEixoX();
			this.eixoY = coordenada.getEixoY();
			
			return this;
		}
		
		public Builder apontandoPara(Direcao direcao){
			this.direcao = direcao;
			
			return this;
		}
		
		public PosicaoDirecional build(){
			return new PosicaoDirecional(this);
		}
	}
}
