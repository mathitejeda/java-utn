package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClienteDao;
import dao.UsuarioDao;
import entidad.Cliente;
import entidad.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

public class ClienteDaoImpl implements ClienteDao {

	private static final String insert = "INSERT INTO clientes(idUsuario ,dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correoElectronico, telefono, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	private static final String readall = "SELECT * FROM clientes";
	private static final String readCliente = "SELECT * from clientes where idUsuario=?";

	@Override
	public boolean agregarCliente(Cliente cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, cliente.getUsuario().getIdUsuario());
			statement.setString(2, cliente.getDni());
			statement.setString(3, cliente.getCuil());
			statement.setString(4, cliente.getNombre());
			statement.setString(5, cliente.getApellido());
			statement.setString(6, cliente.getSexo());
			statement.setString(7, cliente.getNacionalidad());
			statement.setDate(8, cliente.getFechaNacimiento());
			statement.setString(9, cliente.getDireccion());
			statement.setString(10, cliente.getLocalidad());
			statement.setString(11, cliente.getProvincia());
			statement.setString(12, cliente.getEmail());
			statement.setString(13, cliente.getTelefono());
			statement.setBoolean(14, true);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
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
	public ArrayList<Cliente> listarClientes() {	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		PreparedStatement statement;
		ResultSet rs;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			rs = statement.executeQuery();
			while (rs.next()) {
				lista.add(getCliente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private Cliente getCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		UsuarioNegocio negocioUsu = new UsuarioNegocioImpl();
		Usuario usuario = negocioUsu.ReadUser(rs.getString("idUsuario"));
		cliente.setUsuario(usuario);
		cliente.setDni(rs.getString("dni"));
		cliente.setCuil(rs.getString("cuil"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setApellido(rs.getString("apellido"));
		cliente.setSexo(rs.getString("sexo"));
		cliente.setNacionalidad(rs.getString("nacionalidad"));
		cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		cliente.setDireccion(rs.getString("direccion"));
		cliente.setLocalidad(rs.getString("localidad"));
		cliente.setProvincia(rs.getString("provincia"));
		cliente.setEmail(rs.getString("correoElectronico"));
		cliente.setTelefono(rs.getString("telefono"));
		cliente.setEstado(rs.getBoolean("estado"));
		return cliente;
	}

	public int getNextID() {
		Connection conn = Conexion.getConexion().getSQLConexion();
		;
		int id = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idCliente) + 1 as id FROM clientes");

			while (rs.next()) {
				id = rs.getInt("id");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean eliminarCliente(String idUsuario) {

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try {

			statement = conexion
					.prepareStatement("update clientes SET estado = false where idUsuario = '" + idUsuario + "'");
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				deleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return deleteExitoso;
	}

	@Override
	public Cliente leerCliente(String cli) {

		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		Cliente clienteBuscado = new Cliente();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readCliente);
			System.out.println("idUsuario " + cli);
			statement.setString(1, cli);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Usuario usuario = new Usuario();
				UsuarioDao udao = new UsuarioDaoImpl();
				
				usuario = udao.ReadUser(resultSet.getString("idUsuario"));
				clienteBuscado.setUsuario(usuario);
				clienteBuscado.setDni(resultSet.getString("dni"));
				clienteBuscado.setCuil(resultSet.getString("cuil"));
				clienteBuscado.setNombre(resultSet.getString("nombre"));
				clienteBuscado.setApellido(resultSet.getString("apellido"));
				clienteBuscado.setSexo(resultSet.getString("sexo"));
				clienteBuscado.setNacionalidad(resultSet.getString("nacionalidad"));
				clienteBuscado.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
				clienteBuscado.setDireccion(resultSet.getString("direccion"));
				clienteBuscado.setLocalidad(resultSet.getString("localidad"));
				clienteBuscado.setProvincia(resultSet.getString("provincia"));
				clienteBuscado.setEmail(resultSet.getString("correoElectronico"));
				clienteBuscado.setTelefono(resultSet.getString("telefono"));
				clienteBuscado.setEstado(resultSet.getBoolean("estado"));

			} else {
				clienteBuscado = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clienteBuscado;
	}

	@Override
	public boolean modificarCliente(Cliente cliente, String idUsuario) {

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {

			statement = conexion.prepareStatement("update clientes SET " + " dni = '" + cliente.getDni() + "', cuil = '"
					+ cliente.getCuil() + "', nombre = '" + cliente.getNombre() + "', apellido = '"
					+ cliente.getApellido() + "', sexo = '" + cliente.getSexo() + "', nacionalidad = '"
					+ cliente.getNacionalidad() + "', fechaNacimiento = '" + cliente.getFechaNacimiento()
					+ "', direccion = '" + cliente.getDireccion() + "', localidad = '" + cliente.getLocalidad()
					+ "', provincia = '" + cliente.getProvincia() + "', correoElectronico = '" + cliente.getEmail()
					+ "', telefono = '" + cliente.getTelefono() + "', estado= " + cliente.getEstado()
					+ " where idUsuario = '" + idUsuario + "'");

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
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
	public ArrayList<Cliente> listarClientesFiltro(Cliente cliente) {
		ResultSet rs;
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		try {
			StringBuilder sqlStatement = new StringBuilder("Select * from clientes where ");

			if (cliente.getDni() != null) {
				sqlStatement.append("dni like '%").append(cliente.getDni()).append("%'and ");
			}
			if (cliente.getUsuario().getIdUsuario() != null) {
				sqlStatement.append("idUsuario like '%").append(cliente.getUsuario().getIdUsuario()).append("%'and ");
			}
			if (cliente.getNombre() != null) {
				sqlStatement.append("nombre like '%").append(cliente.getNombre()).append("%'and ");
			}
			if (cliente.getSexo() != null) {
				sqlStatement.append("sexo like '%").append(cliente.getSexo()).append("%'and ");
			}

			sqlStatement.append("estado =").append(cliente.getEstado()).append("");

			Statement st = conn.createStatement();
			rs = st.executeQuery(sqlStatement.toString());

			while (rs.next()) {
				lista.add(getCliente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public boolean existeCliente(String dni) {
		Connection conn = Conexion.getConexion().getSQLConexion();
		
		String dniexiste = null;
		Boolean existe = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from clientes where dni ='" + dni + "'");
			while (rs.next()) {
			dniexiste = rs.getString("dni");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(dniexiste!=null) {
			existe=true;
		} else {
			existe=false;
		}
		
		return existe;
	}
}




