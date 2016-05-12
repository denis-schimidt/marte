package com.elo7.marte.model;

public final class Coordenada{
	private final int x;
	private final int y;
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordenada mudarCoordenada(MudancaCoordenada movimento){
		int xAtual = x;
		int yAtual = y;
		
		if(movimento!= null){
			if(movimento.getEixo() == Eixo.X){
				xAtual+= movimento.getValor();
				
			}else if(movimento.getEixo() == Eixo.Y){
				yAtual+= movimento.getValor();
			}
		}
		
		return new Coordenada(xAtual, yAtual);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Coordenada [x=%s, y=%s]", x, y);
	}
}
