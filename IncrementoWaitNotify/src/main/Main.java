package main;

import models.ContadorConcurrente;
import models.HiloDecrementador;
import models.HiloIncrementador;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int N_HILOS = 10;

		// Monitor concurrente
		ContadorConcurrente contadorConc = new ContadorConcurrente();

		// Hilos que pelean por incrementar en paralelo
		HiloIncrementador[] hilosInc = new HiloIncrementador[N_HILOS];
		HiloDecrementador[] hilosDec = new HiloDecrementador[N_HILOS];

		// Hilos creados
		for (int i = 0; i < N_HILOS; i++) {
			hilosInc[i] = new HiloIncrementador(contadorConc, 1);
			hilosDec[i] = new HiloDecrementador(contadorConc, 1);
		}

		// Lanzando hilos
		for (int i = 0; i < N_HILOS; i++) {
			hilosDec[i].start();
			hilosInc[i].start();
		}

		// Esperando a que los hilos acaben
		for (int i = 0; i < N_HILOS; i++) {
			hilosInc[i].join();
			hilosDec[i].join();
		}

		System.out.println("N vale " + contadorConc.getN());

	}

}
