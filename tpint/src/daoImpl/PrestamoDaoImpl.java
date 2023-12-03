package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import dao.ClienteDao;
import dao.PrestamoDao;
import entidad.Cliente;
import entidad.Prestamo;
import entidad.Usuario;

public class PrestamoDaoImpl implements PrestamoDao{

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
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
		{
			Statement st = conn.createStatement();
			String query = "INSERT INTO prestamos (idUsuario, cbu, fechaSolicitud, montoCouta, montoTotal, plazoPago, aprobado, pendiente) "
					+ "values ('"+prestamo.getCliente().getUsuario().getIdUsuario()+"','"+prestamo.getCbu()+"','"+prestamo.getFechaSolicitud()+"'"
							+ ",'"+prestamo.getMontoCuota()+"','"+prestamo.getMontoTotal()+"','"+prestamo.getPlazoPago()+"','"
							+ (prestamo.isAprobado() ? 1 : 0) +"','"+ (prestamo.isPendiente() ? 1 : 0) +"')";

			if(st.executeUpdate(query) > 0){ 
				conn.commit();
				agrego = true; 
			}

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
		return agrego;
	}

	@Override
	public List<Prestamo> listarPrestamosUsuario(String idUsuario) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = Conexion.getConexion().getSQLConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos WHERE idUsuario = '"+ idUsuario + "'");
			while(rs.next()){
				Prestamo prestamo = new Prestamo();
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();

				prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				prestamo.setCliente(cliente);
				prestamo.setCbu(rs.getString("cbu"));
				prestamo.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo.setPlazoPago(rs.getInt("plazoPago"));
				prestamo.setAprobado(rs.getBoolean("aprobado"));
				lista.add(prestamo);
			}
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
		return lista;
	}

	@Override
	public ArrayList<Prestamo> listarTodos() {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		try
		{
			Statement st = conn.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos");
			
			while(rs.next()){			
				Prestamo prestamo = new Prestamo();
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();

				prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				prestamo.setCliente(cliente);
				prestamo.setCbu(rs.getString("cbu"));
				prestamo.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo.setPlazoPago(rs.getInt("plazoPago"));
				prestamo.setAprobado(rs.getBoolean("aprobado"));
				prestamo.setPendiente(rs.getBoolean("pendiente"));

				lista.add(prestamo);
			}
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
		return lista;
	}

	@Override
	public boolean modificarPrestamo(Prestamo prestamo) {
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
		{	StringBuilder sqlStatement = new StringBuilder("UPDATE prestamos SET ");

	        // Validaciones para no hacer update en los campos que no se modific� nada
	        if (prestamo.getCliente().getUsuario().getIdUsuario() != null && !prestamo.getCliente().getUsuario().getIdUsuario().isEmpty()) {
	            sqlStatement.append("idUsuario = '").append(prestamo.getCliente().getUsuario().getIdUsuario()).append("', ");
	        }

	        if (prestamo.getCbu() != null && !prestamo.getCbu().isEmpty()) {
	            sqlStatement.append("cbu = '").append(prestamo.getCbu()).append("', ");
	        }

	        if (prestamo.getFechaSolicitud() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaSolicitudFormatted = sdf.format(prestamo.getFechaSolicitud());
				sqlStatement.append("fechaSolicitud = '").append(fechaSolicitudFormatted).append("', ");
	        }
	        
	        if (prestamo.getMontoCuota() != null) {
	            sqlStatement.append("montoCouta = '").append(prestamo.getMontoCuota()).append("', ");
	        }

	        if (prestamo.getMontoTotal() != null) {
	            sqlStatement.append("montoTotal = '").append(prestamo.getMontoTotal()).append("', ");
	        }

	        if (prestamo.getPlazoPago() != 0) {
	            sqlStatement.append("plazoPago = '").append(prestamo.getPlazoPago()).append("', ");
	        }
	        sqlStatement.append("pendiente = false,");
			sqlStatement.append("aprobado = '").append(prestamo.isAprobado() ? 1 : 0).append("', ");
	        sqlStatement.setLength(sqlStatement.length() - 2);
	        sqlStatement.append(" WHERE idPrestamo = ").append(prestamo.getIdPrestamo());

	        Statement st = conn.createStatement();

	        if(st.executeUpdate(sqlStatement.toString()) > 0){
				conn.commit();
				agrego = true;
			}
	    }
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
		finally {
			if (conn != null) {
				try { Conexion.getConexion().cerrarConexion(); }
				catch (Exception e) { e.printStackTrace(); }
			}
		}
        return agrego;
	}

	@Override
	public boolean rechazarPrestamo(String idPrestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = Conexion.getConexion().getSQLConexion();
		boolean agrego = false;

		try {
			StringBuilder sqlStatement = new StringBuilder("UPDATE prestamos SET aprobado = false, pendiente = false WHERE idPrestamo = ")
					.append(idPrestamo);

			System.out.print("Query: " + sqlStatement);
			Statement st = conn.createStatement();
			System.out.print("Query: " + st);
			if (st.executeUpdate(sqlStatement.toString()) > 0) {
				conn.commit();
				agrego = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return agrego;
	}

	@Override
	public int getNextId() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idPrestamo) FROM prestamos");
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try { Conexion.getConexion().cerrarConexion(); } 
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		return id + 1;
	}

	@Override
	public List<Prestamo> listarAprobadosPorUsuario(String idUsuario) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = Conexion.getConexion().getSQLConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos WHERE idUsuario = '"+ idUsuario + "'");
			while(rs.next()){
				Prestamo prestamo = new Prestamo();
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();

				prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				prestamo.setCliente(cliente);
				prestamo.setCbu(rs.getString("cbu"));
				prestamo.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo.setPlazoPago(rs.getInt("plazoPago"));
				prestamo.setAprobado(rs.getBoolean("aprobado"));
				prestamo.setPendiente(rs.getBoolean("pendiente"));
				lista.add(prestamo);
			}
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
		return lista;
	}

	@Override
	public Prestamo getPrestamo(String idPrestamo) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		Prestamo prestamo = new Prestamo();
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = Conexion.getConexion().getSQLConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos WHERE idPrestamo = '"+ idPrestamo + "'");
			while(rs.next()){
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();

				prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				prestamo.setCliente(cliente);
				prestamo.setCbu(rs.getString("cbu"));
				prestamo.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo.setPlazoPago(rs.getInt("plazoPago"));
				prestamo.setAprobado(rs.getBoolean("aprobado"));
			}
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
		return prestamo;
	}

	@Override
	public ArrayList<Prestamo> listarTodosEstado(boolean estado) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		try
		{
			Statement st = conn.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos where aprobado=" + estado);
			
			while(rs.next()){			
				Prestamo prestamo = new Prestamo();
				ClienteDao clienteDao = new ClienteDaoImpl();
				Cliente cliente = clienteDao.leerCliente(rs.getString("idUsuario"));
				
				prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				prestamo.setCliente(cliente);
				prestamo.setCbu(rs.getString("cbu"));
				prestamo.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo.setPlazoPago(rs.getInt("plazoPago"));
				prestamo.setAprobado(rs.getBoolean("aprobado"));
				prestamo.setPendiente(rs.getBoolean("pendiente"));

				lista.add(prestamo);
			}
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
		return lista;
	}
	
	@Override
	public ArrayList<Prestamo> listarPrestamosFiltro(Prestamo prestamo, String operador) {
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		ResultSet rs;
		Connection conn = Conexion.getConexion().getSQLConexion();	
		try
		{
			StringBuilder sqlStatement = new StringBuilder("Select * from prestamos where ");
	        
			 if (!prestamo.getCbu().isEmpty()) {
	            sqlStatement.append("cbu like '%").append(prestamo.getCbu()).append("%'and ");
	        }
			 
	        if (prestamo.getFechaSolicitud()!=null) {
	            sqlStatement.append("fechaSolicitud like '%").append(prestamo.getFechaSolicitud()).append("%'and ");
	        }
	        if (operador != "" && prestamo.getMontoTotal() != BigDecimal.ZERO) {
	            sqlStatement.append("montoTotal ").append(operador).append(" ").append(prestamo.getMontoTotal()).append(" and ");
	        }
	        sqlStatement.append("aprobado =").append(prestamo.isAprobado()).append("");
	        
	        System.out.print("Query:" +  sqlStatement);
	        Statement st = conn.createStatement();
			rs = st.executeQuery(sqlStatement.toString());
			
			while(rs.next()){
				Prestamo prestamo2 = new Prestamo();
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();

				prestamo2.setIdPrestamo(rs.getInt("idPrestamo"));
				usuario.setIdUsuario(rs.getString("idUsuario"));
				cliente.setUsuario(usuario);
				prestamo2.setCliente(cliente);
				prestamo2.setCbu(rs.getString("cbu"));
				prestamo2.setFechaSolicitud(rs.getDate("fechaSolicitud"));
				prestamo2.setMontoCuota(rs.getBigDecimal("montoCouta"));
				prestamo2.setMontoTotal(rs.getBigDecimal("montoTotal"));
				prestamo2.setPlazoPago(rs.getInt("plazoPago"));
				prestamo2.setAprobado(rs.getBoolean("aprobado"));

				lista.add(prestamo2);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return lista;
	}
}
