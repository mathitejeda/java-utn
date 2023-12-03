package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;

public interface UsuarioNegocio {
	public boolean insert(Usuario usuario, String pass2);
	public Usuario ReadUser(String user);
	public ArrayList<Usuario> obtenerUsuarios();
	public boolean modificarClave(String usuario, String pass, String pass2);
	public List<Usuario> readAll();
	public ArrayList<Usuario> obtenerUsuariosDisponibles();
}
