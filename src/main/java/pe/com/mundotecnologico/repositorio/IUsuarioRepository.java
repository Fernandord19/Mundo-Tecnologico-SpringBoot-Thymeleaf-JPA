package pe.com.mundotecnologico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pe.com.mundotecnologico.modelo.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

	@Query(nativeQuery = true,value = "call sp_validar_usuario(:correo, :clave)")  // call store procedure 
	Usuario validarUsuario(@Param("correo")String correo, @Param("clave")String clave);
	
	@Modifying
	@Transactional
	@Query(value = "update Usuario u set u.clave = ? where u.codigo = ?", 
	  nativeQuery = true)
	int restablecerClave(String clave, String codigo);
}
