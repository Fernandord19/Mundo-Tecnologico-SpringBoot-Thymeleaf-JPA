package pe.com.mundotecnologico.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pe.com.mundotecnologico.modelo.Marca;
import pe.com.mundotecnologico.repositorio.IMarcaRepository;

@Controller
public class MarcaController {
	
	@GetMapping("marca/registrar")
	public String registrarMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "registrar_marca";
	}
	
	@GetMapping("marca/editar")
	public String editarMarca(@ModelAttribute Marca p, Model model) {
		model.addAttribute("marca",repo.findById(p.getCodigo()));
		return "editar_marca";
	}
	
	@GetMapping("marca/eliminar")
	public String eliminarMarca(@ModelAttribute Marca m, Model model) {
		model.addAttribute("listaMarcas", repo.findAll());
		return "listado_marcas";
	}
	
	@Autowired
	private IMarcaRepository repo;
	
	@PostMapping("marca/guardar")
	public String actualizarBus(@ModelAttribute Marca marca, Model model) {
		
		try {
			repo.save(marca);
			model.addAttribute("exito", "Datos de la marca " + marca.getCodigo() + " actualizados correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", "Hubo un error: " + e.getMessage());
		} finally {
			model.addAttribute("listaMarcas", repo.findAll());
		}
		
		return "listado_marcas";
	}
	
	@GetMapping("marca/listar")
	public String listarMarcas(Model model) {
		model.addAttribute("listaMarcas", repo.findAll());
		return "listado_marcas";
	}
	
}
