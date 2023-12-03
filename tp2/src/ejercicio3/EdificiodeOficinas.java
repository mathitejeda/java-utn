package ejercicio3;

public class EdificiodeOficinas extends Edificio {
	private int cantidadOficinas;

	public EdificiodeOficinas() {
		
	}
	
	public EdificiodeOficinas(double superficie, int cantidad) {
		super(superficie);
		this.cantidadOficinas = cantidad;
	}
	
	
	public int getCantidadOficinas() {
		return cantidadOficinas;
	}

	public void setCantidadOficionas(int cantidadOficionas) {
		this.cantidadOficinas = cantidadOficionas;
	}

	@Override
	public String toString() {
		return super.toString() + " Cantidad de oficinas:" + cantidadOficinas;
	}
}
