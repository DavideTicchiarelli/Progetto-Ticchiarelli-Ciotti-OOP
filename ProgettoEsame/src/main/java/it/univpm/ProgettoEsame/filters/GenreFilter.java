package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.GenreStats;


public class GenreFilter {

	public JSONObject FiltroGenere (String genere, Vector<Evento> eventiDaFiltrare) {
		GenreStats stats=new GenreStats();
		
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(genere.equals(ev.getGenere()))
				eventiFiltrati.add(ev);
			
		}	
	
		return stats.GenreEventi(eventiFiltrati, genere);
	}
	
	public Vector<Evento> Filtrogenere (String genere, Vector<Evento> eventiDaFiltrare) {
	
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(genere.equals(ev.getGenere()))
				eventiFiltrati.add(ev);
			
		}	
	
		return eventiFiltrati;
	}
}
