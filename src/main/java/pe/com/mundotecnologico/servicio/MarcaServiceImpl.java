package pe.com.mundotecnologico.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Marca;
import pe.com.mundotecnologico.repositorio.IMarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private IMarcaRepository repo;

	@Override
	public List<Marca> listarMarcas() {
		return repo.findAll();
	}
	
}
