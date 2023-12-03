package ejercicio2;

public class ProductoRefrigerado extends Producto {

	private int codOrganismo;

	public ProductoRefrigerado(){
		super();
	}
	
	
	public ProductoRefrigerado(String fechaCaducidad, String lote, int codOrganismo) {
		super(fechaCaducidad, lote);
		this.codOrganismo = codOrganismo;
	}
	
	
	public int getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(int codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	@Override
	public String toString() {
		return super.toString() + " | " + "Codigo de organismo: " + codOrganismo;
	}
	
	
}
