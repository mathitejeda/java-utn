package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entidad.Prestamo;

public interface PrestamoNegocio {
	public boolean agregarPrestamo(Prestamo prestamo);
	public Prestamo getPrestamo(String idPrestamo);
	public List<Prestamo> listarPrestamosUsuario(String idUsuario);
	public List<Prestamo> listarTodos();
	public List<Prestamo> listarAprobadosPorUsuario(String idUsuario);
	public List<Prestamo> listarPendAprobacion();
	public ArrayList<Prestamo> listarPrestamosFiltro(Prestamo prestamo, String operador);
	public ArrayList<Prestamo> listarTodosPorMes(int estado, String usuarioFiltro);
	public BigDecimal totalMonto(ArrayList<Prestamo> listPrestamos);
	public boolean modificarPrestamo(Prestamo prestamo);
	public boolean rechazarPrestamo(String idPrestamo);
	public int getNextId();
}
