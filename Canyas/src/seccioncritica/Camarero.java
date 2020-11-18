package seccioncritica;

public class Camarero {
	private final int N_CLIENTES = 3;
	private int[] canyasConsumidas;

	public Camarero() {
		this.canyasConsumidas = new int[N_CLIENTES];
	}

	public int[] getCanyasConsumidas() {
		return this.canyasConsumidas;
	}

	public synchronized void consumir(int idCliente) {
		System.out.println("El cliente " + idCliente + " tiene sed!");
		while(!puedeConsumir(idCliente)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.canyasConsumidas[idCliente]++;
		printConsumiciones();
		notify();
	}

	private boolean puedeConsumir(int idCliente) {
		int consumiciones = this.canyasConsumidas[idCliente];
		for (int i = 0; i < this.canyasConsumidas.length; i++) {
			if(i != idCliente) {
				if(Math.abs(this.canyasConsumidas[i] - (consumiciones+1)) > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void printConsumiciones() {
		System.out.print("[ ");
		for (int i = 0; i < canyasConsumidas.length; i++) {
			System.out.print(canyasConsumidas[i]+ " ");
		}
		System.out.print("]\n");
	}
}
