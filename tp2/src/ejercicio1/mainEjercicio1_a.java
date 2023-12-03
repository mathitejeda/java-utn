package ejercicio1;

import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio1_a {

	public static void main(String[] args) {
		//Instancias de los 5 profesores
		Profesor p1 = new Profesor("Victor", 50, "Profesor a cargo", 25);
		Profesor p2 = new Profesor("Belen", 40, "Ayudante", 15);
		Profesor p3 = new Profesor("Roberto", 25, "Add-honorem", 5);
		Profesor p4 = new Profesor("Estela", 32, "Profesor a cargo", 10);
		Profesor p5 = new Profesor("Victoria", 35, "Ayudante", 15);
				
		//Creación y carga de Array
		ArrayList<Profesor> ListaProfesor = new ArrayList<Profesor>(5);
		ListaProfesor.add(p1);
		ListaProfesor.add(p2);
		ListaProfesor.add(p3);
		ListaProfesor.add(p4);
		ListaProfesor.add(p5);
			
		//Listado ordenado por ID
		ListIterator<Profesor> it = ListaProfesor.listIterator();
		while (it.hasNext()) {
			Profesor profesor = it.next();
			System.out.println(profesor.toString());
		}
		
		// Ejercicio 7
		Profesor ej7a = new Profesor("bruce wayne", 45, "Profesor", 20);
		Profesor ej7b = new Profesor("bruce wayne", 45, "Profesor", 20);

		if(ej7a.equals(ej7b)) {
			System.out.println("Es el mismo profesor");
		}
	}

}
