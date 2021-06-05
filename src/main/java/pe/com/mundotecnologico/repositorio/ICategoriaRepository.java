package pe.com.mundotecnologico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.mundotecnologico.modelo.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, String>{
	
}
