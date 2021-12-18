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

import ch.qos.logback.core.joran.event.BodyEvent;
import it.univpm.ProgettoEsame.model.BodyEventi;
import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.model.Stato;

@Service
public class TicketmasterServiceImpl implements TicketmasterService {

	private String apikey="gcbb5hGg46qKGJTo1XeuoIY1AK7AgFiL";
	private String url="https://app.ticketmaster.com/discovery/v2/events.json?stateCode=";

	@Override
	public JSONObject getJSONEvento(String stateCode) {
		
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

	
	@Override
	public Stato getStatoAPI(String stateCode) {

		JSONObject obj=getJSONEvento(stateCode);	
		Stato state=new Stato(stateCode);

		JSONObject embedded1=(JSONObject)obj.get("_embedded");
		JSONArray events=(JSONArray)embedded1.get("events");
		JSONObject evtemp=(JSONObject)events.get(0);
		JSONObject embedded2=(JSONObject)evtemp.get("_embedded");
		JSONArray venues=(JSONArray)embedded2.get("venues");
		JSONObject venuesTemp=(JSONObject)venues.get(0);
		JSONObject st=(JSONObject)venuesTemp.get("state");
		String name=(String) st.get("name");
		String statecode=(String) st.get("stateCode");

		state.setNomeStato(name);
		state.setStateCode(statecode);

		return state;
	}

	@Override
	public Vector<Evento> getStatoEvents(String stateCode) {

		JSONObject obj=getJSONEvento(stateCode);
//		Stato st=new Stato(stateCode);
//		st=getStatoAPI(stateCode);

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
//		st.setEvento(eventi);
		return eventi;
	}


	@SuppressWarnings("unchecked")
	@Override 
	public JSONObject toJSON(Vector<Evento> stato) {

		JSONObject obj=new JSONObject();

//		obj.put("name", stato.getNomeStato());
//		obj.put("stateCode", stato.getStateCode());

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
	/*
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
    "periodo":[
        {
        "inizio":"2022-01-01"
        },
        {
        "fine":"2022-03-01"
        }
    ]
    }
	 */
	public BodyEventi readBody(String body) {
		Vector<String>stati=new Vector<String>();
		Vector<String>generi=new Vector<String>();
		Vector<String>periodo=new Vector<String>();
	 	BodyEventi eb;
	 	JSONObject Body;
		try {
			Body= (JSONObject)new JSONParser().parse(body);
//			JSONObject obj=(JSONObject)new JSONObject();
			

			JSONArray jsonstati=(JSONArray)Body.get("stati");
			
			for(int j=0;j<jsonstati.size();j++) {
				JSONObject statetmp=(JSONObject)jsonstati.get(j);
				String stato1=(String)statetmp.get("stato1");
				String stato2=(String)statetmp.get("stato2");
				
				stati.add(j,stato1);
				stati.add(j,stato2);
			}

			JSONArray jsongeneri=(JSONArray)Body.get("generi");
				for(int j=0;j<generi.size();j++) {
					JSONObject genretemp=(JSONObject)jsongeneri.get(j);
					String genere1=(String)genretemp.get("genere1");
					String genere2=(String)genretemp.get("genere2");
					
					generi.add(j,genere1);
					generi.add(j,genere2);
					
			}
				
				JSONArray jsonperiodo=(JSONArray)Body.get("generi");
				for(int j=0;j<periodo.size();j++) {
					JSONObject periodotemp=(JSONObject)jsonperiodo.get(j);
					String periodo1=(String)periodotemp.get("genere1");
					String periodo2=(String)periodotemp.get("genere2");
					
					periodo.add(j,periodo1);
					periodo.add(j,periodo2);
					
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return eb=new BodyEventi(stati,generi,periodo);
		
		
	}
	
}
