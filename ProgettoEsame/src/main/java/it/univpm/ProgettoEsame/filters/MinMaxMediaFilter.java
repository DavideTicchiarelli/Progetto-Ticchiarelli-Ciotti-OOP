package it.univpm.ProgettoEsame.filters;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.MinMaxMedia;

/**
 * Classe che permette di filtrare gli eventi in base ad un periodo personalizzato.
 *
 */
public class MinMaxMediaFilter {

	/**
	 * Metodo che filtra il vettore di eventi in base ad un periodo personalizzato. 
	 * 
	 * @param inizio Data inizio del periodo personalizzato.
	 * @param fine Data fine del periodo personalizzato.
	 * @param eventidaFiltrare Vettore contenente gli eventi da filtrare.
	 * @return eventiFiltrati Vettore contenente gli eventi filtrati.
	 */
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
	
	/**
	 * Metodo che filtra il vettore di eventi in base ad un periodo personalizzato e fornisce la statistica. 
	 * 
	 * @param inizio Data inizio del periodo personalizzato.
	 * @param fine Data fine del periodo personalizzato.
	 * @param eventidaFiltrare Vettore contenente gli eventi da filtrare.
	 * @return result JSONObject che contiene la statistica degli eventi filtrati per periodo personalizzato.
	 */
	public JSONObject filtroPeriodo(String inizio,String fine,Vector<Evento>eventidaFiltrare) {
		
		JSONObject result=new JSONObject();
		
		MinMaxMedia stats=new MinMaxMedia();
		Vector<Evento>eventiFiltrati=new Vector<Evento>();
		
		eventiFiltrati=filtroperiodo(inizio,fine,eventidaFiltrare);
		result=stats.EventiMensiliperiodo(inizio,fine,eventiFiltrati);
		
		return result;
	}
	
	/**
	 * Metodo che converte una data in formato stringa in formato LocalDate.
	 * 
	 * @param date Stringa della data.
	 * @return localDate LocalDate della data.
	 */
	public LocalDate dateConverter(String date) {

		LocalDate localdate = LocalDate.parse((CharSequence) date);
		
		return localdate;
	
	}
	
}
