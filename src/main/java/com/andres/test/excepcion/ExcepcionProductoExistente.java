package com.andres.test.excepcion;

public class ExcepcionProductoExistente extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExcepcionProductoExistente(String message) {
		super(message);
	}
	
}
