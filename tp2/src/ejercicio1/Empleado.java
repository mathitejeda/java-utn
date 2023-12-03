package ejercicio1;

public class Empleado implements Comparable<Empleado>{
		
		//Atributos 
		private final int id;
		private String nombre;
		private int edad;
		private static int cont=1000;
		
		// Constructores
		public Empleado() {
			this.id = cont;
			this.nombre = "sin nombre";
			this.edad = 99;
			cont ++;
		}
		
		public Empleado(String nombre, int edad) {
			this.id = cont;
			this.nombre = nombre;
			this.edad = edad;
			cont ++;
		}
		
		//Getters - Setters 
		public int getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getEdad() {
			return edad;
		}
		public void setEdad(int edad) {
			this.edad = edad;
		}
		
		//Métodos
		static int devuelveProximoID() {
			return cont;
		}

		@Override
		public String toString() {
			return "ID: "+ id + ", Nombre: " + nombre + ", Edad: " + edad + ", "; 
		}

		@Override
		public int compareTo(Empleado o) {
			//ORDENAMIENTO por DNI de > a <
			if(o.id == this.id)
				return 0;
			
			if (o.id<this.id)
			{
				return 1;
			}		
			return -1;
		}
		
}
