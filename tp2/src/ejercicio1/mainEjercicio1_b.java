package ejercicio1;

import java.util.Iterator;
import java.util.TreeSet;

public class mainEjercicio1_b {

	public static void main(String[] args) {
		
		//Instancias de los 5 profesores
		Profesor p1 = new Profesor("Sonia", 50, "Profesor a cargo", 25);
		Profesor p2 = new Profesor("Oscar", 40, "Ayudante", 15);
		Profesor p3 = new Profesor("Valentina", 25, "Add-honorem", 5);
		Profesor p4 = new Profesor("Julio", 32, "Profesor a cargo", 10);
		Profesor p5 = new Profesor("Carla", 35, "Ayudante", 15);
		
		//Creación y carga de Array
		TreeSet<Profesor> listaProfesor = new TreeSet<Profesor>();	
		listaProfesor.add(p1);
		listaProfesor.add(p2);
		listaProfesor.add(p3);
		listaProfesor.add(p4);
		listaProfesor.add(p5);
		
		//Listado ordenado por ID
		Iterator<Profesor> it = listaProfesor.iterator();
		while(it.hasNext())
		{
			Profesor p = (Profesor) it.next();
			System.out.println(p.toString());
		}
		
	}

}
