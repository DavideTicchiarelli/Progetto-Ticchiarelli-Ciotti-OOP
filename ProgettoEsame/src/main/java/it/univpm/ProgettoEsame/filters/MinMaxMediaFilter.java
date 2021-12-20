package it.univpm.ProgettoEsame.filters;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.MinMaxMedia;

public class MinMaxMediaFilter {

	public JSONObject filtroPeriodo(String inizio,String fine,Vector<Evento>eventidaFiltrare) {
		
		JSONObject result=new JSONObject();
		
		MinMaxMedia stats=new MinMaxMedia();
		Vector<Evento>eventiFiltrati=new Vector<Evento>();
		
		eventiFiltrati=filtroperiodo(inizio,fine,eventidaFiltrare);
		result=stats.EventiMensiliperiodo(inizio,fine,eventiFiltrati);
		
		return result;
	}
	
	
	public LocalDate dateConverter(String date) {

		LocalDate localdate = LocalDate.parse((CharSequence) date);
		
		return localdate;
	
	}
	
	public Vector<Evento> filtroperiodo(String inizio,String fine,Vector<Evento>eventidaFiltrare) {
			
			Vector<Evento>eventiFiltrati=new Vector<Evento>();
			
			LocalDate dataIniziale=dateConverter(inizio);
			LocalDate dataFinale=dateConverter(fine);
			
			for(Evento ev:eventidaFiltrare) {
				if((dataFinale.isAfter(ev.getDate())||ev.getDate().isEqual(dataFinale))&&(dataIniziale.isBefore(ev.getDate())||ev.getDate().isEqual(dataIniziale)))
					eventiFiltrati.add(ev);
			}
			
			return eventiFiltrati;
			
		}

}
