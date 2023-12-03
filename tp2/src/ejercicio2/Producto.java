package ejercicio2;

public class Producto {
	//Atributos
	private String fechaCaducidad;
	private String lote;
	
	//Constructor
	public Producto() {

	}
	
	public Producto(String fechaCaducidad, String lote) {
		this.fechaCaducidad = fechaCaducidad;
		this.lote = lote;
	}


	//Setters y Getters
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	//Metodos sobrecargados
	@Override
	public String toString() {
		return "Lote: " + lote + " | " + "Caduca: " + fechaCaducidad;
	}
	
	
}
