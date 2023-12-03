package entidad;

public class Persona {
	private String dni;
	private String nombre;
	private String apellido;
	
	
	//Constructor sin parametros
	public Persona() {
		
	}
	//Constructor por parametros
	public Persona(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	//Setter y Getters
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
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
	@Override
	public String toString() {
		return nombre + " " + apellido + " " + dni;
	}
	
	
}
