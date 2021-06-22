package pe.com.mundotecnologico.servicio;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Categoria;
import pe.com.mundotecnologico.repositorio.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private ICategoriaRepository repo;

	@Override
	public int getCantidadRegistros() {
		return (int) repo.count();
	}

	@Override
	public Categoria registraActualizaCategoria(Categoria reg) {
		try {
			return repo.save(reg);
		} catch (Exception e) {
			return null;
		} 
	}

	@Override
	public Categoria eliminarCategoria(String codigo) {
		Categoria reg = buscarCategoria(codigo);
		reg.setEstado(2);
		return registraActualizaCategoria(reg);
	}

	@Override
	public List<Categoria> listarCategorias() {
		return repo.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public Categoria buscarCategoria(String codigo) {
		return repo.findById(codigo).get();
	}

	@Override
	public String generarCodigo() {
		String codigo = "CAT0001";
		if(getCantidadRegistros() > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "CAT" + df.format(getCantidadRegistros() + 1);
		}
		return codigo;
	}

	@Override
	public String generarCodigo(int numeroCodigo) {
		String codigo = "CAT0001";
		if(numeroCodigo > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "CAT" + df.format(numeroCodigo);
		}
		return codigo;
	}

	
	
}
