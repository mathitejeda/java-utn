package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {
	private static final String insert = "INSERT INTO Personas(Dni, Nombre, Apellido) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE Dni = ?";
	private static final String update = "UPDATE personas SET Nombre = ?, Apellido = ?  WHERE Dni = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String readDni = "SELECT * FROM personas WHERE Dni = ?";

	public boolean insert(Persona persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try	{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
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
	public boolean delete(Persona persona_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, persona_a_eliminar.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	

	@Override
	public boolean update(Persona persona_a_editar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona_a_editar.getNombre());
			statement.setString(2, persona_a_editar.getApellido());
			statement.setString(3, persona_a_editar.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isupdateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isupdateExitoso;
	}

	@Override
	public List<Persona> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException {
		String dni = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		Persona persona = new Persona(dni, nombre, apellido);
		return persona;
	}

	@Override
	public Persona ReadDni(String dni){
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		Persona personaBuscada = new Persona();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readDni);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				personaBuscada = getPersona(resultSet);
			}else {
				personaBuscada = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personaBuscada;
	}

}
