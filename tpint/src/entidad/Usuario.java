package entidad;

public class Usuario {
	private String idUsuario;
	private String pass;
	private boolean admin;
	private boolean estado;	
	
	public Usuario() {
		
	}
	
	public Usuario(String idUsuario, String pass, boolean admin, boolean estado) {
		super();
		this.idUsuario = idUsuario;
		this.pass = pass;
		this.admin = admin;
		this.estado = estado;
	}

	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", pass=" + pass + ", admin=" + admin + ", estado=" + estado + "]";
	}
		
}
