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
	RaDomainComponent domain;
	
	@Autowired
	AgencyComponent agencia;
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public List<String> log(){
		if(agencia.getLog()== null){
			System.err.println("nulo");
		}
		List<String> lista = Arrays.asList(agencia.getLog().split("\\n"));
		
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
		try {
			agencia.cargar(clase);
		} catch (Exception e) {
			System.out.println(e);
			agencia.agencyPrint(e.getMessage());
			return "error";
		}
		
		
		return clase;
	}
	
	@RequestMapping(value = "/removeClass", method = RequestMethod.PUT)
	public String removeClass(@RequestBody String clase){
		try {
			agencia.destroy(clase);
		} catch (Exception e) {
			System.out.println(e);
			return "error";
		}
		
		return clase;
	}
}
