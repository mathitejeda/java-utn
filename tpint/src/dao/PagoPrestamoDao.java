package dao;

import java.util.List;

import entidad.PagoPrestamo;

public interface PagoPrestamoDao {
	public boolean agregarPagoPrestamo(String idPrestamo, int nroPago);

	public List<PagoPrestamo> listarPagos(String idPrestamo);

	public int getProximaCuota(String idPrestamo);
}
