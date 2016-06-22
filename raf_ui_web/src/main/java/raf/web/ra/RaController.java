package raf.web.ra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import raf.principal.RaAddress;



@RestController
public class RaController {
	
	@Autowired
	RaDomainComponent domain;
	
	@Autowired
	AgencyComponent agencia;
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public List<String> log(){
		
		
		List<String> lista = Arrays.asList(agencia.getLog().split("\\n"));
		
		return lista;
	}
	
	@RequestMapping(value = "/getAgentes", method = RequestMethod.GET)
	public List<String> getAgentes(){
		
		return agencia.getListaClases();
	}
	
	@RequestMapping(value = "/getAgencias", method = RequestMethod.GET)
	public List<String> getAgencias(){
		ArrayList<RaAddress> lista = domain.raModel.getAgencys();
		List<String> nombres = new ArrayList<String>();
		for(RaAddress ra : lista){
			nombres.add(ra.host + ":" + ra.port);
		}
		return nombres;
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
	
	@RequestMapping(value = "/sendAgent", method = RequestMethod.PUT)
	public String sendAgent(@RequestBody String[] elem){
		agencia.agencyPrint("Enviando agente: "+ elem[0] + " a la agencia: " + elem[1]);
		agencia.sendTo(elem[1], elem[0]);
		return "ok";
	}
	
	
}
