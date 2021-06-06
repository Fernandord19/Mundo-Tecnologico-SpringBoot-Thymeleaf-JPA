package pe.com.mundotecnologico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.mundotecnologico.modelo.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

	
	
}
