package com.elo7.marte.model.exception;

public class CoordenadaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoordenadaInvalidaException() {
		super();
	}

	public CoordenadaInvalidaException(String message) {
		super(message);
	}

	public CoordenadaInvalidaException(String message, Object... params) {
		super(String.format(message, params));
	}
}
