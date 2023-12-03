package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoSeguroDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "SegurosGroup";
	
	public ArrayList<TipoSeguro> obtenerTipoSeguros() {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<TipoSeguro> lista = new ArrayList<TipoSeguro>();
		
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT idTipo,descripcion FROM tipoSeguros");
			
			while(rs.next()){				
				TipoSeguro tipoSeguroRs = new TipoSeguro();
				tipoSeguroRs.setIdTipo(rs.getInt("idTipo"));
				tipoSeguroRs.setDescripcion(rs.getString("descripcion"));
				
				lista.add(tipoSeguroRs);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public int obtenerIdTipo (String desc) {	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int id = 0;
		Connection cn = null;		
		try
		{
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = cn.prepareStatement("SELECT * FROM tiposeguros WHERE descripcion = ?");
			miSentencia.setString(1, desc);
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			id = resultado.getInt(1);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return id;
	}

	public String obtenerDescripcion (int idTipo) {	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String desc="";
		Connection cn = null;	
		try
		{
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = cn.prepareStatement("SELECT * FROM tiposeguros WHERE idTipo = ?");
			miSentencia.setInt(1, idTipo);
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			desc = resultado.getString(2);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return desc;
	}
}
