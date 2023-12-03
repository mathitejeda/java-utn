package dominio;

public class Seguro {

	private int id;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private float costoContratacion;
	private float costoAsegurado;

	public Seguro() {
	}
	
	public Seguro(int id, String descripcion, TipoSeguro tipoSeguro, float costoContratacion, float costoMaxAsegurado) {
		this.descripcion = descripcion;
		this.tipoSeguro = tipoSeguro;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoMaxAsegurado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public float getCostoContratacion() {
		return costoContratacion;
	}
	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}
	public float getCostoAsegurado() {
		return costoAsegurado;
	}
	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
	
	@Override
	public String toString() {
		return "Seguros [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipoSeguro + ", costoContratacion="
				+ costoContratacion + ", costoMaxAsegurado=" + costoAsegurado + "]";
	}
}
