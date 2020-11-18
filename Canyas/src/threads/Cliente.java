package threads;

import java.util.Random;

import seccioncritica.Camarero;

public class Cliente extends Thread{
	
	private int idCliente;
	private Camarero camarero;
	
	public Cliente(int idCliente, Camarero c) {
		super();
		this.idCliente = idCliente;
		this.camarero = c;
	}
	
	public void consumir() {
		this.camarero.consumir(idCliente);
		//Duerme durante 1-3s
		try {
			Random r = new Random();
			int low = 1000;
			int high = 3000;
			int result = r.nextInt(high-low) + low;
			Thread.sleep(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * El cliente consume constantemente.
	 */
	public void run() {
		while(true) {
			consumir();
		}
	}

}
