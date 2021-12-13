package it.univpm.ProgettoEsame.stats;

public class MinMaxMedia {
	
	private int min;
	private int max;
	private double media;
	
	public MinMaxMedia() {}
	
	public int[] bubbleSort(int numeroEventi[] ){
		int n=numeroEventi.length;
	    int i, j;

	    for (i=0;i<n-1;i++){

	        for (j=0;j<n-i-1;j++) {
	            if (numeroEventi[j]>numeroEventi[j+1]){
	                int tmp=numeroEventi[j];
	                numeroEventi[j]=numeroEventi[j+1];
	                numeroEventi[j+1]=tmp;
	            }
	        }
	    }
	    return numeroEventi;
	}
	
	public void minEventi(int[]numeroEventi) {
		this.min=numeroEventi[0];	
	}
	
	public void maxEventi(int[]numeroEventi) {
		int max=numeroEventi.length;
		this.max=numeroEventi[max-1];
	}
	
	public void mediaEventi(int[]numeroEventi) {
		int cont=0;
		
		for(int i=0;i<numeroEventi.length;i++) {
			cont+=numeroEventi[i];
			this.media=(double)cont/(double)numeroEventi.length;
		}
		
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	
}
