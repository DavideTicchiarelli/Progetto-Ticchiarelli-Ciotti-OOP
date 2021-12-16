package it.univpm.ProgettoEsame.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProgettoEsame.model.BodyEventi;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;
import it.univpm.ProgettoEsame.stats.EventStats;
import it.univpm.ProgettoEsame.stats.GenreStats;
import it.univpm.ProgettoEsame.stats.MinMaxMedia;

@RestController
public class TestTicketmasterController {
	
	@Autowired
	private TicketmasterServiceImpl ticketmasterservice;
	
	@GetMapping(value="/getStato")
	public ResponseEntity<Object>getStato(@RequestParam(name="stateCode",defaultValue="AZ")String stateCode){
		return new ResponseEntity<>(ticketmasterservice.toJSON(ticketmasterservice.getStatoAPI(stateCode)),HttpStatus.OK);
	}
    
	@GetMapping(value="/getEvento")
	public ResponseEntity<Object> getEventobyStato(@RequestParam(name="stateCode",defaultValue="AZ") String stateCode) {
			return new ResponseEntity<>(ticketmasterservice.toJSON(ticketmasterservice.getStatoEvents(stateCode)),HttpStatus.OK);
    }
	
	@GetMapping(value="/numEventi")
	public ResponseEntity<Object>getNumEventi(@RequestParam(name="stateCode")String stateCode){
		EventStats stats=new EventStats();
	
		return new ResponseEntity<>(stats.TotEventi(stateCode),HttpStatus.OK);
	}
	
	@GetMapping(value="/numGenere")
	public ResponseEntity<Object>getNumGeneri(@RequestParam(name="stateCode")String stateCode,
												@RequestParam(name="genre")String genre){
		GenreStats stats=new GenreStats();
		
		return new ResponseEntity<>(stats.GenreEventi(stateCode,genre),HttpStatus.OK);
	
	}
	
	@GetMapping(value="/eventiMensili")
	public ResponseEntity<Object>getEventiMensili(@RequestParam(name="stateCode")String stateCode){
		MinMaxMedia stats=new MinMaxMedia();
		return new ResponseEntity<>(stats.EventiMensili(stateCode),HttpStatus.OK);
	}
	
	@GetMapping(value="/Eventi")
	public JSONObject getEventi(@RequestBody BodyEventi eb){
		
		JSONObject risultato=new JSONObject();
		
		
		
		
		return risultato;
	}
	
}
