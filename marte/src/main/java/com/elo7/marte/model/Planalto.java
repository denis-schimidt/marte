package com.elo7.marte.model;

public final class Planalto {
	private final int x;
	private final int y;
	
	public Planalto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isCoordenadaDentroPlanalto(Coordenada coordenada){
		return coordenada.getX() >= 0 && coordenada.getX() <= getX() &&
				coordenada.getY() >= 0 &&  coordenada.getY() <= getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("Planalto [x=%s, y=%s]", x, y);
	}
}
