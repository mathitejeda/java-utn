package ejercicio2;

public class ProductoFresco extends Producto {
	
	private String fechaEnvasado;
	private String paisOrigen;
	
	//Constructores
	public ProductoFresco() {
		super();
	}
	
	public ProductoFresco(String fechaCaducidad, String lote, String fechaEnvasado, String paisOrigen) {
		super(fechaCaducidad, lote);
		this.fechaEnvasado = fechaEnvasado;
		this.paisOrigen = paisOrigen;
	}

	//Setters y Getters
	public String getFechaEnvasado() {
		return fechaEnvasado;
	}

	public void setFechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	@Override
	public String toString() {
		return super.toString()+ "ProductoFresco [fechaEnvasado=" + fechaEnvasado + ", paisOrigen=" + paisOrigen + "]";
	}		
}
