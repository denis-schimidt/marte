package com.elo7.marte.domain.sonda;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class Coordenada implements Serializable{
	private static final long serialVersionUID = 1L;

	private EixoX eixoX;
	
	private EixoY eixoY;
	
	Coordenada() {}
	
	public Coordenada(EixoX eixoX, EixoY eixoY) {
		Validate.noNullElements(new Object[]{eixoX, eixoY});
		
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}
	
	Coordenada moverAFrente(Direcao direcao){
		return new Coordenada(eixoX.moverParaFrente(direcao), eixoY.moverParaFrente(direcao));
	}

	public int getEixoX() {
		return eixoX.getValor();
	}
	
	public int getEixoY() {
		return eixoY.getValor();
	}

	@Override
	public int hashCode() {
		return Objects.hash(eixoX, eixoY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Coordenada other = (Coordenada) obj;
		
		return Objects.equals(getEixoX(), other.getEixoX()) &&
				Objects.equals(getEixoY(), other.getEixoY());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
