package pe.com.mundotecnologico.servicio;

import java.text.DecimalFormat;
import java.util.List;

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
	public Producto eliminarProducto(String codigo) {
		Producto reg = buscarProducto(codigo);
		reg.setEstado(2);
		return registraActualizaProducto(reg);
	}

	@Override
	public List<Producto> listarProductos() {
		return repo.findAll();
	}

	@Override
	public Producto buscarProducto(String codigo) {
		return repo.findById(codigo).get();
	}

	@Override
	public int getCantidadRegistros() {
		return (int) repo.count();
	}

	@Override
	public String generarCodigo() {
		String codigo = "PRO0001";
		if(getCantidadRegistros() > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "PRO" + df.format(getCantidadRegistros() + 1);
		}
		return codigo;
	}

	@Override
	public String generarCodigo(int numeroCodigo) {
		String codigo = "PRO0001";
		if(numeroCodigo > 0) {
			DecimalFormat df = new DecimalFormat("0000");
			codigo = "PRO" + df.format(numeroCodigo);
		}
		return codigo;
	}

	
	@Override
	public List<Producto> filtrarProductos(String criterio, String filtro) {
		return repo.filtrarProductos(criterio, filtro);
	}

}
