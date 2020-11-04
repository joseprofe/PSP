package models;

public class HiloDecrementador extends Thread{
	private ContadorConcurrente c;
	private int idHilo;
	
	public HiloDecrementador(ContadorConcurrente c, int idHilo){
		this.c = c;
		this.idHilo = idHilo;
	}
	
	public void run(){
		try {
			c.decrementar(idHilo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
