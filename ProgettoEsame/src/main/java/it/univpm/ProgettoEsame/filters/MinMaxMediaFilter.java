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
		Evento ev=new Evento();
		for(int i=0;i<eventiFiltrati.size();i++)
			ev=eventiFiltrati.get(i);
		
		result=stats.EventiMensili(ev.getStateCode());
		
		return result;
	}
	
	
	public LocalDate dateConverter(String date) {

		LocalDate locD = LocalDate.parse((CharSequence) date);
		
		return locD;
	
	}
	
	public Vector<Evento> filtroperiodo(String inizio,String fine,Vector<Evento>eventidaFiltrare) {
			
			JSONObject result=new JSONObject();
			
			Vector<Evento>eventiFiltrati=new Vector<Evento>();
			
			LocalDate dataIniziale=dateConverter(inizio);
			LocalDate dataFinale=dateConverter(fine);
			
			for(Evento ev:eventidaFiltrare) {
				if(dataFinale.isAfter(ev.getDate())&&dataIniziale.isBefore(ev.getDate()))
					eventiFiltrati.add(ev);
			}
			
			return eventiFiltrati;
		}
	
	
}
