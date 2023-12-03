package dominio;

public class TipoSeguro {

	private int idTipo;
	private String descripcion;
	
	public TipoSeguro(int idTipo, String descripcion) {

		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	
	public TipoSeguro() { }

	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "tipoSeguros [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}
	
	
}
