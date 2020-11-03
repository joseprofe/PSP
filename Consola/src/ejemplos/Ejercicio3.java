package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String args[]) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		String IP = leerIP();

		// -- Linux / MacOS --

		// Run a shell command
		processBuilder.command("bash", "-c", "ping -c 1 " + IP);

		// Run a shell script
		// processBuilder.command("path/to/hello.sh");

		// -- Windows --

		// Run a command
		// processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

		// Run a bat file
		// processBuilder.command("C:\\Users\\mkyong\\hello.bat");

		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}

			if (checkConectividad(buffer.toString())) {
				System.out.println("Hay conectividad con " + IP);
			} else {
				System.out.println("Se lio chiquita...");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String leerIP() {
		// No cierren nunca el System.in si no quieren cargarse el flujo estandar
		// (teclado)
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la IP a comprobar: ");
		return sc.nextLine();
	}

	private static boolean checkConectividad(String buffer) {
		return buffer.contains("0.0% packet loss");
	}

}