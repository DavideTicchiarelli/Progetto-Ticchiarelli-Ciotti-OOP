package it.univpm.ProgettoEsame.stats;
import java.time.LocalDate;
import java.util.Vector;

import it.univpm.ProgettoEsame.model.Evento;

public class EventStats {
	
public int[] numeroEventi(Vector<Evento> listaEventi) {
		
		int[] monthsEvents=new int[12];
		
		for(int i=0;i<listaEventi.size();i++) {
			
			Evento ev=new Evento();
			ev=listaEventi.get(i);
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
