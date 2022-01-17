
<h1 align="center"> Progetto-Programmazione-Ad-Oggetti </h1>

<h1 align="center"> Eventi USA TicketMaster </h1>
 

## **Sommario**

* [Introduzione](#introduzione)
  * [Statistiche e Filtri](#statistiche-e-filtri)
* [Installazione](#installazione)
* [Rotte](#rotte)
* [JUnit Test](#junit-test)
* [Documentazione JavaDoc](#documentazione-javadoc)
* [Software Utilizzati](#software-utilizzati)
* [Autori](#autori)

## Introduzione
"Eventi USA TicketMaster" è un'applicazione che consente di visualizzare l'analisi degli eventi che avranno luogo negli Stati Uniti, utilizzando le APi di ticketmaster, sito per la gestione ed la prenotazione di vari eventi (musicali, artistici, sportivi, ecc...).
In particolare il programma, grazie anche all'ausilio di filtri, fornisce la lista dgli eventi che si terranno negli USA e le relative statistiche.

### Statistiche e Filtri
L'applicazione sviluppata fornisce le seguenti statistiche:
* numero totale di eventi per ogni Stato.
* numero totale di eventi raggruppati per genere per ogni Stato.
* numero minimo/massimo/media degli eventi mensili per ogni Stato.

e offre i seguenti filtri:
* filtro eventi in USA in base a uno o più Stati.
* filtro eventi in USA in base a di uno o più generi.
* filtro in base a un periodo personalizzato per calcolare valori di minimo/massimo/media eventi (invece di calcolarli mensilmente).

## Installazione
"Eventi USA TicketMaster" può essere scaricato seguendo i seguenti passaggi:
* creare una cartella sul desktop 
* da terminale entrare nella cartella e digitare il seguente comando (git bash) :  
`git clone https://github.com/DavideTicchiarelli/Progetto-esame-Ticchiarelli-Ciotti-OOP`

## Rotte

Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo
```
localhost:8080
```

### 1.   /getEvento?stateCode=' '

La rotta restituisce un JSONObject contenente tutti gli eventi e le relative informazioni di un determinato stato passando come parametro il relativo stateCode. 

![WhatsApp Image 2022-01-11 at 16 54 19](https://user-images.githubusercontent.com/95363767/148976420-e727fea2-392d-4d47-a5b3-9d2cf9284135.jpeg)

### 2.   /numEventi?stateCode=' '

La rotta restituisce un JSONObject contenente il numero totale eventi di un determinato stato, il parametro da passare è lo stateCode. 

<img width="450" alt="Schermata 2022-01-11 alle 17 08 39" src="https://user-images.githubusercontent.com/93603411/148987410-294856ba-6a61-4b9f-b0f1-8ec5d0de2c60.png">

### 3.   /numGenere?stateCode=' '&genre=' '

La rotta restituisce un JSONObject contenente il numero totale di eventi di un determinato stato filtrati per un determinato genere, i parametri da passare sono lo stateCode 
e il genere.

<img width="852" alt="Schermata 2022-01-11 alle 18 51 38" src="https://user-images.githubusercontent.com/93603411/148995986-b5337306-0817-4141-bc76-dabfc032e283.png">

### 4.   /eventiMensili?stateCode=' '

La rotta restituisce un JSONObject contenente il numero minimo/massimo/medio di eventi di un determinato stato, il parametro da passare è lo stateCode.

<img width="486" alt="Schermata 2022-01-11 alle 17 20 31" src="https://user-images.githubusercontent.com/93603411/148988080-8aa158b5-0112-4755-814d-b95432ada883.png">

### 5.   /Eventi?

Questa rotta è di tipo POST restituisce un JSONObject contenente le statistiche degli eventi filtrati per uno o più stati, uno o più generi e in base ad un periodo personalizzato.
Richiede un body di questo tipo:

```json
{
    "stati":[
       { 
        "stato1":"CA"
        },
       {
         "stato2":"NC"
        }
    ],
    "generi":[
        {
        "genere1":"Rock"
        },
        {
        "genere2":"Hockey"
        }
     ],
    "periodo":
        {
        "inizio":"2022-01-01",
        "fine":"2022-10-01"
        }
    }
```
- **stati** è il JSONArray che contiene gli stateCode degli stati di cui si vuole fare statistica.Gli stateCode ammissibili si trovano nel file "statecodes.txt" all'interno della cartella resources.
- **generi** è il JSONArray che contiene i generi degli eventi relativi al rispettivo stato di cui si vuole fare statistica.I generi ammissibili si trovano nel file "generi.txt" all'interno della cartella resources.
- **periodo** è il JSONObject che contiene l'inizio e la fine del periodo personalizzato degli eventi di cui si vuole fare la statistica. Inizio e fine devono essere forniti nel formato "yyyy-mm-dd".

La risposta della rotta è la seguente:

```json
{
    "Eventi per il genere Hockey": [
        {
            "in North Carolina": 1
        },
        {
            "in California": 2
        }
    ],
    "Eventi in California": {
        "Totale": 4
    },
    "Statistiche periodiche di eventi in California": {
        "numero medio di eventi": 2.0,
        "numero massimo di eventi": 8,
        "numero minimo di eventi": 0
    },
    "Eventi in North Carolina": {
        "Totale": 4
    },
    "Statistiche periodiche di eventi in North Carolina": {
        "numero medio di eventi": 2.0,
        "numero massimo di eventi": 12,
        "numero minimo di eventi": 0
    },
    "eventi in North Carolina": {
        "events": [
            {
                "localTime": "20:00:00",
                "city": "Charlotte",
                "name": "Eagles",
                "genre": "Rock",
                "stateCode": "NC",
                "state": "North Carolina",
                "localDate": "2022-02-21",
                "url": "https://www.ticketmaster.com/eagles-charlotte-north-carolina-02-21-2022/event/2D005B768B2A1822"
            },
            {
                "localTime": "19:00:00",
                "city": "Raleigh",
                "name": "Imagine Dragons: Mercury World Tour",
                "genre": "Rock",
                "stateCode": "NC",
                "state": "North Carolina",
                "localDate": "2022-02-10",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-raleigh-north-carolina-02-10-2022/event/2D005B1FFA456D3F"
            },
            {
                "localTime": "20:00:00",
                "city": "Raleigh",
                "name": "Eagles",
                "genre": "Rock",
                "stateCode": "NC",
                "state": "North Carolina",
                "localDate": "2022-03-02",
                "url": "https://www.ticketmaster.com/eagles-raleigh-north-carolina-03-02-2022/event/2D005B77926921AA"
            },
            {
                "localTime": "19:00:00",
                "city": "Raleigh",
                "name": "Carolina Hurricanes vs. Pittsburgh Penguins",
                "genre": "Hockey",
                "stateCode": "NC",
                "state": "North Carolina",
                "localDate": "2022-03-04",
                "url": "https://www.ticketmaster.com/carolina-hurricanes-vs-pittsburgh-penguins-raleigh-north-carolina-03-04-2022/event/2D005AF7C6236968"
            }
        ]
    },
    "Eventi per il genere Rock": [
        {
            "in California": 2
        },
        {
            "in North Carolina": 3
        }
    ],
    "eventi in California": {
        "events": [
            {
                "localTime": "19:00:00",
                "city": "Inglewood",
                "name": "iHeartRadio ALTer EGO Presented by Capital One",
                "genre": "Rock",
                "stateCode": "CA",
                "state": "California",
                "localDate": "2022-01-15",
                "url": "https://www.ticketmaster.com/iheartradio-alter-ego-presented-by-capital-inglewood-california-01-15-2022/event/09005B4712E4601E"
            },
            {
                "localTime": "19:00:00",
                "city": "Los Angeles",
                "name": "Imagine Dragons: Mercury World Tour",
                "genre": "Rock",
                "stateCode": "CA",
                "state": "California",
                "localDate": "2022-03-12",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-los-angeles-california-03-12-2022/event/2C005B1FEC0B0D99"
            },
            {
                "localTime": "19:30:00",
                "city": "Los Angeles",
                "name": "Los Angeles Kings vs. Boston Bruins",
                "genre": "Hockey",
                "stateCode": "CA",
                "state": "California",
                "localDate": "2022-02-28",
                "url": "https://www.ticketmaster.com/event/Z7r9jZ1AdF4FA"
            },
            {
                "localTime": "19:00:00",
                "city": "Los Angeles",
                "name": "Los Angeles Kings vs. Chicago Blackhawks",
                "genre": "Hockey",
                "stateCode": "CA",
                "state": "California",
                "localDate": "2022-03-24",
                "url": "https://www.ticketmaster.com/event/Z7r9jZ1AdF4Fa"
            }
        ]
    }
}
```

## JUnit Test
All'interno del progetto è presente anche un package di test contente le seguenti classi:

* [toJSONtest](https://github.com/DavideTicchiarelli/Progetto-esame-Ticchiarelli-Ciotti-OOP/blob/master/ProgettoEsame/src/test/java/it/univpm/ProgettoEsame/toJSONtest.java), un test del metodo toJSON contenuto nella classe TicketmasterServiceImpl che verifica se l'oggetto viene convertito correttamente in JSONObject.

* [GenreEventiTest](https://github.com/DavideTicchiarelli/Progetto-esame-Ticchiarelli-Ciotti-OOP/blob/master/ProgettoEsame/src/test/java/it/univpm/ProgettoEsame/GenreEventiTest.java), un test del metodo GenreEventi nella classe GenreStats che verifica se il calcolo del numero totale di eventi per un determinato genere è corretto.

* [MinMaxMediaStatsTest](https://github.com/DavideTicchiarelli/Progetto-esame-Ticchiarelli-Ciotti-OOP/blob/master/ProgettoEsame/src/test/java/it/univpm/ProgettoEsame/MinMaxMediaStatsTest.java), un test dei tre metodi contenuti nella classe MinMaxMedia che verifica se il calcolo del minimo,massimo e medio di un vettore è corretto.

## Documentazione JavaDoc
Il codice java dell'applicazione "Eventi USA TicketMaster" è interamente documentato attraverso [Javadoc](https://github.com/DavideTicchiarelli/Progetto-esame-Ticchiarelli-Ciotti-OOP/tree/master/ProgettoEsame/doc).

## Software Utilizzati
* L'IDE [Eclipse](https://www.eclipse.org/downloads/) per la scrittura del codice dell'intero applicativo
* [Postman](https://www.postman.com/downloads/) per il testing delle API, impiegato per gestire le chiamate `GET` e `POST` delle rotte.
* [GitHub](https://github.com/), per il versioning del codice dell'interfaccia. 
* [Git Bash](https://git-scm.com/downloads), per eseguire il versioning del codice tramite terminale direttamente dalla cartella locale del progetto.
* Il framework [JUnit 5](https://junit.org/junit5/), per lo Unit Testing dei metodi dell'applicativo
* L'applicativo [Javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html), incluso nel pacchetto [JDK](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2020-12/R/eclipse-java-2020-12-R-win32-x86_64.zip), utilizzato per la generazione automatica della documentazione del codice sorgente scritto in Java
* [Discord](https://discord.com/download), per videochiamate, confronti e scambi di idee.
* [Spring Inizializr](https://start.spring.io/), per la generazione del progetto. 
* Il framework [Spring](https://spring.io/), per l'esecuzione e sviluppo dell'applicazione Java.
* Il progetto [Spring Boot](https://spring.io/projects/spring-boot), che consente di avviare l'applicazione attraverso un metodo main che, a sua volta, lancia il web server integrato.
* L'IDE [Spring Tool Suite 4](https://spring.io/tools), per lo sviluppo di applicazioni Spring.
* Il tool [Spring Web](https://spring.io/guides/gs/serving-web-content/), affinchè l'applicazione possa accettare richieste `HTTP` all'indirizzo `localhost:8080`.
* Il tool [Apache Maven](https://maven.apache.org/), per la definizione della struttura del progetto mediante il file `pom.xml`.
* Il web server locale [Apache Tomcat](https://spring.io/blog/2014/03/07/deploying-spring-boot-applications), per la gestione delle richieste `HTTP`.

## Autori
Autori del progetto:
- Davide Ticchiarelli
- Niccolò Ciotti 
