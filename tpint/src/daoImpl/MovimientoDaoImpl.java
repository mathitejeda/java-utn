package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CuentaDao;
import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private static final String insert = "INSERT INTO movimientos(cbu, fechaOperacion, concepto, importe, idTipoMovimiento) " + 
										 "VALUES (?, ?, ?, ?, ?)";
	private static final String listarCbu = "SELECT * FROM movimientos WHERE cbu = ?";
	
	@Override
	public boolean agregarMovimiento(Movimiento movimiento){
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try	{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, movimiento.getCuenta().getCbu());
			statement.setDate(2, movimiento.getFechaOperacion());
			statement.setString(3, movimiento.getConcepto());
			statement.setBigDecimal(4, movimiento.getImporte());
			statement.setInt(5, movimiento.getTipoMovimiento().getIdTipoMovimiento());
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
//		finally {
//			if (conexion != null) {
//				try {
//					Conexion.getConexion().cerrarConexion();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		return isInsertExitoso;
	}

	@Override
	public ArrayList<Movimiento> listarMovimientosUsuario(String idUsuario) {
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		
		try
		{
			Statement st = conn.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM movimientos WHERE idUsuario = "+ idUsuario);

			while(rs.next()){			
				Movimiento movimiento = new Movimiento();
				CuentaDao cuentadao = new CuentaDaoImpl();
				Cuenta cuenta = new Cuenta();
				
				movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
				cuenta = cuentadao.leerCuenta(rs.getString("cbu"));				
				movimiento.setCuenta(cuenta);				
				movimiento.setFechaOperacion(rs.getDate("fechaOperacion"));
				movimiento.setConcepto(rs.getString("concepto"));
				movimiento.setImporte(rs.getBigDecimal("importe"));
				movimiento.setTipoMovimiento(rs.getInt("idTipoMovimiento"));				
				
				lista.add(movimiento);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return lista;
	}
	
	@Override
	public ArrayList<Movimiento> listarMovimientosCuenta(Cuenta cuenta) { 
		ArrayList<Movimiento> movimientos = new ArrayList<>();
		
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		ResultSet rs;
		try {
			statement = conexion.getSQLConexion().prepareStatement(listarCbu);
			statement.setString(1, cuenta.getCbu());
			rs = statement.executeQuery();
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
				movimiento.setFechaOperacion(rs.getDate("fechaOperacion"));
				movimiento.setConcepto(rs.getString("concepto"));
				movimiento.setImporte(rs.getBigDecimal("importe"));
				movimiento.setTipoMovimiento(rs.getInt("idTipoMovimiento"));
				
				movimientos.add(movimiento);
				
				System.out.println(movimiento.getIdMovimiento());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movimientos;
	}
	
	@Override
	public ArrayList<Movimiento> listarTodos() {
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		
		try
		{
			Statement st = conn.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM movimientos");
			
			while(rs.next()){			
				Movimiento movimiento = new Movimiento();
				CuentaDao cuentadao = new CuentaDaoImpl();
				Cuenta cuenta = new Cuenta();
				
				movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
				cuenta = cuentadao.leerCuenta(rs.getString("cbu"));	
				movimiento.setCuenta(cuenta);				
				movimiento.setFechaOperacion(rs.getDate("fechaOperacion"));
				movimiento.setConcepto(rs.getString("concepto"));
				movimiento.setImporte(rs.getBigDecimal("importe"));
				movimiento.setTipoMovimiento(rs.getInt("idTipoMovimiento"));	
				
				lista.add(movimiento);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return lista;
	}
	
	@Override
	public ArrayList<Movimiento> listarMovimientosTipo(int tipo) {
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		Connection conn = Conexion.getConexion().getSQLConexion();
		
		try
		{
			Statement st = conn.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM movimientos WHERE idTipoMovimiento = "+ tipo);

			while(rs.next()){			
				Movimiento movimiento = new Movimiento();
				CuentaDao cuentadao = new CuentaDaoImpl();
				Cuenta cuenta = new Cuenta();
				
				movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
				cuenta = cuentadao.leerCuenta(rs.getString("cbu"));				
				movimiento.setCuenta(cuenta);				
				movimiento.setFechaOperacion(rs.getDate("fechaOperacion"));
				movimiento.setConcepto(rs.getString("concepto"));
				movimiento.setImporte(rs.getBigDecimal("importe"));
				movimiento.setTipoMovimiento(rs.getInt("idTipoMovimiento"));				
				
				lista.add(movimiento);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return lista;
	}
	
	public int getNextID () {
		Connection conn = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idMovimiento) + 1 AS id FROM movimientos");
			
			while(rs.next()){
				id = rs.getInt("id");
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
		return id;
	}

	
}
