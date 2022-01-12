package it.univpm.ProgettoEsame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.stats.GenreStats;

/**
 * Classe che testa il funzionamento del metodo GenreEventi (nella classe GenreStats).
 *
 *
 */
class GenreEventiTest {
	
	Evento ev,ev2;
	Vector<Evento>eventiFiltrati;
	GenreStats test;
	
	/**
	 * Inizializza i componenti necessari per il test.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		eventiFiltrati=new Vector<Evento>();
		ev=new Evento();
		ev2=new Evento();
		test=new GenreStats();
	}

	/**
	 * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test che verifica il calcolo del numero totale di eveti per un determinato genere.
	 * @throws EventiException
	 */
	@SuppressWarnings("unchecked")
	@Test
	void test() throws EventiException {
		
		JSONObject obj=new JSONObject();
		
		String tempDate="2022-01-15";
		LocalDate date= LocalDate.parse(tempDate);

			ev.setNome("iHeartRadio ALTer EGO Presented by Capital One");
			ev.setUrl("https://www.ticketmaster.com/iheartradio-alter-ego-presented-by-capital-inglewood-california-01-15-2022/event/09005B4712E4601E");
			ev.setCitta("Inglewood");
			ev.setStato("California");
			ev.setStateCode("CA");
			ev.setDate(date);
			ev.setOra("19:00:00");
			ev.setGenere("Rock");
		
			eventiFiltrati.add(ev);	
			
			String tempDate2="2022-03-12";
			LocalDate date2= LocalDate.parse(tempDate2);
			
			ev2.setNome("Imagine Dragons: Mercury World Tour");
			ev2.setUrl("https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-los-angeles-california-03-12-2022/event/2C005B1FEC0B0D99");
			ev2.setCitta("Los Angeles");
			ev2.setStato("California");
			ev2.setStateCode("CA");
			ev2.setDate(date2);
			ev2.setOra("19:00:00");
			ev2.setGenere("Rock");
		
			eventiFiltrati.add(ev2);
	
		obj.put("in "+ev.getStato(), 2);
		JSONObject result=new JSONObject();
		result=test.GenreEventi(eventiFiltrati, ev.getGenere());
	
		assertEquals(obj, result);
	}

}
