package com.elo7.marte.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Preconditions;

@Embeddable
public class Planalto {
	
	@Column(nullable=false,name="ponto_maximo_x")
	private int pontoMaximoX;
	
	@Column(nullable=false,name="ponto_maximo_y")
	private int pontoMaximoY;
	
	Planalto() {}
	
	public Planalto(int pontoMaximoX, int pontoMaximoY) {
		Preconditions.checkArgument(pontoMaximoX >= 0, "O ponto máximo X do planalto não pode ser menor que zero.");
		Preconditions.checkArgument(pontoMaximoY >= 0, "O ponto máximo Y do planalto não pode ser menor que zero.");
		
		this.pontoMaximoX = pontoMaximoX;
		this.pontoMaximoY = pontoMaximoY;
	}
	
	public boolean isCoordenadaDentroPlanalto(Coordenada coordenada){
		Preconditions.checkArgument(coordenada!=null, "A coordenada não pode ser nula para o planalto.");
		
		return coordenada.getEixoX() >= 0 && coordenada.getEixoX() <= getPontoMaximoX() &&
				coordenada.getEixoY() >= 0 &&  coordenada.getEixoY() <= getPontoMaximoY();
	}

	public int getPontoMaximoX() {
		return pontoMaximoX;
	}
	
	public int getPontoMaximoY() {
		return pontoMaximoY;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pontoMaximoX,pontoMaximoY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Planalto other = (Planalto) obj;
		
		return Objects.equals(pontoMaximoX, other.pontoMaximoX) &&
				Objects.equals(pontoMaximoY, other.pontoMaximoY);
	}

	@Override
	public String toString() {
		return String.format("Planalto [pontoMaximoX=%s, pontoMaximoY=%s]", pontoMaximoX, pontoMaximoY);
	}
}
