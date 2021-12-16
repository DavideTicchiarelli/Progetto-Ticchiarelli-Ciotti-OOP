package it.univpm.ProgettoEsame.service;


import org.json.simple.JSONObject;
import it.univpm.ProgettoEsame.model.Stato;


public interface TicketmasterService {
	
	public abstract JSONObject getJSONEvento(String stateCode);
	public abstract Stato getStatoAPI(String stateCode);
	public abstract Stato getStatoEvents(String stateCode);
	public abstract JSONObject toJSON(Stato stateCode);



}
