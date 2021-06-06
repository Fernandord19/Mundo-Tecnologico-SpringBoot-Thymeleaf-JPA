package pe.com.mundotecnologico.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.mundotecnologico.modelo.Usuario;
import pe.com.mundotecnologico.servicio.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public String listado(Model model) {
		model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
		return "listado_usuarios";
	}

	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("reg", new Usuario());
		return "registrar_usuario";
	}

	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Usuario reg) {

		reg.setCodigo(usuarioService.generarCodigo());
		usuarioService.registraActualizaUsuario(reg);
		return "redirect:/usuario/listar";
	}

	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", usuarioService.buscarUsuario(codigo));
		return "editar_usuario";
	}

	@PostMapping("/editar")
	public String editar(@ModelAttribute Usuario reg) {
		
		reg.setClave(usuarioService.buscarUsuario(reg.getCodigo()).getClave());
		usuarioService.registraActualizaUsuario(reg);
		return "redirect:/usuario/listar";
	}

	@GetMapping("/detalle/{codigo}")
	public String detalle(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", usuarioService.buscarUsuario(codigo));
		return "detalle_usuario";
	}

	@GetMapping("/eliminar/{numeroCodigo}")
	public String eliminar(@PathVariable("numeroCodigo") int numeroCodigo) {
		usuarioService.eliminarUsuario(usuarioService.generarCodigo(numeroCodigo));
		return "redirect:/usuario/listar";
	}

}
