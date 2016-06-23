package com.elo7.marte.domain.model.sonda;

public class CoordenadaForaDoPlanaltoException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoordenadaForaDoPlanaltoException() {
		super();
	}

	public CoordenadaForaDoPlanaltoException(String message) {
		super(message);
	}

	public CoordenadaForaDoPlanaltoException(String message, Object... params) {
		super(String.format(message, params));
	}
}
