package main;

import models.ContadorConcurrente;
import models.HiloIncrementador;

public class Main {

	public static void main(String[] args) {
		final int N_HILOS = 150;		
		
		//Monitor concurrente
		ContadorConcurrente contadorConc = new ContadorConcurrente();
		
		//Hilos que pelean por incrementar en paralelo
		HiloIncrementador[] hilos = new HiloIncrementador[N_HILOS];
		
		//Hilos creados
		for(int i = 0 ; i < N_HILOS ; i++){
			hilos[i] = new HiloIncrementador(contadorConc, i+1);
		}
		//Lanzando hilos
		for(int i = 0 ; i < N_HILOS ; i++){
			hilos[i].start();
		}
		
	}

}
