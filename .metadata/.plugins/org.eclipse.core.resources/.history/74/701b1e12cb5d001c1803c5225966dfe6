package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import it.univpm.ProgettoEsame.model.Evento;

public class StateFilter {
	
	
	public static Vector<Evento> FiltroStati(String stato, Vector<Evento> eventiDaFiltrare) {
		
		Vector<Evento> eventiFiltrati = new Vector<Evento>();
		
		for (Evento ev : eventiDaFiltrare) {
			
			if(stato.equals(ev.getStato()))
				eventiFiltrati.add(ev);
			
		}
		
		return eventiFiltrati;
		
	}
}
