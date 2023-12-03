package ejercicio1;

public class Persona implements Comparable<Persona>{

	private String nombre;
	private String apellido;
	private String dni;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


	public static boolean verificarDniInvalido(String DNI) throws DniInvalido
	{
		boolean auxDni = false;
		
        for (int i = 0; i < DNI.length(); i++)
        {
            char c = DNI.charAt(i);
            //valida que cada caracter este entre el 0 y 9
            if (c >= '0' && c <= '9') {
            	auxDni = true;
            } else {
            	//si encuentra alguna letra en medio de la cadena, genera la excepci�n.
            	throw new DniInvalido();
            }
        }
			
	return auxDni;
	}
	
	public static boolean esDniValido(String DNI) {
		boolean esValido = false;
		
        for (int i = 0; i < DNI.length(); i++) {
            char c = DNI.charAt(i);
     
            if (c >= '0' && c <= '9') {
            	esValido = true;
            } else {
            	return false;
            }
        }			
        return esValido;
	}
	
	public static Persona Cadena_a_Persona(String Datos[]) {
		Persona persona = new Persona();
		try {
			persona.setNombre(Datos[0]);
			persona.setApellido(Datos[1]);
			persona.setDni(Datos[2]);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}
	
	public String Persona_a_Cadena () {
		String cadena = null;
		try {
			cadena = this.getNombre() + "-" + this.getApellido() + "-" + this.getDni() + "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadena;
	}

	@Override
	public int compareTo(Persona p) {
		
		return this.getApellido().compareTo(p.getApellido());
	}	
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + "]\n";
	}
}
