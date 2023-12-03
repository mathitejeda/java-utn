package TP5;

public class Pelicula {
	private final int ID;
	private String nombre;
	private Categoria genero;
	private static int cont=0;
	
	public Pelicula(String nombre, Categoria genero) {
		cont ++;
		this.ID = cont;
		this.nombre = nombre;
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return "ID: " + ID + " - Nombre: " + nombre + " - Género: " + genero;
	}

	public Pelicula()
	{
		cont ++;
		this.ID = cont;
	}	
	
	
	public int getID() {
		return this.ID;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getGenero() {
		return genero;
	}

	public void setGenero(Categoria genero) {
		this.genero = genero;
	}
	
}
