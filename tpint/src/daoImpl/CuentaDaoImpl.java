package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import dao.ClienteDao;
import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;

public class CuentaDaoImpl implements CuentaDao{
	
	private static final String insert = "INSERT INTO cuentas(cbu, idUsuario, nroCuenta, idTipoCuenta, fechaAlta, saldo, estado) VALUES(?, ?, ?, ?, ?, ?, ?) ";
	private static final String leerTodo = "SELECT * FROM cuentas";
	private static final String leerCuentaClienteID = "SELECT * FROM cuentas WHERE idUsuario=?";
	private static final String leerCuentaClienteCBU = "SELECT * FROM cuentas WHERE cbu=?";
	private static final String transferencia = " UPDATE cuentas SET saldo = ? WHERE cbu = ? ";
	
	
	@Override
	public boolean agregarCuenta(Cuenta cuenta) {

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		Connection conexion = null;
		
		PreparedStatement st;

		conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try	{

			st = conexion.prepareStatement(insert);
			
			Date date = new Date(System.currentTimeMillis());
			java.sql.Date mFechasql = new java.sql.Date(date.getTime());
			
			st.setString(1, cuenta.getCbu());
			st.setString(2, cuenta.getCliente().getUsuario().getIdUsuario());
			st.setInt(3, cuenta.getNroCuenta());
			st.setInt(4, cuenta.getIdTipoCuenta());
			st.setDate(5, mFechasql);

			
			
			st.setBigDecimal(6, cuenta.getSaldo());
			Boolean estado = true;
			st.setBoolean(7, estado);
			
			
			if(st.executeUpdate() > 0)
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
		finally {
			if (conexion != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return isInsertExitoso;
	}
	
	

	@Override
	public ArrayList<Cuenta> listarCuentasCliente(String idUsuario) {	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		PreparedStatement st;
		ResultSet rs;
		Connection conn = Conexion.getConexion().getSQLConexion();		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		try
		{
			st = conn.prepareStatement(leerCuentaClienteID);
			st.setString(1, idUsuario);
			rs = st.executeQuery();

			while(rs.next()){			
				Cuenta cuenta = new Cuenta();
				Usuario usuario = new Usuario();
				Cliente cliente = new Cliente();
				
				cuenta.setCbu(rs.getString("cbu"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				cuenta.setCliente(cliente);
				cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				cuenta.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				cuenta.setFechaAlta(rs.getDate("fechaAlta"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));				
				
				lista.add(cuenta);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listarCuentas() {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Connection conn = Conexion.getConexion().getSQLConexion();		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		try
		{
			Statement st = conn.createStatement();	
			ResultSet rs = st.executeQuery(leerTodo);

			while(rs.next()){			
				Cuenta cuenta = new Cuenta();
				Cliente cliente = new Cliente();
				ClienteDao clienteDao = new ClienteDaoImpl();			
				
				cuenta.setCbu(rs.getString("cbu"));	
				cliente = clienteDao.leerCliente(rs.getString("idUsuario"));
				cuenta.setCliente(cliente);
				cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				cuenta.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				cuenta.setFechaAlta(rs.getDate("fechaAlta"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));				
				
				lista.add(cuenta);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try { Conexion.getConexion().cerrarConexion(); } 
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		return lista;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Connection conn = Conexion.getConexion().getSQLConexion();
		boolean agrego = false;
		
		try
		{	StringBuilder sqlStatement = new StringBuilder("UPDATE cuentas SET  ");  
	        
	        if (cuenta.getIdTipoCuenta() != 0) {
	            sqlStatement.append("idTipoCuenta = '").append(cuenta.getIdTipoCuenta()).append("', ");
	        }

			if (cuenta.getFechaAlta() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(cuenta.getFechaAlta());
				sqlStatement.append("fechaAlta = '").append(formattedDate).append("', ");
			}
	        
	        BigDecimal saldoCero = BigDecimal.ZERO;

	        if (cuenta.getSaldo() != null && cuenta.getSaldo() != saldoCero) {
	            sqlStatement.append("saldo = '").append(cuenta.getSaldo()).append("', ");
	        }

	        sqlStatement.setLength(sqlStatement.length() - 2);
	        sqlStatement.append(" ,estado = ").append(cuenta.getEstado()).append("");
	        sqlStatement.append(" WHERE cbu = '").append(cuenta.getCbu()).append("'");
	        Statement st = conn.createStatement();
	        if(st.executeUpdate(sqlStatement.toString()) > 0){	
	        	agrego = true; 
	        	conn.commit();	
	        }      
	        
	    } 	
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
        return agrego;
	}
	
	@Override
	public boolean transferencia(Cuenta cuenta, Movimiento movimiento) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(transferencia);
			statement.setBigDecimal(1, cuenta.getSaldo());
			statement.setString(2, cuenta.getCbu());
			
			MovimientoNegocio movNegocio = new MovimientoNegocioImpl();
			
			if(statement.executeUpdate() > 0)
			{
				if(movNegocio.insert(movimiento)) {
					conexion.commit();
					isupdateExitoso = true;
					
				}else
					conexion.rollback();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isupdateExitoso;
	}
	
	@Override
	public boolean eliminarCuenta(String cbu) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try	{

			statement = conexion.prepareStatement("UPDATE cuentas SET estado = false WHERE cbu = '" + cbu + "'");
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				deleteExitoso = true;
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
		
		return deleteExitoso;
	}
	
	public int getNextID () {
		Connection conn = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idCliente) + 1 as id FROM clientes");
			
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

	@Override
	public Cuenta leerCuenta(String cbu) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Connection conn = Conexion.getConexion().getSQLConexion();		
		Cuenta cuenta = null;
				
		try
		{				
			PreparedStatement st = conn.prepareStatement(leerCuentaClienteCBU);
			st.setString(1, cbu);
	        ResultSet rs = st.executeQuery();
	        
	        if(rs.next()) {
				Cliente cliente = new Cliente();
				ClienteDao clienteDao = new ClienteDaoImpl();
				
				cuenta = new Cuenta();//cambio
				
				cuenta.setCbu(rs.getString("cbu"));
				cliente = clienteDao.leerCliente(rs.getString("idUsuario"));
				cuenta.setCliente(cliente);
				cuenta.setNroCuenta(rs.getInt("nroCuenta"));
				cuenta.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				cuenta.setFechaAlta(rs.getDate("fechaAlta"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));
	        }					        
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
		return cuenta;
	}

	public String getNextCBU() {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

		Connection conn = Conexion.getConexion().getSQLConexion();
		String cbu = "";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(cbu) as cbu FROM cuentas");
			
			while(rs.next()){
				cbu = rs.getString("cbu");
				cbu = cbu.substring(3);
				int cbuInt = Integer.parseInt(cbu);
				cbuInt++;
				cbu = "CBU" + String.format("%03d", cbuInt);
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return cbu;
	}

	private Cuenta getCuenta(ResultSet rs) throws SQLException {
		Cuenta cuenta = new Cuenta();
		
		Cliente cliente = new Cliente();
		ClienteDao clienteDao = new ClienteDaoImpl();
		
		cuenta.setCbu(rs.getString("cbu"));
		cliente = clienteDao.leerCliente(rs.getString("idUsuario"));
		cuenta.setCliente(cliente);
		cuenta.setNroCuenta(rs.getInt("nroCuenta"));
		cuenta.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
		cuenta.setFechaAlta(rs.getDate("fechaAlta"));
		cuenta.setSaldo(rs.getBigDecimal("saldo"));
		cuenta.setEstado(rs.getBoolean("estado"));

		System.out.println("Cuenta encontrada: " + cuenta.getEstado());
		System.out.println("Cbu: " + cuenta.getCbu());
		return cuenta;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasFiltro(Cuenta cuenta, String operador) {
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		ResultSet rs;
		Connection conn = Conexion.getConexion().getSQLConexion();
		try
		{
			StringBuilder sqlStatement = new StringBuilder("Select * from cuentas where ");
	        
			 if (cuenta.getNroCuenta() != 0) {
	            sqlStatement.append("nroCuenta like '%").append(cuenta.getNroCuenta()).append("%'and ");
	        }
			 
	        if (!cuenta.getCbu().isEmpty()) {
	            sqlStatement.append("cbu like '%").append(cuenta.getCbu()).append("%'and ");
	        }
	        if (cuenta.getIdTipoCuenta() != 0) {
	            sqlStatement.append("idTipoCuenta like '%").append(cuenta.getIdTipoCuenta()).append("%'and ");
	        }
	        if (operador != "" && cuenta.getSaldo() != BigDecimal.ZERO) {
	            sqlStatement.append("saldo ").append(operador).append(" ").append(cuenta.getSaldo()).append(" and ");
	        }
	        
	        
	        sqlStatement.append("estado =").append(cuenta.getEstado()).append("");

	        Statement st = conn.createStatement();
			rs = st.executeQuery(sqlStatement.toString());
			
			while(rs.next()){		
				lista.add(getCuenta(rs));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return lista;
	}

}