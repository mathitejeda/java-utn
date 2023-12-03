package negocioImpl;

import java.util.List;

import dao.PagoPrestamoDao;
import daoImpl.PagoPrestamoDaoImpl;
import entidad.PagoPrestamo;
import negocio.PagoPrestamoNegocio;

public class PagoPrestamoNegocioImpl implements PagoPrestamoNegocio {

	@Override
	public boolean agregarPagoPrestamo(String idPrestamo, int nroPago) {
		PagoPrestamoDao pagoPrestamoDao = new PagoPrestamoDaoImpl();
		return pagoPrestamoDao.agregarPagoPrestamo(idPrestamo, nroPago);
	}

	@Override
	public List<PagoPrestamo> listarPagos(String idPrestamo) {
		PagoPrestamoDao pagoPrestamoDao = new PagoPrestamoDaoImpl();
		return pagoPrestamoDao.listarPagos(idPrestamo);
	}

	@Override
	public int getProximaCuota(String idPrestamo) {
		PagoPrestamoDao pagoPrestamoDao = new PagoPrestamoDaoImpl();
		return pagoPrestamoDao.getProximaCuota(idPrestamo);
	}

}
