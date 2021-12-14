package it.univpm.ProgettoEsame.filters;

import java.util.Vector;
import it.univpm.ProgettoEsame.model.Evento;

//Filtro per genere
public class GenreFilter {

public static Vector<Evento> FiltroGenere (String genere, Vector<Evento> eventiDaFiltrare) {
		
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(genere.equals(ev.getGenere()))
				eventiFiltrati.add(ev);
			
		}	
		return eventiFiltrati;
		
	}
}
