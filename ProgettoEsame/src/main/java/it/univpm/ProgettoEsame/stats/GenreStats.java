package it.univpm.ProgettoEsame.stats;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

public class GenreStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	
	@SuppressWarnings("unchecked")
	public JSONObject GenreEventi(Vector<Evento>eventiFiltrati,String genre) throws EventiException {
		
		int contGenre=0;
		
		JSONObject obj=new JSONObject();
		Evento ev=new Evento();
		
		for(int i=0;i<eventiFiltrati.size();i++) {
			
			ev=eventiFiltrati.get(i);
			
			if(genre.equals(ev.getGenere())) {
				contGenre++;
			}
			
		}
		
		if(eventiFiltrati.isEmpty()) {
			throw new EventiException("Nessun evento per il genere "+ev.getGenere());
		}
		obj.put("in "+ev.getStato(), contGenre);
		return obj;
	}
}
