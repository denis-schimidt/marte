package com.elo7.marte.domain.planalto;

public class PossivelColisaoSondasNoPlanaltoException extends Exception {
	private static final long serialVersionUID = 1L;

	public PossivelColisaoSondasNoPlanaltoException() {
		super();
	}

	public PossivelColisaoSondasNoPlanaltoException(String message) {
		super(message);
	}

	public PossivelColisaoSondasNoPlanaltoException(String message, Object... params) {
		super(String.format(message, params));
	}
}
