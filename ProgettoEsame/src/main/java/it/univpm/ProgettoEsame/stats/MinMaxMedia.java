package it.univpm.ProgettoEsame.stats;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Stato;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

public class MinMaxMedia {

	private int min;
	private int max;
	private double media;
	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();

	public MinMaxMedia() {}

	public int[] sortSelectedEvents(int[] numEventi) {

		int n = numEventi.length;
		int temp = 0;  

		for(int i = 0; i<n; i++){  

			for(int j=1; j<(n-i);j++){  

				if(numEventi[j-1] > numEventi[j]){ 

					temp = numEventi[j-1];  
					numEventi[j-1] = numEventi[j];  
					numEventi[j] = temp;  

				}  
			}  
		}    
		return numEventi;	
	}

	public int minEventi(int[]numeroEventi) {
		sortSelectedEvents(numeroEventi);
		return this.min=numeroEventi[0];	
	}

	public int maxEventi(int[]numeroEventi) {
		int max=numeroEventi.length;
		sortSelectedEvents(numeroEventi);
		return this.max=numeroEventi[max-1];
	}

	public double mediaEventi(int[]numeroEventi) {
		int cont=0;

		for(int i=0;i<numeroEventi.length;i++) {
			cont+=numeroEventi[i];
			this.media=(double)cont/(double)numeroEventi.length;
		}
		return this.media;
	}	
	
	@SuppressWarnings("unchecked")
    public JSONObject EventiMensili(String stateCode) {

        Stato st=new Stato(stateCode);
        st=service.getStatoEvents(stateCode);

        EventStats stat=new EventStats();

        min=minEventi(stat.getMonthsEvents());;
        max=maxEventi(stat.getMonthsEvents());
        media=mediaEventi(stat.getMonthsEvents());

        JSONObject obj=new JSONObject();
        obj.put("numero minimo di eventi mensili in "+st.getNomeStato(),min);
        obj.put("numero massimo di eventi mensili in "+st.getNomeStato(),max);
        obj.put("numero medio di eventi mensili in "+st.getNomeStato(),media);
        return obj;
    }
}



