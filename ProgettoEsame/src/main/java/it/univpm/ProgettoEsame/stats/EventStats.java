package it.univpm.ProgettoEsame.stats;
import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.filters.MinMaxMediaFilter;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;
/**
 * 
 * EventsStats classe che fornisce le statistiche degli eventi.
 *
 */
public class EventStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	private int[]monthsEvents=new int[12];
	private int[] months;
	
	
	/**
	 * Metodo che analizza l'API e restituisce un vettore contente il numero di eventi mensili.
	 * 
	 * @param stateCode Statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @return monthsEvents Vettore contente il numero di eventi mensili.
	 */
	public int[] MonthsEvents(String stateCode) {

		Vector<Evento>eventiPerStato=new Vector<Evento>();
		
		try {
			eventiPerStato=service.getStatoEvents(stateCode);
		} catch (EventiException e) {
			e.printStackTrace();
		}
		Evento ev=new Evento();

		monthsEvents=new int[12];

		for(int i=0;i<eventiPerStato.size();i++) {

			
			ev=eventiPerStato.get(i);
			LocalDate mese1=ev.getDate(); 

			for(int j=1;j<=12;j++) {

				LocalDate mese2=mese1.withMonth(j);

				if(mese1.equals(mese2)) {

					int counter=j-1;
					monthsEvents[counter]+=1;
				} 
				else
				{					
					int counter=j-1;
					monthsEvents[counter]+=0;
				}

				int cont=j+1;
				mese2.plusMonths(cont);
			}
		}
		
		return monthsEvents;

	}
	
	/**
	 * 
	 * Metodo che analizza l'API e restituisce un vettore contente il numero di eventi mensili nel periodo personalizzato.
	 * 
	 * @param inizio Data inizio del periodo personalizzato.
	 * @param fine   Data fine del periodo personalizzato
	 * @param eventiFiltrati Vettore di eventi contente eventi filtrati per periodo.
	 * @return months Vettore contente il numero di eventi mensili nel periodo personalizzato.
	 */
	public int[] MonthsEventsPeriodo(String inizio,String fine,Vector<Evento>eventiFiltrati) {

		Evento ev=new Evento();
		MinMaxMediaFilter mma=new MinMaxMediaFilter();
		int dim;
		
		LocalDate dataIniziale=mma.dateConverter(inizio);
		LocalDate dataFinale=mma.dateConverter(fine);
		
		dim=(dataFinale.getMonthValue()-dataIniziale.getMonthValue())+1;
		
		months=new int[dim];

		for(int i=0;i<eventiFiltrati.size();i++) {

			ev=eventiFiltrati.get(i);
			LocalDate mese1=ev.getDate(); 

			for(int j=1;j<=dim;j++) {

				LocalDate mese2=mese1.withMonth(j);

				if(mese1.equals(mese2)) {

					int counter=j-1;
					months[counter]+=1;
				} 
				else
				{					
					int counter=j-1;
					months[counter]+=0;
				}

				int cont=j+1;
				mese2.plusMonths(cont);
			}
		}
		
		return months;

	}
	
/**
 *
 * Metodo che fornisce il JSONObject contente il numero totale di eventi di uno Stato (utilizzato nello StateFilter).
 * 
 * @param eventidaFiltrare Vettore contente gli eventi da filtrare.
 * @param stateCode Statecode dello stato di cui si vogliono visualizzare gli eventi.
 * @return obj JSONObject contenente il numero totale di eventi di uno Stato.
 */
	@SuppressWarnings("unchecked")
	public JSONObject TotEventi(Vector<Evento>eventidaFiltrare,String stateCode) {
		
		JSONObject obj=new JSONObject();

		int eventiTot=0;
		
		monthsEvents=MonthsEvents(stateCode);
		
		for(int i=0;i<monthsEvents.length;i++) {
			eventiTot+=monthsEvents[i];	
		}
		obj.put("eventi", eventiTot);	

		return obj;
		
	}
	
	/**
	 *
	 * Metodo che fornisce il JSONObject contente il numero totale di eventi di uno Stato (utilizzato nel Controller).
	 * 
	 * @param stateCode Statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @return obj JSONObject contenente il numero totale di eventi di uno Stato.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject totEventi(String stateCode) {

		JSONObject obj=new JSONObject();

		int eventiTot=0;
		
		monthsEvents=MonthsEvents(stateCode);
		
		for(int i=0;i<monthsEvents.length;i++) {
			eventiTot+=monthsEvents[i];	
			
		}
		obj.put("eventi", eventiTot);	

		return obj;
		
	}
	
}
