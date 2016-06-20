package com.elo7.marte.domain.sonda;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Preconditions;

@Embeddable
class EixoX implements Eixo, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,updatable=false,name="eixo_x")
	private int valor;

	EixoX() {}
	
	public EixoX(int valor) {
		Preconditions.checkArgument(valor>=0, "O eixo x deve ser maior ou igual a zero.");
		
		this.valor = valor;
	}

	@Override
	public EixoX moverParaFrente(Direcao direcao) {
		int valorTemp = valor;
		
		if(Direcao.WEST == direcao){
			return new EixoX(--valorTemp);
			
		}else if(Direcao.EAST == direcao){
			return new EixoX(++valorTemp);
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
		EixoX other = (EixoX) obj;
		
		return Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
