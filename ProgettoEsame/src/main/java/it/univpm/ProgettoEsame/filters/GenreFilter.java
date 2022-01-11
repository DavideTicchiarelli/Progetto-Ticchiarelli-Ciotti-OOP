package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.GenreStats;

/**
 * Classe che filtra gli eventi per un determinato genere.
 *
 */
public class GenreFilter {

	/**
	 * Metodo che che fornisce la statistica degli eventi filtrati per genere.
	 * 
	 * @param genere Genere per il filtro.
	 * @param eventiDaFiltrare Vettore contenente gli eventi da filtrare.
	 * @return result JSONObject che fornisce la statistica degli eventi filtrati.
	 */
	public JSONObject FiltroGenere (String genere, Vector<Evento> eventiDaFiltrare) {
		GenreStats stats=new GenreStats();
		JSONObject result=new JSONObject();
		
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(genere.equals(ev.getGenere()))
				eventiFiltrati.add(ev);
		}	
	
		try {
			result=stats.GenreEventi(eventiFiltrati, genere);
		} catch (EventiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Metodo che filtra il vettore di eventi per un determinato genere.
	 * 
	 * @param genere Genere per il filtro.
	 * @param eventiDaFiltrare Vettore contenente gli eventi da filtrare.
	 * @return eventiFiltrati Vettore di eventi filtrati per genere.
	 */
	public Vector<Evento> Filtrogenere (String genere, Vector<Evento> eventiDaFiltrare) {
	
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(genere.equals(ev.getGenere()))
				eventiFiltrati.add(ev);
			
		}	
	
		return eventiFiltrati;
	}
}
