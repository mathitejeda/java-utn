package negocio;

import java.util.List;

import entidad.PagoPrestamo;

public interface PagoPrestamoNegocio {
	public boolean agregarPagoPrestamo(String idPrestamo, int nroPago);

	public List<PagoPrestamo> listarPagos(String idPrestamo);

	public int getProximaCuota(String idPrestamo);
}