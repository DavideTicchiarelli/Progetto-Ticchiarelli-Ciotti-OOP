package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.EventStats;

public class StateFilter {
	
	
public JSONObject FiltroStati(String stato, Vector<Evento> eventiDaFiltrare) {

	EventStats stats=new EventStats();
		
		Vector<Evento> eventiFiltrati=new Vector<Evento>();
		
		for(Evento eventiTemp:eventiDaFiltrare) {
			
			if(stato.equals(eventiTemp.getStateCode()))
				eventiFiltrati.add(eventiTemp);

		}
	
		return 	stats.TotEventi(eventiFiltrati, stato);
		
	}
	
}
