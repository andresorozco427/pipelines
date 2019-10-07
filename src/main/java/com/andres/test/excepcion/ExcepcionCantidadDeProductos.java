package com.andres.test.excepcion;

public class ExcepcionCantidadDeProductos extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExcepcionCantidadDeProductos(String message) {
		super(message);
	}

	
}
