package pe.com.mundotecnologico.servicio;

import java.util.List;

import pe.com.mundotecnologico.modelo.Usuario;

public interface UsuarioService {
	

	public abstract int getCantidadRegistros();

	public abstract Usuario registraActualizaUsuario(Usuario reg);

	public abstract Usuario eliminarUsuario(String codigo);

	public abstract List<Usuario> listarUsuarios();

	public abstract Usuario buscarUsuario(String codigo);

	public abstract String generarCodigo();

	public abstract String generarCodigo(int numeroCodigo);
	
}
