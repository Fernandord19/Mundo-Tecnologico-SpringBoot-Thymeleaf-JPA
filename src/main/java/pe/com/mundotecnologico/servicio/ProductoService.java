package pe.com.mundotecnologico.servicio;

import java.util.List;
import java.util.Optional;

import pe.com.mundotecnologico.modelo.Producto;

public interface ProductoService {
	
	public abstract Producto registraActualizaProducto(Producto reg);
	public abstract Producto eliminarProducto(int codigo);
	public abstract List<Producto> listarProductos();
	public abstract Optional<Producto> buscarProducto(int codigo);
	
}
