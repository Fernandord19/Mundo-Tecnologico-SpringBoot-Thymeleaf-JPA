package pe.com.mundotecnologico.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.mundotecnologico.modelo.Marca;
import pe.com.mundotecnologico.servicio.MarcaService;

@Controller
@RequestMapping(value = "/marca")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/listar")
	public String listarBuses(Model model) {
		model.addAttribute("listaMarcas", marcaService.listarMarcas());
		return "listado_marcas";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("reg", new Marca());
		return "registrar_marca";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Marca reg, Model model, RedirectAttributes redirectAttrs) {
		reg.setCodigo(marcaService.generarCodigo());
		
		if (marcaService.regActMarca(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Marca '" + reg.getCodigo() + "' registrada correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/marca/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al registrar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/marca/listar";
		}
	}
	
	@GetMapping("/editar/{codigo}")
	public String editarMarca(@PathVariable("codigo")String codigo, Model model) {
		model.addAttribute("reg",marcaService.buscarMarca(codigo));
		return "editar_marca";
	}
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Marca reg, RedirectAttributes redirectAttrs) {
		if (marcaService.regActMarca(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Marca '" + reg.getCodigo() + "' editada correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/marca/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al editar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/marca/listar";
		}
	}
	
	
	@GetMapping("/detalle/{codigo}")
	public String detalle(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", marcaService.buscarMarca(codigo));
		return "detalle_marca";
	}
	
	@GetMapping("/eliminar/{numeroCodigo}")
	public String eliminar(@PathVariable("numeroCodigo") int numeroCodigo) {
		marcaService.eliminarMarca(marcaService.generarCodigo(numeroCodigo));
		return "redirect:/marca/listar";
	}
	
}
