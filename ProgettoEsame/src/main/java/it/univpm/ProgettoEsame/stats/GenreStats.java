package it.univpm.ProgettoEsame.stats;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.model.Stato;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

public class GenreStats {

	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	
	@SuppressWarnings("unchecked")
	public JSONObject GenreEventi(String stateCode,String genre) {
		
		Stato st=new Stato(stateCode);
		st=service.getStatoEvents(stateCode);
		
		int contGenre=0;
		
		JSONObject obj=new JSONObject();
		
		for(int i=0;i<st.getEvento().size();i++) {
			Evento ev=new Evento();
			ev=st.getEvento().get(i);
			
			if(genre.equals(ev.getGenere())) {
				contGenre++;
			}
			
		}
		
		obj.put("eventi per il genere "+genre+" in "+st.getNomeStato(), contGenre);
		return obj;
	}
}
