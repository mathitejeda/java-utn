package ejercicio3;

public class Edificio {
	//Atributos
	private double superficieEdificio;
	
	//Constructores
	public Edificio() {
		
	}
	
	public Edificio(double superficie) {
		this.superficieEdificio = superficie;
	}

	//Getters y Setters
	public double getSuperficie() {
		return superficieEdificio;
	}

	public void setSuperficie(double superficie) {
		this.superficieEdificio = superficie;
	}

	@Override
	public String toString() {
		return "Superficie: " + superficieEdificio;
	}
	
	
}
