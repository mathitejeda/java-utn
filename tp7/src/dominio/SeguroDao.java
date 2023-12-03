package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeguroDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "SegurosGroup";

	
	public int agregarSeguro(Seguro seguro) {		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user, pass);
			Statement st = cn.createStatement();
			String query = "INSERT INTO seguros(descripcion, idTipo, costoContratacion, costoAsegurado) "
					+ "values ('"+seguro.getDescripcion()+"','"+seguro.getTipoSeguro().getIdTipo()+"','"
					+seguro.getCostoContratacion()+"','"+seguro.getCostoAsegurado()+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public ArrayList<Seguro> obtenerSeguros() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //com.mysql.cj.jdbc.Driver
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT idSeguro,descripcion,idTipo,costoContratacion,costoAsegurado FROM seguros");
			
			while(rs.next()){				
				Seguro seguro = new Seguro();
				TipoSeguro tipoSeguro = new TipoSeguro();
				seguro.setId(rs.getInt("idSeguro"));
				seguro.setDescripcion(rs.getString("descripcion"));
				tipoSeguro.setIdTipo(Integer.parseInt(rs.getString("idTipo")));
				seguro.setTipoSeguro(tipoSeguro);
				seguro.setCostoContratacion(rs.getFloat("costoContratacion"));
				seguro.setCostoAsegurado(rs.getFloat("costoAsegurado"));
				
				lista.add(seguro);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}

	public ArrayList<Seguro> obtenerSeguros_X_Tipo(int TipoId){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement st = conn.prepareStatement("SELECT idSeguro,descripcion,idTipo,costoContratacion,costoAsegurado FROM seguros WHERE idTipo=?");
			st.setInt(1, TipoId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){				
				Seguro seguro = new Seguro();
				TipoSeguro tipoSeguro = new TipoSeguro();
				seguro.setId(rs.getInt("idSeguro"));
				seguro.setDescripcion(rs.getString("descripcion"));
				tipoSeguro.setIdTipo(Integer.parseInt(rs.getString("idTipo")));
				seguro.setTipoSeguro(tipoSeguro);
				seguro.setCostoContratacion(rs.getFloat("costoContratacion"));
				seguro.setCostoAsegurado(rs.getFloat("costoAsegurado"));
				
				lista.add(seguro);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public int getNextID () {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		int id = 1;
		
		try
		{
			conn = DriverManager.getConnection(host+dbName, user, pass);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idSeguro) + 1 as id FROM seguros");
			
			while(rs.next()){
				id = rs.getInt("id");
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
}
