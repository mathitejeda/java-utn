package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

public class Principal {

	public static void main(String[] args) {
		Archivo archivoPersonas = new Archivo();
		archivoPersonas.setRuta("src\\Personas.txt");
		
		Archivo archivoResultados = new Archivo();
		archivoResultados.setRuta("src\\Resultados.txt");
		
		if(!archivoPersonas.existe()) {			
			archivoPersonas.creaArchivo();
		}
		
		if(!archivoResultados.existe()) {			
			archivoResultados.creaArchivo();
		}
		
		System.out.println("LEO ARCHIVO Personas.txt: ");
		archivoPersonas.leerArchivo();
		
		System.out.println("PASO ARCHIVO A Treeset:");
		TreeSet<Persona> listaPersonas = pasarArchivoATreeset(archivoPersonas);
		
		System.out.println("LEO EL Treeset:");
		System.out.println(listaPersonas);
		
		System.out.println("PASO Treeset A Resultados.txt:");
		pasarTreesetAArchivo(listaPersonas, archivoResultados);
		
		//System.out.println("LEO ARCHIVO Resultados.txt: ");
		//archivoResultados.leerArchivo();
	}
	
	private static TreeSet<Persona> pasarArchivoATreeset(Archivo archivo) {
		TreeSet<Persona> listaPersonas = new TreeSet<Persona>();
		
		FileReader entrada;
		try {
			entrada = new FileReader(archivo.getRuta());
			BufferedReader miBuffer = new BufferedReader(entrada);
			
			String linea = "";
			while (linea != null) {
				linea = miBuffer.readLine();
				String datos[] = linea.split("-");
				if(Persona.esDniValido(datos[2])) {
					listaPersonas.add(Persona.Cadena_a_Persona(datos));
				}
			}
			miBuffer.close();
			entrada.close();
		} catch (Exception e) {
			System.out.println("Hubo un problema");
		}
		return listaPersonas;
	}
	
	private static void pasarTreesetAArchivo(TreeSet<Persona> listaPersonas, Archivo archivo) {		
		listaPersonas.forEach(persona -> {
			archivo.escribe_lineas(persona.Persona_a_Cadena());
		});		
	}

}
