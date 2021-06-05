package pe.com.mundotecnologico.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Categoria;
import pe.com.mundotecnologico.repositorio.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private ICategoriaRepository repo;

	@Override
	public List<Categoria> listarCategorias() {
		return repo.findAll();
	}
	
}
