package ejercicio3;

public class Polideportivo extends Edificio implements IInsatalacionDeportiva {
	
	public String nombre;
		
	public Polideportivo() {

	}

	public Polideportivo(double superficie, String nombre) {
		super(superficie);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return super.toString() + " Nombre: " + nombre;
	}

	@Override
	public int getTipoDeInstalacion() {		
		return 12;
	}
}
