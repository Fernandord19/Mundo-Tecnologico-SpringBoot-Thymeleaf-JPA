package pe.com.mundotecnologico.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.mundotecnologico.modelo.Producto;
import pe.com.mundotecnologico.repositorio.IProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private IProductoRepository repo;
	
	@Override
	public Producto registraActualizaProducto(Producto reg) {
		return repo.save(reg);
	}

	@Override
	public Producto eliminarProducto(int codigo) {
		Optional<Producto> reg = repo.findById(codigo);
		reg.get().setEstado(2);
		return registraActualizaProducto(reg.get());
	}

	@Override
	public List<Producto> listarProductos() {
		return repo.findAll();
	}

	@Override
	public Optional<Producto> buscarProducto(int codigo) {
		return repo.findById(codigo);
	}

}
