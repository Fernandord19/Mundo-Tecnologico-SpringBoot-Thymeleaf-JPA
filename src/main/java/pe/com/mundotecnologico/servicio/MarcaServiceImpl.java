package pe.com.mundotecnologico.servicio;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Marca;
import pe.com.mundotecnologico.repositorio.IMarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private IMarcaRepository repo;
	
	@Override
	public List<Marca> listarMarcas() {
		return repo.findAll();
	}

	@Override
	public int getCantidadRegistros() {
		return (int) repo.count();
	}

	@Override
	public Marca regActMarca(Marca reg) {
		return repo.save(reg);
	}

	@Override
	public Marca eliminarMarca(String codigo) {
		Marca reg = buscarMarca(codigo);
		reg.setEstado(2);
		return regActMarca(reg);
	}

	@Override
	public Marca buscarMarca(String codigo) {
		return repo.findById(codigo).get();
	}

	@Override
	public String generarCodigo() {
		String codigo = "MAR0001";
		if(getCantidadRegistros() > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "MAR" + df.format(getCantidadRegistros() + 1);
		}
		return codigo;
	}
	
	@Override
	public String generarCodigo(int numeroCodigo) {
		String codigo = "MAR0001";
		if(numeroCodigo > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "MAR" + df.format(numeroCodigo);
		}
		return codigo;
	}
}
