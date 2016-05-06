package com.elo7.marte.model;

public class Coordenada implements Clonavel<Coordenada>{
	private int x;
	private int y;
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void mudarCoordenada(MoveEixo mudancaEixo){
		
		if(mudancaEixo!= null){
			if(mudancaEixo.getEixo() == Eixo.X){
				x+= mudancaEixo.getValor();
				
			}else if(mudancaEixo.getEixo() == Eixo.Y){
				y+= mudancaEixo.getValor();
			}
		}
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
	public Coordenada clonar() {
		return new Coordenada(x, y);
	}
}
