package models;

public class ContadorConcurrente {
	private int n;

	public ContadorConcurrente() {
		this.n = 0;
	}

	public synchronized void incrementar(int incremento) {
		int aux = n;
		n += incremento;
		System.out.println(aux + " + "+incremento +" = " + n);
		
		//Siempre que incremento notifico a algún parado para que despierte y decremente.
		notify();
	}
	
	public synchronized void decrementar(int decremento) throws InterruptedException {
		//Mientras no puedas decrementar, ESPERA a que te avisen.
		while((n - decremento) < 0){
			wait();
		}
		int aux = n;
		n -= decremento;
		System.out.println(aux + " - "+decremento +" = " + n);
	}
	
	public int getN() {
		return n;
	}
}
