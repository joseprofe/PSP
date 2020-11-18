package mainapp;

import seccioncritica.Camarero;
import threads.Cliente;

public class MainApp {

	public static void main(String[] args) {
		Camarero c = new Camarero();
		Cliente[] clientes = new Cliente[3];
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(i,c);
		}
		
		for (Cliente cliente : clientes) {
			cliente.start();
		}
	}

}
