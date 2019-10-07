package com.andres.test.models.repository;

import com.andres.test.models.Producto;

public interface ProductoRepository {

	public void guardar(Producto producto);
	
	public int cantidadDeProductosStock();
	
	public boolean elProductoExiste(String nombre);
}
