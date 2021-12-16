package it.univpm.ProgettoEsame.stats;
import java.time.LocalDate;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.model.Stato;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

public class EventStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	private int[]monthsEvents;
	
	@SuppressWarnings("unchecked")
	public JSONObject TotEventi(String stateCode) {

		Stato st=new Stato(stateCode);
		st=service.getStatoEvents(stateCode);
//
		int eventiTot=0;
//
//		monthsEvents=new int[12];
//
//		for(int i=0;i<st.getEvento().size();i++) {
//
//			Evento ev=new Evento();
//			ev=st.getEvento().get(i);
//			LocalDate mese1=ev.getDate(); 
//
//			for(int j=1;j<=12;j++) {
//
//				LocalDate mese2=mese1.withMonth(j);
//
//				if(mese1.equals(mese2)) {
//
//					int counter=j-1;
//					monthsEvents[counter]+=1;
//				} 
//				else
//				{					
//					int counter=j-1;
//					monthsEvents[counter]+=0;
//				}
//
//				int cont=j+1;
//				mese2.plusMonths(cont);
//			}
//		}
		
		MonthsEvents(stateCode);
		for(int i=0;i<monthsEvents.length;i++) {
			eventiTot+=monthsEvents[i];
		}

		JSONObject obj=new JSONObject();

		obj.put("Totale Eventi in "+st.getNomeStato(), eventiTot);
		return obj;

	}

	public int[] MonthsEvents(String stateCode) {
		
		Stato st=new Stato(stateCode);
		st=service.getStatoEvents(stateCode);
		
		int[] monthsEvents=new int[12];

		for(int i=0;i<st.getEvento().size();i++) {

			Evento ev=new Evento();
			ev=st.getEvento().get(i);
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

	
}
