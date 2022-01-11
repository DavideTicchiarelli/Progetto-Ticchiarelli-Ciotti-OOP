package it.univpm.ProgettoEsame;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.service.TicketmasterServiceImpl;

/**
 * Classe che testa il funzionamento del metodo toJSON.
 *
 */
class ToJSONtest {

	Evento ev;
	Vector <Evento> eventi;
	TicketmasterServiceImpl test;
	
	/**
	 * Inizializza i componenti necessari per il test.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		eventi=new Vector<Evento>();
		ev=new Evento();
		test=new TicketmasterServiceImpl();
	}

	/**
	 * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test che verifica se l'oggetto eventi viene convertito correttamente in JSONObject.
	 */
	@SuppressWarnings("unchecked")
	@Test
	void test() {
		
	String tempDate="2022-01-22";
	LocalDate date= LocalDate.parse(tempDate);

		ev.setNome("Phoenix Suns vs. Indiana Pacers");
		ev.setUrl("https://www.ticketmaster.com/phoenix-suns-vs-indiana-pacers-phoenix-arizona-01-22-2022/event/19005B13479C3E4B");
		ev.setCitta("Phoenix");
		ev.setStato("Arizona");
		ev.setStateCode("AZ");
		ev.setDate(date);
		ev.setOra("19:00:00");
		ev.setGenere("Basketball");
		
		eventi.add(ev);
		
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray ();
		
		for (int i=0;i<eventi.size();i++) {
			
			JSONObject Ev=new JSONObject();

			Ev.put("name", ev.getNome());
			Ev.put("url", ev.getUrl());
			Ev.put("city", ev.getCitta());
			Ev.put("state", ev.getStato());
			Ev.put("stateCode", ev.getStateCode());

			LocalDate localDate = (ev.getDate());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedString = localDate.format(formatter);

			Ev.put("localDate", formattedString);
			Ev.put("localTime", ev.getOra());
			Ev.put("genre", ev.getGenere());

			arr.add(Ev);
		}
		
		obj.put("events", arr);
		JSONObject result=test.toJSON(eventi);
		assertEquals (obj, result);
	}

}
