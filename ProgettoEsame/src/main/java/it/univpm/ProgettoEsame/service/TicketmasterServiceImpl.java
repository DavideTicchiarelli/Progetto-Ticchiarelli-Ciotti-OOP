package it.univpm.ProgettoEsame.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.filters.GenreFilter;
import it.univpm.ProgettoEsame.filters.MinMaxMediaFilter;
import it.univpm.ProgettoEsame.model.BodyEventi;
import it.univpm.ProgettoEsame.model.Evento;

/**
 * Implementazione dell'interfaccia TicketmasterService.
 */
@Service
public class TicketmasterServiceImpl implements TicketmasterService {

	private String apikey="gcbb5hGg46qKGJTo1XeuoIY1AK7AgFiL";
	private String url="https://app.ticketmaster.com/discovery/v2/events.json?countryCode=US&stateCode=";

	/**
	 * Metodo che analizza l'API di ticketmaster e restituisce il JSONObject filtrato per statecode.
	 * 
	 * @param stateCode Statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @return evento JSONObject dell'evento.
	 */
	@Override
	public JSONObject getJSONEventoStato(String stateCode) {

		JSONObject evento=null;

		try {
			URL u = new URL(this.url+stateCode+"&apikey="+apikey);

			URLConnection openConnection=u.openConnection();
			InputStream input=openConnection.getInputStream();

			String dati="";
			String line="";

			try {
				InputStreamReader inReader=new InputStreamReader(input);
				BufferedReader buffer=new BufferedReader(inReader);

				while((line=buffer.readLine())!=null){
					dati+=line;
				}
			}finally {
				input.close();
			}
			evento=(JSONObject) JSONValue.parseWithException(dati);

		}catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return evento;		
	}

	/**
	 * Metodo che, tramite il JSONObject di eventi, costruice il Vettore di tipo Evento.
	 * 
	 * @param stateCode statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @return eventi Vettore di tipo Evento.
	 * @throws EventiException se il vettore di Eventi è vuoto.
	 */
	@Override
	public Vector<Evento> getStatoEvents(String stateCode) throws EventiException{

		JSONObject obj=getJSONEventoStato(stateCode);

		JSONObject embedded1=(JSONObject)obj.get("_embedded");
		JSONArray events=(JSONArray)embedded1.get("events");

		Vector<Evento> eventi=new Vector<Evento>(events.size());

		for(int i=0;i<events.size();i++) {

			Evento ev=new Evento();

			JSONObject currentCont=(JSONObject)events.get(i);

			String nome=(String)currentCont.get("name");
			String url=(String)currentCont.get("url");

			ev.setNome(nome);
			ev.setUrl(url);

			JSONObject dates=(JSONObject)currentCont.get("dates");
			JSONObject start=(JSONObject)dates.get("start");

			LocalDate date=LocalDate.parse((CharSequence) start.get("localDate"));

			ev.setDate(date);
			String localtime=(String)start.get("localTime");
			ev.setOra(localtime);

			JSONArray classifications=(JSONArray)currentCont.get("classifications");
			JSONObject classificationsTemp=(JSONObject)classifications.get(0);
			JSONObject genre=(JSONObject)classificationsTemp.get("genre");
			String nameGenre=(String)genre.get("name");
			ev.setGenere(nameGenre);

			JSONObject embedded2=(JSONObject)currentCont.get("_embedded");
			JSONArray venues=(JSONArray)embedded2.get("venues");

			for(int j=0;j<venues.size();j++) {
				JSONObject venuesTemp=(JSONObject)venues.get(j);
				JSONObject namecity=(JSONObject)venuesTemp.get("city");
				JSONObject state=(JSONObject)venuesTemp.get("state");
				String citta=(String)namecity.get("name");
				String namestate=(String)state.get("name");
				String statecode=(String)state.get("stateCode");
				ev.setCitta(citta);
				ev.setStato(namestate);
				ev.setStateCode(statecode);
			}

			eventi.add(ev);
		}
		if (eventi.isEmpty()) {
			throw new EventiException("Il vettore di eventi è vuoto");
		}
		return eventi;
	}

