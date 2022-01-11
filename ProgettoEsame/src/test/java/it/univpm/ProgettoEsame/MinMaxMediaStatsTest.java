package it.univpm.ProgettoEsame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.univpm.ProgettoEsame.stats.MinMaxMedia;


class MinMaxMediaStatsTest {
    
	int [] v = {1,9,5,4,7,4,2,6,7,0,4,10} ; 
	int [] risultato = {0,1,2,4,4,4,5,6,7,7,9,10};
	MinMaxMedia test;
	
	@BeforeEach
	void setUp() throws Exception {
		test=new MinMaxMedia();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSortSelected() {
		
		assertArrayEquals (risultato,test.sortSelectedEvents(v));
		
	}
	
	@Test
	void testMinEventi() {
		
		assertEquals (risultato[0],test.minEventi(v));
		
	}
	
	@Test
	void testMaxEventi() {
		
		assertEquals (risultato[risultato.length-1],test.maxEventi(v));
		
	}
	
	@Test
	void testMediaEventi() {
		
		int cont=0;
		double media=0;
		
		for(int i=0;i<risultato.length;i++) {
			cont+=risultato[i];
			media=(double)cont/(double)risultato.length;
		}
		
		assertEquals (media,test.mediaEventi(v));
		
	}
	
	
	
	

} 
