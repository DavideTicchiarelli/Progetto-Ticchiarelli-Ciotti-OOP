package it.univpm.ProgettoEsame.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.EventStats;

/**
 * 
 *Classe che esegue il filtro degli eventi in base ad un determinato stato.
 */
public class StateFilter {

	/**
	 * Metodo che esegue il filtro degli eventi in base ad un determinato stato e fornisce la statistica degli eventi filtrati.
	 * 
	 * @param stato statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @param eventiDaFiltrare Vettore contenente gli eventi da filtrare.
	 * @return Statistica degli eventi filtrati in base ad un determinato stato.
	 */
	public JSONObject FiltroStati(String stato, Vector<Evento> eventiDaFiltrare) {

		EventStats stats=new EventStats();

		Vector<Evento> eventiFiltrati=new Vector<Evento>();

		for(Evento eventiTemp:eventiDaFiltrare) {

			if(stato.equals(eventiTemp.getStateCode()))
				eventiFiltrati.add(eventiTemp);

		}

		return 	stats.TotEventi(eventiFiltrati);

	}

}
