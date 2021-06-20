package pe.com.mundotecnologico.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.mundotecnologico.servicio.ProductoServiceImpl;

@Controller
@RequestMapping("/ecommerce")
public class EcommerceController {

	//Servicios
	@Autowired
	ProductoServiceImpl servicioProducto;
	
	@RequestMapping("/catalogo")
	public String catalogo(@RequestParam(name = "criterio", required = false, defaultValue = "") String criterio,
							@RequestParam(name = "filtro", required = false, defaultValue = "") String filtro, Model modelo) {
		modelo.addAttribute("criterio", criterio);
		modelo.addAttribute("filtro", filtro);
		modelo.addAttribute("listaProductos", servicioProducto.filtrarProductos(criterio, filtro));
		return "catalogo";
	}
}
