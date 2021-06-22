package pe.com.mundotecnologico.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.mundotecnologico.modelo.Producto;
import pe.com.mundotecnologico.servicio.ProductoServiceImpl;

@Controller
@RequestMapping("/ecommerce")
public class EcommerceController {

	//Servicios
	@Autowired
	ProductoServiceImpl servicioProducto;
	
	@RequestMapping("/catalogo")
	public String catalogo(@RequestParam(name = "criterio", required = false, defaultValue = "") String criterio,
							@RequestParam(name = "filtro", required = false, defaultValue = "") String filtro,
							@RequestParam(name = "pagInicio", required = false, defaultValue = "0") int pagInicio,
							@RequestParam(name = "f", required = false, defaultValue = "") String f,
							Model modelo) {
		
		List<Producto> listaProductos = servicioProducto.filtrarProductos(criterio, filtro);
		
		// para la paginacion
        int filas = 6;
        int cantidadRegistros = listaProductos.size();
        int numPaginas = cantidadRegistros % filas == 0 ? cantidadRegistros/filas: cantidadRegistros / filas +1;
        
        // Establecer las condiciones de flecha
        switch (f)
        {
            case "derecha":
                pagInicio++;
                if (pagInicio > numPaginas - 1) pagInicio = numPaginas - 1;
                if (pagInicio < 0) pagInicio = 0;
                break;
            case "izquierda":
                pagInicio--;
                if (pagInicio < 0) pagInicio = 0;
                break;
            case "inicio":
                pagInicio = 0;
                break;
            case "fin":
                pagInicio = numPaginas - 1;
                if (pagInicio < 0) pagInicio = 0; // valida si solo hay una página
                break;
        }
        
		modelo.addAttribute("criterio", criterio);
		modelo.addAttribute("filtro", filtro);
		modelo.addAttribute("pagInicio", pagInicio);
        modelo.addAttribute("numPaginas", numPaginas);
        modelo.addAttribute("listaProductos", listaProductos.stream().skip(pagInicio * filas).limit(filas).collect(Collectors.toList()));
		
		return "catalogo";
	}
}
