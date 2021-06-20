package pe.com.mundotecnologico.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.mundotecnologico.modelo.Producto;

public interface IProductoRepository extends JpaRepository<Producto, String>{
	
	@Query(nativeQuery = true,value = "call sp_filtrar_productos(:criterio, :filtro)")  // call store procedure 
	List<Producto> filtrarProductos(@Param("criterio")String criterio, @Param("filtro")String filtro);

}
