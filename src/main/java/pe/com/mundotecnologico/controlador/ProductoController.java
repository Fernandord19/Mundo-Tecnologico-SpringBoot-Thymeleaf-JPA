package pe.com.mundotecnologico.controlador;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String registrar(@ModelAttribute Producto reg, Model model, @RequestParam("fotoProducto") MultipartFile multipartFile) throws IOException {
		
		String nombreArchivo = (productoService.listarProductos().size() +1) + ".jpg";
		reg.setFoto("/img/"+nombreArchivo);
		FileUploadUtil.saveFile(nombreArchivo, multipartFile);
		
		System.out.println(reg);
		productoService.registraActualizaProducto(reg);
		return "redirect:listar";
	}
	
}
