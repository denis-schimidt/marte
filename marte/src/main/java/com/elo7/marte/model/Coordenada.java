package com.elo7.marte.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Preconditions;

@Embeddable
public class Coordenada{
	
	@Column(nullable=false,updatable=false,name="eixo_x")
	private int eixoX;
	
	@Column(nullable=false,updatable=false,name="eixo_y")
	private int eixoY;
	
	Coordenada() {}
	
	public Coordenada(int eixoX, int eixoY) {
		Preconditions.checkArgument(eixoX>=0, "O eixo x deve ser maior ou igual que zero.");
		Preconditions.checkArgument(eixoY>=0, "O eixo y deve ser maior ou igual que zero.");
		
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}
	
	public Coordenada mudarCoordenada(MudancaCoordenada movimento){
		int xAtual = eixoX;
		int yAtual = eixoY;
		
		if(movimento!= null){
			if(movimento.getEixo() == Eixo.X){
				xAtual+= movimento.getValor();
				
			}else if(movimento.getEixo() == Eixo.Y){
				yAtual+= movimento.getValor();
			}
		}
		
		return new Coordenada(xAtual, yAtual);
	}

	public int getEixoX() {
		return eixoX;
	}
	
	public int getEixoY() {
		return eixoY;
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
		return String.format("Coordenada [x=%s, y=%s]", eixoX, eixoY);
	}
}
