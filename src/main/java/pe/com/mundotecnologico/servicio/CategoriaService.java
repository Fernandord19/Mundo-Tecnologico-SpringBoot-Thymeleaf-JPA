package pe.com.mundotecnologico.servicio;

import java.util.List;

import pe.com.mundotecnologico.modelo.Categoria;

public interface CategoriaService {

	public abstract int getCantidadRegistros();
	public abstract Categoria registraActualizaCategoria(Categoria reg);
	public abstract Categoria eliminarCategoria(String codigo);
	public abstract List<Categoria> listarCategorias();
	public abstract Categoria buscarCategoria(String codigo);
	public abstract String generarCodigo();
	public abstract String generarCodigo(int numeroCodigo);
	
}
