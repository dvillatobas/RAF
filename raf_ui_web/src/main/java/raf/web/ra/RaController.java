package raf.web.ra;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RaController {
	
	
	
	@Autowired
	RaComponent agencia;
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public List<String> log(){
		List<String> lista = Arrays.asList(agencia.log.toString().split("\\n"));
		
		return lista;
	}
	
	@RequestMapping(value = "/getAgentes", method = RequestMethod.GET)
	public List<String> getAgentes(){
		
		return agencia.getListaClases();
	}
	
	@RequestMapping(value = "/getAgentesCargados", method = RequestMethod.GET)
	public List<String> getAgentesCargados(){
		
		return agencia.getListaAgentes();
	}
	
	@RequestMapping(value = "/addClass", method = RequestMethod.PUT)
	public String addClass(@RequestBody String clase){
		agencia.cargar(clase);
		
		return clase;
	}
	
	@RequestMapping(value = "/removeClass", method = RequestMethod.DELETE)
	public String removeClass(@RequestBody String clase){
		agencia.destroy(clase);
		
		return clase;
	}
}
