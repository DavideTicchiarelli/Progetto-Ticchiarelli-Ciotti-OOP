package it.univpm.ProgettoEsame.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProgettoEsame.filters.GenreFilter;
import it.univpm.ProgettoEsame.filters.MinMaxMediaFilter;
import it.univpm.ProgettoEsame.filters.StateFilter;
import it.univpm.ProgettoEsame.model.BodyEventi;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;
import it.univpm.ProgettoEsame.stats.EventStats;
import it.univpm.ProgettoEsame.stats.GenreStats;
import it.univpm.ProgettoEsame.stats.MinMaxMedia;

@RestController
public class TestTicketmasterController {
	
	@Autowired
	private TicketmasterServiceImpl ticketmasterservice;
    
	@GetMapping(value="/getEvento")
	public ResponseEntity<Object> getEventobyStato(@RequestParam(name="stateCode",defaultValue="AZ") String stateCode) {
			return new ResponseEntity<>(ticketmasterservice.toJSON(ticketmasterservice.getStatoEvents(stateCode)),HttpStatus.OK);
    }
	
	@GetMapping(value="/numEventi")
	public ResponseEntity<Object>getNumEventi(@RequestParam(name="stateCode")String stateCode){
		EventStats stats=new EventStats();
	
		return new ResponseEntity<>(stats.totEventi(stateCode),HttpStatus.OK);
	}
	
	@GetMapping(value="/numGenere")
	public ResponseEntity<Object>getNumGeneri(@RequestParam(name="stateCode")String stateCode,
												@RequestParam(name="genre")String genre){
		GenreStats stats=new GenreStats();
		
		return new ResponseEntity<>(stats.GenreEventi(ticketmasterservice.getStatoEvents(stateCode),genre),HttpStatus.OK);
	
	}
	
	@GetMapping(value="/stats")
	public ResponseEntity<Object>getStats(@RequestParam(name="stateCode")String stateCode,@RequestBody String body) throws ParseException{
		MinMaxMediaFilter filtro=new MinMaxMediaFilter();
		
		JSONObject Body;
		Body= (JSONObject)new JSONParser().parse(body);
		JSONObject periodo=(JSONObject)Body.get("periodo");
		String inizio=(String)periodo.get("inizio");
		String fine=(String)periodo.get("fine");
		

		return new ResponseEntity<>(filtro.filtroPeriodo(inizio,fine,ticketmasterservice.getStatoEvents(stateCode)),HttpStatus.OK);

	}
	
	
	@GetMapping(value="/eventiMensili")
	public ResponseEntity<Object>getEventiMensili(@RequestParam(name="stateCode")String stateCode){
		MinMaxMedia stats=new MinMaxMedia();
		return new ResponseEntity<>(stats.EventiMensili(stateCode),HttpStatus.OK);
	}
	
	/*
	 * {
    "stati":[
       { 
        "stato1":"AZ"
        },
       {
         "stato2":"NC"
        }
    ],
    "generi":[
        {
        "genere1":"Football"
        },
        {
        "genere2":"Basketball"
        }
     ],
    "periodo":[
        {
        "inizio":"2022-01-01"
        },
        {
        "fine":"2022-03-01"
        }
    ]
    }
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Eventi", method=RequestMethod.POST)
	public ResponseEntity<Object> getEventi(@RequestBody String body){

		JSONObject risultato=new JSONObject();
		Vector<Evento>eventidaFiltrare=new Vector<Evento>();
		Vector<Evento>eventidaFiltrare2=new Vector<Evento>();
		JSONObject eventiFiltratiPerStati=new JSONObject();
		JSONObject eventiFiltratiPerStati2=new JSONObject();
		JSONObject eventiFiltratiPerGenere=new JSONObject();
		JSONObject eventiFiltratiPerGenere2=new JSONObject();
		JSONObject eventiFiltratiPeriodo=new JSONObject();
		JSONObject eventiFiltratiPeriodo2=new JSONObject();

		BodyEventi eb;
		Vector<String>stati,generi,periodo;

		StateFilter filtrostati=new StateFilter();
		GenreFilter filtrogenere=new GenreFilter();
		MinMaxMediaFilter filtroperiodo=new MinMaxMediaFilter();

		eb=ticketmasterservice.readBody(body);

		stati=eb.getStati();
		generi=eb.getGeneri();
		periodo=eb.getPeriodo();
		String stato1=stati.get(0);
		String stato2=stati.get(1);
		String genere1=generi.get(0);
		String genere2=generi.get(1);
		String inizio=periodo.get(0);
		String fine=periodo.get(1);
	
		
		eventidaFiltrare=ticketmasterservice.getStatoEvents(stato1);
		eventidaFiltrare2=ticketmasterservice.getStatoEvents(stato2);

		eventiFiltratiPerStati=filtrostati.FiltroStati(stato1,eventidaFiltrare);
		eventiFiltratiPerGenere=filtrogenere.FiltroGenere(genere1,eventidaFiltrare);
		eventiFiltratiPeriodo=filtroperiodo.filtroPeriodo(inizio,fine, eventidaFiltrare);
		Evento ev1=new Evento();
		ev1=eventidaFiltrare.get(0);
		eventiFiltratiPerStati2=filtrostati.FiltroStati(stato2,eventidaFiltrare2);
		eventiFiltratiPerGenere2=filtrogenere.FiltroGenere(genere2,eventidaFiltrare2);
		eventiFiltratiPeriodo2=filtroperiodo.filtroPeriodo(inizio,fine, eventidaFiltrare2);
		Evento ev2=new Evento();
		ev2=eventidaFiltrare2.get(0);

		risultato.put("Totale Eventi in "+ev1.getStato(),eventiFiltratiPerStati);		
		risultato.put("Totale Eventi in "+ev2.getStato(),eventiFiltratiPerStati2);

		risultato.put("Eventi per il genere "+genere1,eventiFiltratiPerGenere);
		risultato.put("Eventi per il genere "+genere2,eventiFiltratiPerGenere2);

		risultato.put("Statistiche periodiche di eventi in "+ev1.getStato(), eventiFiltratiPeriodo);
		risultato.put("Statistiche periodiche di eventi in "+ev2.getStato(), eventiFiltratiPeriodo2);
		
		risultato.put("eventi in "+ev1.getStato(),ticketmasterservice.getResultEventi(stato1,genere1,inizio,fine));
		risultato.put("eventi in "+ev2.getStato(),ticketmasterservice.getResultEventi(stato2,genere2,inizio,fine));

		return new ResponseEntity<>(risultato,HttpStatus.OK);
	}

}
