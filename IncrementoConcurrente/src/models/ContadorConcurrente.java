package models;

public class ContadorConcurrente {
	private int n;

	public ContadorConcurrente() {
		n = 0;
	}

	public synchronized void incrementar(int idHilo) {
		n++;
		System.out.println("Sumando en el hilo "+idHilo +", n vale ahora: " + n);
	}
}
