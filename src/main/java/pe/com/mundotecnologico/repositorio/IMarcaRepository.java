package pe.com.mundotecnologico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.mundotecnologico.modelo.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
	
}