	/**
	 * Metodo che converte il Vettore di tipo Evento in un JSONObject.
	 * 
	 * @param stato Vettore di tipo Evento.
	 * @return obj JSONObject del vettore.
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public JSONObject toJSON(Vector<Evento> stato) {

		JSONObject obj=new JSONObject();
		JSONArray listaEventi=new JSONArray();

		for(int i=0;i<(stato.size());i++) {

			JSONObject Ev=new JSONObject();

			Ev.put("name", (stato.get(i)).getNome());
			Ev.put("url", (stato.get(i)).getUrl());
			Ev.put("city", (stato.get(i)).getCitta());
			Ev.put("state", (stato.get(i).getStato()));
			Ev.put("stateCode",(stato.get(i).getStateCode()));

			LocalDate localDate = (stato.get(i)).getDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedString = localDate.format(formatter);

			Ev.put("localDate", formattedString);
			Ev.put("localTime", (stato.get(i)).getOra());
			Ev.put("genre", (stato.get(i)).getGenere());

			listaEventi.add(Ev);
		}

		obj.put("events", listaEventi);

		return obj;
	}


	/**
	 * Metodo che legge il body e crea l'oggetto di tipo BodyEventi.
	 * Il body deve essere di questo tipo:
	 * {
	    "stati":[
	       { 
	        "stato1":"AZ"
	        },
	       {
	         "stato2":"NC"
	        }
	    ],
	    "generi":[
	        {
	        "genere1":"Football"
	        },
	        {
	        "genere2":"Basketball"
	        }
	     ],
	    "periodo":
	        {
	        "inizio":"2022-01-01",
	        "fine":"2022-03-01"
	       	}
    	}

	 *@param body Stringa del body in ingresso.
	 *@return eb Oggetto di tipo BodyEventi.
	 */
	public BodyEventi readBody(String body) {
		Vector<String>stati=new Vector<String>();
		Vector<String>generi=new Vector<String>();
		Vector<String>periodo=new Vector<String>();
		BodyEventi eb;
		JSONObject Body;
		try {
			Body= (JSONObject)new JSONParser().parse(body);

			JSONArray jsonstati=(JSONArray)Body.get("stati");

			JSONObject statetmp=(JSONObject)jsonstati.get(0);
			JSONObject statetmp2=(JSONObject)jsonstati.get(1);

			String stato1=(String)statetmp.get("stato1");
			String stato2=(String)statetmp2.get("stato2");

			stati.add(stato1);
			stati.add(stato2);

			JSONArray jsongeneri=(JSONArray)Body.get("generi");

			JSONObject genretemp=(JSONObject)jsongeneri.get(0);
			JSONObject genretemp2=(JSONObject)jsongeneri.get(1);
			String genere1=(String)genretemp.get("genere1");
			String genere2=(String)genretemp2.get("genere2");

			generi.add(genere1);
			generi.add(genere2);


			JSONObject jsonperiodo=(JSONObject)Body.get("periodo");
			String periodo1=(String)jsonperiodo.get("inizio");
			String periodo2=(String)jsonperiodo.get("fine");

			periodo.add(periodo1);
			periodo.add(periodo2);


		} catch (ParseException e) {
			e.printStackTrace();
		}

		return eb=new BodyEventi(stati,generi,periodo);

	}

	/**
	 * Metodo che restituisce il risultato del JSONObject degli eventi filtrati per periodo,genere e stato.
	 * 
	 * @param statecode statecode dello stato di cui si vogliono visualizzare gli eventi.
	 * @param genere Genere per il filtro.
	 * @param inizio Data inizio del periodo personalizzato.
	 * @param fine Data fine del periodo personalizzato. 
	 * @return result JSONObject degli eventi filtrati.
	 * @throws EventiException se il vettore filtrato è vuoto.
	 */
	@Override
	public JSONObject getResultEventi(String statecode,String genere,String inizio,String fine) throws EventiException {

		GenreFilter filtro=new GenreFilter();
		MinMaxMediaFilter filter=new MinMaxMediaFilter();
		Vector<Evento>eventi=new Vector<Evento>();

		eventi=(filtro.Filtrogenere(genere, getStatoEvents(statecode)));

		if(eventi.isEmpty()) {
			throw new EventiException("Il vettore di eventi per lo stato è vuoto");
		}

		JSONObject result=toJSON(filter.filtroperiodo(inizio,fine,eventi));
		return result ;

	}

}
