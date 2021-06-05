package pe.com.mundotecnologico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.mundotecnologico.modelo.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	
	

}
