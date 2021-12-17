package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.model.Stato;

public class StateFilter {
	
	
public Vector<Evento> FiltroStati(String stato, Vector<Evento> eventiDaFiltrare) {
		
		Vector<Evento> eventiFiltrati=new Vector<Evento>();
		
		for(Evento eventiTemp:eventiDaFiltrare) {
			
			if(stato.equals(eventiTemp.getStato()))
				eventiFiltrati.add(eventiTemp);
			
		}
		
		return eventiFiltrati;
		
	}
	
}
