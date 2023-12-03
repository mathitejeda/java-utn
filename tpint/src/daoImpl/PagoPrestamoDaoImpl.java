package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PagoPrestamoDao;
import entidad.PagoPrestamo;

public class PagoPrestamoDaoImpl implements PagoPrestamoDao {
	@Override
	public boolean agregarPagoPrestamo(String idPrestamo, int nroPago) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement st;
		conexion = Conexion.getConexion().getSQLConexion();
		boolean exito = false;
		try {
			String queryInsert = "insert into PagoPrestamos(idPrestamo, fechaPago, nroPago) values(?,?,?)";

			st = conexion.prepareStatement(queryInsert);

			Date date = new Date(System.currentTimeMillis());
			java.sql.Date mFechasql = new java.sql.Date(date.getTime());

			st.setInt(1, Integer.parseInt(idPrestamo));
			st.setDate(2, mFechasql);
			st.setInt(3, nroPago);

			if (st.executeUpdate() > 0) {
				conexion.commit();
				exito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (conexion != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return exito;
	}

	@Override
	public List<PagoPrestamo> listarPagos(String idPrestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<PagoPrestamo> lista = new ArrayList<PagoPrestamo>();
		Connection conexion = null;
		PreparedStatement st;
		ResultSet rs;
		conexion = Conexion.getConexion().getSQLConexion();
		try {
			String query = "select * from PagoPrestamos where idPrestamo = ?";

			st = conexion.prepareStatement(query);

			st.setInt(1, Integer.parseInt(idPrestamo));
			rs = st.executeQuery();

			while (rs.next()) {
				PagoPrestamo pagoPrestamo = new PagoPrestamo();
				pagoPrestamo.setIdPago(rs.getInt("idPago"));
				pagoPrestamo.setIdPrestamo(rs.getInt("idPrestamo"));
				pagoPrestamo.setFechaPago(rs.getDate("fechaPago"));
				pagoPrestamo.setNroPago(rs.getInt("nroPago"));
				lista.add(pagoPrestamo);
			}
			if (st.executeUpdate() > 0) {
				conexion.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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
	public int getProximaCuota(String idPrestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int proximaCuota = 0;
		Connection conexion = null;
		PreparedStatement st;
		ResultSet rs;
		conexion = Conexion.getConexion().getSQLConexion();
		try {
			String query = "select ifnull(max(nroPago), 0) + 1 from PagoPrestamos where idPrestamo = ?";

			st = conexion.prepareStatement(query);

			st.setInt(1, Integer.parseInt(idPrestamo));
			rs = st.executeQuery();

			if (rs.next()) {
				proximaCuota = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (conexion != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return proximaCuota;
	}
}
