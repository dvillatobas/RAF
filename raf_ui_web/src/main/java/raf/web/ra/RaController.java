package raf.web.ra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class RaController {
	
	
	
	@Autowired
	RaComponent agencia;
	
	@RequestMapping("/")
	public String main(Model model){
		model.addAttribute("title", "RAF");
		return "index";
	}
}
