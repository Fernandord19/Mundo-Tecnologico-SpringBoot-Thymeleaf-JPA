package pe.com.mundotecnologico.servicio;

import java.util.List;

import pe.com.mundotecnologico.modelo.Marca;

public interface MarcaService {

	public abstract int getCantidadRegistros();
	public abstract List<Marca> listarMarcas();
	public abstract Marca regActMarca(Marca reg);
	public abstract Marca eliminarMarca(String codigo);
	public abstract Marca buscarMarca(String codigo);
	public abstract String generarCodigo();
	public abstract String generarCodigo(int numeroCodigo);
	
}
