package pe.com.mundotecnologico.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	private String codigo;
	private String nombre;
	private String apellidos;
	private String correo;
	private String clave;
	private int estado;
	private String codigo_tipo_usuario;

	public int getNumeroCodigo() {
		return Integer.parseInt(codigo.substring(3));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getCodigo_tipo_usuario() {
		return codigo_tipo_usuario;
	}

	public void setCodigo_tipo_usuario(String codigo_tipo_usuario) {
		this.codigo_tipo_usuario = codigo_tipo_usuario;
	}
	
	public String getNombreCompleto() {
		return this.nombre + " " + this.apellidos;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", clave=" + clave + ", estado=" + estado + ", codigo_tipo_usuario=" + codigo_tipo_usuario + "]";
	}

}
