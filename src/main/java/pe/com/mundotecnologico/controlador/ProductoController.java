package pe.com.mundotecnologico.controlador;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.mundotecnologico.modelo.Producto;
import pe.com.mundotecnologico.servicio.CategoriaService;
import pe.com.mundotecnologico.servicio.MarcaService;
import pe.com.mundotecnologico.servicio.ProductoService;
import pe.com.mundotecnologico.utils.FileUploadUtil;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private MarcaService marcaService;
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public String listado(Model model) {
		model.addAttribute("listaProductos", productoService.listarProductos());
		return "listado_productos";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("reg", new Producto());
		model.addAttribute("listaMarcas", marcaService.listarMarcas());
		model.addAttribute("listaCategorias", categoriaService.listarCategorias());
		return "registrar_producto";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Producto reg, @RequestParam("fotoProducto") MultipartFile multipartFile, RedirectAttributes redirectAttrs) throws IOException {
		
		String nombreArchivo = (productoService.generarCodigo()) + ".jpg";
		reg.setFoto("/img/productos/"+nombreArchivo);
		reg.setCodigo(productoService.generarCodigo());
		FileUploadUtil.saveFile(nombreArchivo, multipartFile);

		if (productoService.registraActualizaProducto(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Producto '" + reg.getCodigo() + "' registrado correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/producto/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al registrar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/producto/listar";
		}
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", productoService.buscarProducto(codigo));
		model.addAttribute("listaMarcas", marcaService.listarMarcas());
		model.addAttribute("listaCategorias", categoriaService.listarCategorias());
		return "editar_producto";
	}
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Producto reg, @RequestParam("fotoProducto") MultipartFile multipartFile, RedirectAttributes redirectAttrs) throws IOException {
		
		String nombreArchivo = (reg.getCodigo()) + ".jpg";
		reg.setFoto("/img/productos/"+nombreArchivo);

		FileUploadUtil.saveFile(nombreArchivo, multipartFile);

		if (productoService.registraActualizaProducto(reg) != null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Producto '"+ reg.getCodigo() + "' editado correctamente.")
            .addFlashAttribute("clase", "success");
			return "redirect:/producto/listar";
		} else {
			redirectAttrs
            .addFlashAttribute("mensaje", "Hubo un error al registrar")
            .addFlashAttribute("clase", "danger");
			return "redirect:/producto/listar";
		}
	}
	
	@GetMapping("/detalle/{codigo}")
	public String detalle(@PathVariable("codigo") String codigo, Model model) {
		model.addAttribute("reg", productoService.buscarProducto(codigo));
		return "detalle_producto";
	}
	
	@GetMapping("/eliminar/{numeroCodigo}")
	public String eliminar(@PathVariable("numeroCodigo") int numeroCodigo) {
		productoService.eliminarProducto(productoService.generarCodigo(numeroCodigo));
		return "redirect:/producto/listar";
	}
	
}
