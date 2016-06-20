package com.elo7.marte.domain.sonda;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Preconditions;

@Embeddable
class EixoY implements Eixo, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,updatable=false,name="eixo_y")
	private int valor;

	EixoY() {}
	
	public EixoY(int valor) {
		Preconditions.checkArgument(valor>=0, "O eixo y deve ser maior ou igual a zero.");
		
		this.valor = valor;
	}

	@Override
	public EixoY moverParaFrente(Direcao direcao) {
		int valorTemp = valor;
		
		if(Direcao.SOUTH == direcao){
			return new EixoY(--valorTemp);
			
		}else if(Direcao.NORTH == direcao){
			return new EixoY(++valorTemp);
		}
		
		return this;
	}

	@Override
	public int getValor() {
		return valor;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EixoY other = (EixoY) obj;
		
		return Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
