package pe.com.mundotecnologico.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.mundotecnologico.modelo.Usuario;
import pe.com.mundotecnologico.servicio.UsuarioService;
import pe.com.mundotecnologico.utils.Encrypt;

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
	public String registrar(@ModelAttribute Usuario reg, RedirectAttributes redirectAttrs) {

		reg.setCodigo(usuarioService.generarCodigo());
		if (usuarioService.registraActualizaUsuario(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Usuario '" + reg.getCodigo() + "' registrado correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/usuario/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al registrar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/usuario/listar";
		}
	}

	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", usuarioService.buscarUsuario(codigo));
		return "editar_usuario";
	}

	@PostMapping("/editar")
	public String editar(@ModelAttribute Usuario reg, RedirectAttributes redirectAttrs) {
		
		reg.setClave(usuarioService.buscarUsuario(reg.getCodigo()).getClave());
		if (usuarioService.registraActualizaUsuario(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Usuario '" + reg.getCodigo() + "' editado correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/usuario/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al editar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/usuario/listar";
		}
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
	
	@PostMapping("/validar")
	public String validar(@RequestParam(required = true, name = "correo", defaultValue = "") String correo,
						@RequestParam(required = true, name = "clave", defaultValue = "")String clave,
						RedirectAttributes redirectAttrs, HttpSession sesion) {
		Usuario reg = usuarioService.validarUsuario(correo, new Encrypt().getAES(clave));
		if(reg != null) {
		    sesion.setAttribute("usuario", reg);
		    return "redirect:/home/";
		}else {
		    redirectAttrs
            .addFlashAttribute("mensaje", "Crendenciales Incorrectas o Usaurio Eliminado")
            .addFlashAttribute("clase", "danger");
			return "redirect:/home/login";
		}
		
	}
	
	@GetMapping("/restablecer/{codigo}")
	public String restablecer(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("codigo", codigo);
		return "restablecer";
	}
	
	@PostMapping("/restablecer")
	public String restablecer(@RequestParam(required = true, name = "codigo", defaultValue = "") String codigo,
			@RequestParam(required = true, name = "clave", defaultValue = "")String clave,
			RedirectAttributes redirectAttrs) {
		if(usuarioService.restablecerClave( new Encrypt().getAES(clave), codigo) > 0) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Clave del usuario '" + codigo + "' restablecida correctamente")
            .addFlashAttribute("clase", "success");
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al restablecer")
            .addFlashAttribute("clase", "danger");
		}
		return "redirect:/usuario/listar";
	}

}
