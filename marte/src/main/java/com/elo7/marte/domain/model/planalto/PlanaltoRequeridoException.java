package com.elo7.marte.domain.model.planalto;

public class PlanaltoRequeridoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PlanaltoRequeridoException() {
		super();
	}

	public PlanaltoRequeridoException(String message) {
		super(message);
	}

	public PlanaltoRequeridoException(String message, Object... params) {
		super(String.format(message, params));
	}
}
