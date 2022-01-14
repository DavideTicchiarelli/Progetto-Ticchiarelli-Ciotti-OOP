package it.univpm.ProgettoEsame.controller;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.filters.GenreFilter;
import it.univpm.ProgettoEsame.filters.MinMaxMediaFilter;
import it.univpm.ProgettoEsame.filters.StateFilter;
import it.univpm.ProgettoEsame.model.BodyEventi;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;
import it.univpm.ProgettoEsame.stats.EventStats;
import it.univpm.ProgettoEsame.stats.GenreStats;
import it.univpm.ProgettoEsame.stats.MinMaxMediaStats;

/**
 * Classe che gestisce tutte le chiamate.
 * 
 * @author Niccolò Ciotti
 * @author Davide Ticchiarelli
 *
 */
@RestController
public class TestTicketmasterController {
	
	@Autowired
	private TicketmasterServiceImpl ticketmasterservice;
    
	/**
	 * Rotta di tipo GET che mostra tutti gli eventi e le relative informazioni di un determinato stato.
	 * 
	 * @param stateCode stateCode dello stato di cui si vogliono conoscere gli eventi.
	 * @return insieme di eventi di un determinato stato.
	 */
	@GetMapping(value="/getEvento")
	public ResponseEntity<Object> getEventobyStato(@RequestParam(name="stateCode",defaultValue="AZ") String stateCode) {
		JSONObject result = new JSONObject();
			try {
				result=ticketmasterservice.toJSON(ticketmasterservice.getStatoEvents(stateCode));
			} catch (EventiException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(result,HttpStatus.OK);
			
    }
	
	/**
	 * Rotta di tipo GET che mostra il numero totale di eventi per un determinato stato.
	 * 
	 * @param stateCode stateCode dello stato di cui si vogliono conoscere gli eventi.
	 * @return il numero totale degli eventi.
	 */
	@GetMapping(value="/numEventi")
	public ResponseEntity<Object>getNumEventi(@RequestParam(name="stateCode")String stateCode){
		EventStats stats=new EventStats();
	
		return new ResponseEntity<>(stats.totEventi(stateCode),HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che mostra il numero di eventi per un determinato genere.
	 * 
	 * @param stateCode stateCode dello stato di cui si vogliono conoscere gli eventi.
	 * @param genre genere dell'evento.
	 * @return il numero di eventi per un determinato genere.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value="/numGenere")
	public ResponseEntity<Object>getNumGeneri(@RequestParam(name="stateCode")String stateCode,
												@RequestParam(name="genre")String genre){
		GenreStats stats=new GenreStats();
		JSONObject obj=new JSONObject();
		JSONObject result=new JSONObject();
		try {
			obj=stats.GenreEventi(ticketmasterservice.getStatoEvents(stateCode),genre);
			result.put("Eventi", obj);
			result.put("Lista", ticketmasterservice.getResultEventi(stateCode, genre));
		} catch (EventiException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che fornisce il numero minimo/massimo/medio di eventi mensili di uno stato.
	 * 
	 * @param stateCode stateCode dello stato di cui si vogliono conoscere gli eventi.
	 * @return il numero minimo/massimo/medio di eventi mensili di uno stato.
	 */
	@GetMapping(value="/eventiMensili")
	public ResponseEntity<Object>getEventiMensili(@RequestParam(name="stateCode")String stateCode){
		MinMaxMediaStats stats=new MinMaxMediaStats();
		return new ResponseEntity<>(stats.EventiMensili(stateCode),HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo POST che filtra in base a uno o piu stati, uno o piu generi
	 * e in base ad un oeriodo personalizzato per il calcolo degli eventi.
	 * 
	 * Il body inserito dall' utente deve essere di questo tipo:
	 * 
	 * {
	 *	"stati":[
     *  	{ 
     *  	"stato1":"AZ"
     * 	},
  	 * {
     *	"stato2":"NC"
     *	}
     * ],
     *	"generi":[
     *	{
     *	"genere1":"Football"
     *	},
     *	{
     *	"genere2":"Basketball"
     *	}
     * ],
     *	"periodo":
     *	{
     *	"inizio":"2022-01-01",
     *	"fine":"2022-03-01"
     *	}
     * }
     * 
     * @param body Body come indicato in precedenza.
     * @return le statistiche filtrate in base ai parametri inseriti.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Eventi",method=RequestMethod.POST)
	public ResponseEntity<Object> getEventi(@RequestBody String body){

		JSONObject risultato=new JSONObject();
		JSONArray eventi=new JSONArray();
		JSONArray eventi2=new JSONArray();
		Vector<Evento>eventidaFiltrare=new Vector<Evento>();
		Vector<Evento>eventidaFiltrare2=new Vector<Evento>();
		JSONObject eventiFiltratiPerStati=new JSONObject();
		JSONObject eventiFiltratiPerStati2=new JSONObject();
		JSONObject eventiFiltratiPerGenere=new JSONObject();
		JSONObject eventiFiltratiPerGenere2=new JSONObject();
		JSONObject eventiFiltratiPeriodo=new JSONObject();
		JSONObject eventiFiltratiPeriodo2=new JSONObject();
		JSONObject eventiFiltratiPerGenere3=new JSONObject();
		JSONObject eventiFiltratiPerGenere4=new JSONObject();

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
	
		
		try {
			eventidaFiltrare=ticketmasterservice.getStatoEvents(stato1);
		} catch (EventiException e) {
			e.printStackTrace();
		}
		
		try {
			eventidaFiltrare2=ticketmasterservice.getStatoEvents(stato2);
		} catch (EventiException e) {
			e.printStackTrace();
		}

		eventiFiltratiPerStati=filtrostati.FiltroStati(stato1,filtrogenere.FiltroPiuGeneri(genere1,genere2,filtroperiodo.filtroperiodo(inizio, fine, eventidaFiltrare)));
		eventiFiltratiPerGenere=filtrogenere.FiltroGenere(genere1,filtroperiodo.filtroperiodo(inizio, fine, eventidaFiltrare));
		eventiFiltratiPerGenere3=filtrogenere.FiltroGenere(genere1,filtroperiodo.filtroperiodo(inizio, fine,eventidaFiltrare2));
		eventiFiltratiPeriodo=filtroperiodo.filtroPeriodo(inizio,fine,eventidaFiltrare);
		Evento ev1=new Evento();
		ev1=eventidaFiltrare.get(0);
		eventiFiltratiPerStati2=filtrostati.FiltroStati(stato2,filtrogenere.FiltroPiuGeneri(genere2,genere1,filtroperiodo.filtroperiodo(inizio, fine, eventidaFiltrare2)));
		eventiFiltratiPerGenere2=filtrogenere.FiltroGenere(genere2,eventidaFiltrare2);
		eventiFiltratiPerGenere4=filtrogenere.FiltroGenere(genere2,eventidaFiltrare);
		eventiFiltratiPeriodo2=filtroperiodo.filtroPeriodo(inizio,fine, eventidaFiltrare2);
		Evento ev2=new Evento();
		ev2=eventidaFiltrare2.get(0);

		risultato.put("Eventi in "+ev1.getStato(),eventiFiltratiPerStati);		
		
		eventi.add(eventiFiltratiPerGenere);
		eventi.add(eventiFiltratiPerGenere3);
	
		risultato.put("Eventi per il genere "+genere1,eventi);
			
		risultato.put("Statistiche periodiche di eventi in "+ev1.getStato(), eventiFiltratiPeriodo);
		
		try {
			risultato.put("eventi in "+ev1.getStato(),ticketmasterservice.getResultEventiPeriodo(stato1,genere1,genere2,inizio,fine));
		} catch (EventiException e) {
			e.printStackTrace();
		}
		
		risultato.put("Eventi in "+ev2.getStato(),eventiFiltratiPerStati2);
		
		eventi2.add(eventiFiltratiPerGenere2);
		eventi2.add(eventiFiltratiPerGenere4);
		
		risultato.put("Eventi per il genere "+genere2,eventi2);
		
		risultato.put("Statistiche periodiche di eventi in "+ev2.getStato(), eventiFiltratiPeriodo2);	
		
		try {
			risultato.put("eventi in "+ev2.getStato(),ticketmasterservice.getResultEventiPeriodo(stato2,genere2,genere1,inizio,fine));
		} catch (EventiException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(risultato,HttpStatus.OK);
	}

}
