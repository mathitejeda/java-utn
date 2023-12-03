package ejercicio3;

import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio3 {
	public static void main(String[] args) {
		ArrayList<Object> edificios = new ArrayList<Object>();
		edificios.add(new Polideportivo(50, "Polideportivo 1"));
		edificios.add(new Polideportivo(50, "Polideportivo 2"));
		edificios.add(new Polideportivo(50, "Polideportivo 3"));
		edificios.add(new EdificiodeOficinas(100, 50));
		edificios.add(new EdificiodeOficinas(200, 100));

		ListIterator<Object> it = edificios.listIterator();
		while (it.hasNext()) {
			Object edificio = it.next();
			System.out.println(edificio.toString());
		}
	}
}
