package com.andres.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.andres.test.models.Producto;
import com.andres.test.models.repository.ProductoRepository;
import com.andres.test.models.services.ProductoService;

public class ContinuosIntegrationApplicationTests {
	
	private ProductoRepository productoRepository;
	
		@Before
		public void setUp() {
			this.productoRepository = mock(ProductoRepository.class);
		}
		
		@Test
		public void validarExistenciaProducto() {
			//Arrange
			Producto producto = new Producto("Bolso", "Bolso grande", 4000, 4);
			ProductoService productoService = new ProductoService(productoRepository);
			when(this.productoRepository.elProductoExiste(producto.getNombre())).thenReturn(true);
			//Act //Assert
			try {
				productoService.guardar(producto);
			} catch (Exception e) {
				assertEquals("El producto es existente", e.getMessage());
			}
		}
		
		@Test
		public void validarCantidadDeProductos() {
			//Arrange
			Producto producto = new Producto("Bolso", "Bolso grande", 4000, 4);
			ProductoService productoService = new ProductoService(productoRepository);
			when(this.productoRepository.cantidadDeProductosStock()).thenReturn(59);
			//Act //Assert
			try {
				productoService.guardar(producto);
			} catch (Exception e) {
				assertEquals("Supera la cantidad de productos", e.getMessage());
			}
		}
}
