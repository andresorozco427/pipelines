package com.andres.test.models.services;

import com.andres.test.excepcion.ExcepcionCantidadDeProductos;
import com.andres.test.excepcion.ExcepcionProductoExistente;
import com.andres.test.models.Producto;
import com.andres.test.models.repository.ProductoRepository;

public class ProductoService {
	
	private ProductoRepository productoRepository;	
	
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	public void guardar(Producto producto) {
		validarExistenciaProducto(producto.getNombre());
		validarProductosEnStock(producto);
		
		this.productoRepository.guardar(producto);
	}

	private void validarExistenciaProducto(String producto) {
		if (elProductoEsExistente(producto)) {
			throw new ExcepcionProductoExistente("El producto es existente");
		}
		
	}

	private boolean elProductoEsExistente(String producto) {
		return this.productoRepository.elProductoExiste(producto);
	}

	private void validarProductosEnStock(Producto producto) {
		int cantidadDeProductos = this.productoRepository.cantidadDeProductosStock();
		if(cantidadDeProductos > 50) {
			throw new ExcepcionCantidadDeProductos("Supera la cantidad de productos");
		}
	}
}
