package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import dao.UsuarioDao;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private static final String insert = "INSERT INTO usuarios(idUsuario,pass,esAdmin,estado) VALUES(?, ?, ?, ?)";
	private static final String readUser = "SELECT * from usuarios where idUsuario=?";
	private static final String readAll = "SELECT * from usuarios";

	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean insert(Usuario usuario) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try	{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, usuario.getIdUsuario());
			statement.setString(2, usuario.getPass());
			statement.setBoolean(3, usuario.getAdmin());
			statement.setBoolean(4, true);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}		
		return isInsertExitoso; 			
	}

	@Override
	public Usuario ReadUser(String user) {

		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		Usuario userBuscado = new Usuario();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readUser);
			statement.setString(1, user);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				userBuscado = getUsuario(resultSet);
			}else {
				userBuscado = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userBuscado;
	}
		
	private Usuario getUsuario(ResultSet resultSet) throws SQLException {
		String idUsuario = resultSet.getString("idUsuario");

		String clave = resultSet.getString("pass");
		Boolean esAdmin = resultSet.getBoolean("esAdmin");
		Boolean estado = resultSet.getBoolean("estado");
		
		Usuario usuario = new Usuario(idUsuario, clave, esAdmin, estado);
		return usuario;
	}	

	

@Override
	public ArrayList<Usuario> obtenerUsuarios() {

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()){	
				Usuario usuarios = new Usuario();
				usuarios.setIdUsuario(resultSet.getString("idUsuario"));
				usuarios.setPass(resultSet.getString("pass"));
				usuarios.setAdmin(resultSet.getBoolean("esAdmin"));
				usuarios.setEstado(resultSet.getBoolean("estado"));
				lista.add(usuarios);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (conexion != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return lista;
		
	}

@Override
public boolean modificarClave(String usuario, String pass) {

	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isUpdateExitoso = false;
	try {

		statement = conexion.prepareStatement("update usuarios SET  pass= '" + pass + "' where idUsuario = '" + usuario + "'");
		if (statement.executeUpdate() > 0) {
			conexion.commit();
			isUpdateExitoso = true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		try {
			conexion.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	return isUpdateExitoso;
}	

	
}

