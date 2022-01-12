package it.univpm.ProgettoEsame.stats;

import java.util.Vector;
import org.json.simple.JSONObject;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;
/**
 * 
 * Classe che fornisce le statische del numero minimo,massimo e medio di eventi mensili o di un periodo personalizzato.
 *
 */
public class MinMaxMediaStats {

	private int min;
	private int max;
	private double media;
	TicketmasterServiceImpl service=new	TicketmasterServiceImpl();
	
/**
 * Costruttore della classe di default.
 */
	public MinMaxMediaStats() {}
	
/**
 * 
 * Metodo che ordina il vettore contenente il numero di eventi di uno stato in un determinato mese (in modo crescente).
 * 
 * @param numEventi Vettore da ordinare contente il numero di eventi mensili.
 * @return numEventi Vettore ordinato in modo crescente.
 */
	public int[] sortSelectedEvents(int[] numEventi) {

		int n=numEventi.length;
		int temp=0;  

		for(int i=0;i<n;i++){  

			for(int j=1;j<(n-i);j++){  

				if(numEventi[j-1]>numEventi[j]){ 

					temp=numEventi[j-1];  
					numEventi[j-1]=numEventi[j];  
					numEventi[j]=temp;  

				}  
			}  
		}    
		return numEventi;	
	}
	
	/**
	 * Metodo che restituisce il numero minimo di eventi.
	 * 
	 * @param numeroEventi Vettore contenente il numero di eventi.
	 * @return min Numero minimo di eventi.
	 */
	public int minEventi(int[]numeroEventi) {
		sortSelectedEvents(numeroEventi);
		return this.min=numeroEventi[0];	
	}

	/**
	 * Metodo che restituisce il numero massimo di eventi.
	 * 
	 * @param numeroEventi Vettore contenente il numero di eventi.
	 * @return max Numero massimo di eventi.
	 */ 
	public int maxEventi(int[]numeroEventi) {
		int max=numeroEventi.length;
		sortSelectedEvents(numeroEventi);
		return this.max=numeroEventi[max-1];
	}
	
	/**
	 * Metodo che restituisce la media degli eventi.
	 * 
	 * @param numeroEventi Vettore contenente il numero di eventi.
	 * @return media Media degli eventi in un determinato periodo.
	 */
	public double mediaEventi(int[]numeroEventi) {
		int cont=0;

		for(int i=0;i<numeroEventi.length;i++) {
			cont+=numeroEventi[i];
			this.media=(double)cont/(double)numeroEventi.length;
		}
		return this.media;
	}		
	
	/**
	 * 
	 * Metodo che fornisce il numero minimo,massimo e medio di eventi mensili e restituisce il corrispondente JSONObject.
	 * @param stateCode statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @return object JSONObject contente il nummero minimo,massimo e medio di eventi mensili.
	 */
	@SuppressWarnings("unchecked")
    public JSONObject EventiMensili(String stateCode) {

        EventStats stat=new EventStats();

        min=minEventi(stat.MonthsEvents(stateCode));
        max=maxEventi(stat.MonthsEvents(stateCode));
        media=mediaEventi(stat.MonthsEvents(stateCode));

        JSONObject obj=new JSONObject();
        JSONObject object=new JSONObject();
        
        obj.put("numero minimo di eventi",min);
        obj.put("numero massimo di eventi",max);
        obj.put("numero medio di eventi",media);
        object.put("Statistiche mensili",obj);
        return object;
    }
	
	/**
	 * 
	 * Metodo che fornisce il numero minimo,massimo e medio di eventi in un periodo personalizzato e restituisce il corrispondente JSONObject.
	 * @param inizio Data inizio del periodo personalizzato.
	 * @param fine   Data fine del periodo personalizzato.
	 * @param eventiFiltrati Vettore contente gli eventi filtrati per periodo.
	 * @return obj JSONObject contente il nummero minimo,massimo e medio di eventi in un periodo personalizzato.
	 */
	@SuppressWarnings("unchecked")
    public JSONObject EventiMensiliperiodo(String inizio,String fine,Vector<Evento>eventiFiltrati) {

		EventStats stat=new EventStats();
        min=minEventi(stat.MonthsEventsPeriodo(inizio,fine,eventiFiltrati));
        max=maxEventi(stat.MonthsEventsPeriodo(inizio,fine,eventiFiltrati));
        media=mediaEventi(stat.MonthsEventsPeriodo(inizio,fine,eventiFiltrati));

        JSONObject obj=new JSONObject();
        obj.put("numero minimo di eventi",min);
        obj.put("numero massimo di eventi",max);
        obj.put("numero medio di eventi",media);
        return obj;
    }
}



