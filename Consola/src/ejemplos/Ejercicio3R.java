package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3R {

	public static void main(String args[]) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		//String IP = leerIP();

		// -- Linux / MacOS --

		// Run a shell command
		//processBuilder.command("bash", "-c", "ping -c 1 " + IP);
		processBuilder.command("bash", "-c", "ifconfig");

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
			//System.out.println(buffer);
				
			showInterfaces(buffer.toString());
			showIP(buffer.toString(), "en0");
			showMAC(buffer.toString(), "en0");

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
	
	private static boolean isConnected(String buffer) {
		return buffer.contains("0.0% packet loss");
	}
	
	private static void showInterfaces(String buffer) {
		String[] lines = buffer.toString().split("\\n");
		for(String s: lines){
		    if(s.contains(": flags")) System.out.println(s.substring(0,s.indexOf(':')));
		}
	}
	
	private static void showIP(String buffer, String iface) {
		String[] lines = buffer.toString().split("\\n");
		int found = 0;
		final int IPline = 4;
		for(String s: lines){
		    if(s.contains(iface)) {
		    	found++;
		    }
		    if(found != 0 && found != IPline) {
		    	found++;
		    } else if (found == IPline){
		    	System.out.println(extractIP(s));
		    	found++;
		    } else {
		    	found = 0;
		    }
		}
	}
	
	private static void showMAC(String buffer, String iface) {
		String[] lines = buffer.toString().split("\\n");
		int found = 0;
		final int MACline = 3;
		for(String s: lines){
		    if(s.contains(iface)) {
		    	found++;
		    }
		    if(found != 0 && found != MACline) {
		    	found++;
		    } else if (found == MACline){
		    	System.out.println(extractMAC(s));
		    	found++;
		    } else {
		    	found = 0;
		    }
		}
	}
	
	private static String extractIP(String line) {
		String[] words = line.split(" ");
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
		for(String s: words){
			if(s.matches(PATTERN)) return s;
		}
		return null;
	}
	
	private static String extractMAC(String line) {
		String[] words = line.split(" ");
		String PATTERN = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
		for(String s: words){
			if(s.matches(PATTERN)) return s;
		}
		return null;
	}

}