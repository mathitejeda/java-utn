package TP5;

public class Categoria {
	
	private String nombre;
	

	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	public Categoria() {
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
