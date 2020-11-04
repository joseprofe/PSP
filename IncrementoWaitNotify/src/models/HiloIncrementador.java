package models;

public class HiloIncrementador extends Thread{
	private ContadorConcurrente c;
	private int idHilo;
	
	public HiloIncrementador(ContadorConcurrente c, int idHilo){
		this.c = c;
		this.idHilo = idHilo;
	}
	
	public void run(){
		c.incrementar(idHilo);
	}	
}
