package pe.com.mundotecnologico.servicio;

import java.util.List;

import pe.com.mundotecnologico.modelo.Producto;

public interface ProductoService {
	
	public abstract int getCantidadRegistros();
	public abstract Producto registraActualizaProducto(Producto reg);
	public abstract Producto eliminarProducto(String codigo);
	public abstract List<Producto> listarProductos();
	public abstract Producto buscarProducto(String codigo);
	public abstract String generarCodigo();
	public abstract String generarCodigo(int numeroCodigo);
	
}
