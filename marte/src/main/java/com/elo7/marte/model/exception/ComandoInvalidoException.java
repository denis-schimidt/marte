package com.elo7.marte.model.exception;

public class ComandoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ComandoInvalidoException() {
		super();
	}

	public ComandoInvalidoException(String message) {
		super(message);
	}

	public ComandoInvalidoException(String message, Object... params) {
		super(String.format(message, params));
	}
}
