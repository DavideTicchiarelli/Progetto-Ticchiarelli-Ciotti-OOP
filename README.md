
<h1 align="center"> Progetto-Programmazione-Ad-Oggetti </h1>

<h1 align="center"> Eventi USA TicketMaster </h1>
 

## **Sommario:**

* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Rotte](#rotte)
* [JUnit Test](#junit-test)
* [Documentazione JavaDoc](#documentazione-javadoc)
* [Software Utilizzati](#software-utilizzati)
* [Autori](#autori)

## Introduzione
L'obiettivo dell'applicazione "Eventi USA TicketMaster" è quello di consentire l'analisi degli eventi che avranno luogo negli Stati Uniti, utilizzando le API di ticketmaster,sito per la gestione ed la prenotazione di vari eventi (musicali, artistici, sportivi, ecc...).
In particolare, utilizzando le API fornite da TicketMaster, il programma ,grazie anche all'ausilio di filtri, dovrà fornire gli eventi che si terranno negli USA e dovrà, inoltre, fornire alcune statistiche.
Come anticipato nelle righe precedenti, dal punto di vista delle specifiche tecniche,l'applicazione sviluppata fornisce le seguenti statistiche:
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

## JUnit Test

## Documentazione JavaDoc
Il codice java dell'applicazione "Eventi USA TicketMaster" è interamente documentato attraverso Javadoc.

## Software Utilizzati
* L'IDE [Eclipse](https://www.eclipse.org/downloads/) per la scrittura del codice dell'intero applicativo
* [Postman](https://www.postman.com/downloads/) per il testing delle API, impiegato per gestire le chiamate `GET` delle rotte.
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
