package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	public boolean insert(Usuario usuario);
	public boolean modificarClave(String usuario, String pass);
	public Usuario ReadUser(String user);
	public ArrayList<Usuario> obtenerUsuarios();
	public List<Usuario> readAll();
}
