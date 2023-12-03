package ejercicio2;

public class ProductoCongelado extends Producto {
	private float tempCongelacionRecomendada;

	// Constructores
	public ProductoCongelado() {
		super();
	}

	public ProductoCongelado(String fechaCaducidad, String lote, float tempCongelacionRecomendada) {
		super(fechaCaducidad, lote);
		this.tempCongelacionRecomendada = tempCongelacionRecomendada;
	}

	// Getters y setters
	public float getTempCongelacionRecomendada() {
		return tempCongelacionRecomendada;
	}

	public void setTempCongelacionRecomendada(float tempCongelacionRecomendada) {
		this.tempCongelacionRecomendada = tempCongelacionRecomendada;
	}

	@Override
	public String toString() {
		return super.toString() + " | " + "Temperatura recomendada: " + tempCongelacionRecomendada;
	}
}
