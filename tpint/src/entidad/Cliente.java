package entidad;
import java.sql.Date;

public class Cliente {
	private Usuario usuario;
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private String sexo;
	private String nacionalidad;
	private java.sql.Date fechaNacimiento;
	private String direccion;
	private String localidad;
	private String provincia;
	private String email;
	private String telefono;
	private boolean estado;

	public Cliente() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [usuario=" + usuario + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido="
				+ apellido + ", sexo=" + sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento="
				+ fechaNacimiento + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia
				+ ", email=" + email + ", telefono=" + telefono + ", estado=" + estado + "]";
	}	
}
