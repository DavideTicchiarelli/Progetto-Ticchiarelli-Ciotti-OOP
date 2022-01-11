package it.univpm.ProgettoEsame.stats;

import java.util.Vector;
import org.json.simple.JSONObject;
import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

/**
 * 
 * Classe che fornisce le statistiche degli eventi per un determinato genere.
 *
 */
public class GenreStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	/**
	 * 
	 * Classe che fornisce il numero totale di eventi per un determinato genere.
	 * 
	 * @param eventiFiltrati Vettore di eventi filtrati per un determinato genere.
	 * @param genre Genere per il filtro.
	 * @return obj JSONObject contente il numero di eventi per un determinato genere.
	 * @throws EventiException se non è presente alcun evento per un determinato genere.
	 */
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
