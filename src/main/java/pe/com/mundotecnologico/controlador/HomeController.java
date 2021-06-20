package pe.com.mundotecnologico.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping("")
	public String index() {
		return "index";
	}
	
	@RequestMapping("nosotros")
	public String nosotros() {
		return "nosotros";
	}

}
