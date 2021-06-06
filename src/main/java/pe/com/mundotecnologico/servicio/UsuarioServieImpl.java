package pe.com.mundotecnologico.servicio;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Usuario;
import pe.com.mundotecnologico.repositorio.IUsuarioRepository;

@Service
public class UsuarioServieImpl implements UsuarioService{

	@Autowired
	private IUsuarioRepository repo;

	@Override
	public Usuario registraActualizaUsuario(Usuario reg) {
		return repo.save(reg);
	}

	@Override
	public Usuario eliminarUsuario(String codigo) {
		Usuario reg = buscarUsuario(codigo);
		reg.setEstado(2);
		return registraActualizaUsuario(reg);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return repo.findAll();
	}

	@Override
	public Usuario buscarUsuario(String codigo) {
		return repo.findById(codigo).get();
	}

	@Override
	public int getCantidadRegistros() {
		return (int) repo.count();
	}

	@Override
	public String generarCodigo() {
		String codigo = "USU0001";
		if (getCantidadRegistros() > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "USU" + df.format(getCantidadRegistros() + 1);
		}
		return codigo;
	}

	@Override
	public String generarCodigo(int numeroCodigo) {
		String codigo = "USU0001";
		if (numeroCodigo > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "USU" + df.format(numeroCodigo);
		}
		return codigo;
	}
	
}
