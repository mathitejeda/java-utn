package Ejercicio1;

public class Principal {

	public static void main(String[] args) {
		
		Empleado Emp1 = new Empleado();
		Emp1.setNombre("Jose");
		Emp1.setEdad(30);
		System.out.println(Emp1.toString());	
		System.out.println();		

		System.out.println("El pr�ximo ID ser� el " + Empleado.devuelveProximoID());
		
		Empleado Emp2 = new Empleado();
		Emp2.setNombre("Luc�a");
		Emp2.setEdad(21);
		System.out.println(Emp2.toString());	
		System.out.println();	
		
		System.out.println("El pr�ximo ID ser� el " + Empleado.devuelveProximoID());
		
		Empleado Emp3 = new Empleado("Carlos", 49);
		System.out.println(Emp3.toString());	
		System.out.println();
		
		System.out.println("El pr�ximo ID ser� el " + Empleado.devuelveProximoID());
		
		Empleado Emp4 = new Empleado("M�a", 38);
		System.out.println(Emp4.toString());	
		System.out.println();
		
		System.out.println("El pr�ximo ID ser� el " + Empleado.devuelveProximoID());
		
		Empleado Emp5 = new Empleado("Andr�s", 27);
		System.out.println(Emp5.toString());		
		
	}

}
