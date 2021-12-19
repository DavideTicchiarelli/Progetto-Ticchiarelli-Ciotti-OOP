package it.univpm.ProgettoEsame.stats;
import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

public class EventStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	private int[]monthsEvents=new int[12];
	
	@SuppressWarnings("unchecked")
	public int[] MonthsEvents(String stateCode) {

		Vector<Evento>eventiPerStato=new Vector<Evento>();
		eventiPerStato=service.getStatoEvents(stateCode);
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

	@SuppressWarnings("unchecked")
	public JSONObject TotEventi(Vector<Evento>eventidaFiltrare,String stateCode) {
		
		JSONObject obj=new JSONObject();

		int eventiTot=0;
		
		Evento ev=new Evento();
		monthsEvents=MonthsEvents(stateCode);
		
		for(int i=0;i<monthsEvents.length;i++) {
			eventiTot+=monthsEvents[i];	
			ev=eventidaFiltrare.get(i);
			
			
		}
		obj.put("eventi", eventiTot);	

		return obj;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject totEventi(String stateCode) {
		
		Vector<Evento>eventidaFiltrare=service.getStatoEvents(stateCode);
		JSONObject obj=new JSONObject();

		int eventiTot=0;
		
		Evento ev=new Evento();
		monthsEvents=MonthsEvents(stateCode);
		
		for(int i=0;i<monthsEvents.length;i++) {
			eventiTot+=monthsEvents[i];	
			ev=eventidaFiltrare.get(i);
			
			
		}
		obj.put("eventi", eventiTot);	

		return obj;
		
	}
	
}
