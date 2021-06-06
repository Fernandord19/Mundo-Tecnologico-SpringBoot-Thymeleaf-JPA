package pe.com.mundotecnologico.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.mundotecnologico.modelo.Categoria;
import pe.com.mundotecnologico.servicio.CategoriaService;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public String listado(Model model) {
		model.addAttribute("listaCategorias", categoriaService.listarCategorias());
		return "listado_categorias";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("reg", new Categoria());
		return "registrar_categoria";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Categoria reg) {
		
		reg.setCodigo(categoriaService.generarCodigo());
		categoriaService.registraActualizaCategoria(reg);
		return "redirect:/categoria/listar";
		
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", categoriaService.buscarCategoria(codigo));
		return "editar_categoria";
	}
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Categoria reg) {

		categoriaService.registraActualizaCategoria(reg);
		return "redirect:/categoria/listar";
		
	}
	
	@GetMapping("/detalle/{codigo}")
	public String detalle(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", categoriaService.buscarCategoria(codigo));
		return "detalle_categoria";
	}
	
	@GetMapping("/eliminar/{numeroCodigo}")
	public String eliminar(@PathVariable("numeroCodigo") int numeroCodigo) {
		categoriaService.eliminarCategoria(categoriaService.generarCodigo(numeroCodigo));
		return "redirect:/categoria/listar";
	}
	
}
