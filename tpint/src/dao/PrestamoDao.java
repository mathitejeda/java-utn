package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Prestamo;

public interface PrestamoDao {
	public boolean agregarPrestamo(Prestamo prestamo);
	public Prestamo getPrestamo(String idPrestamo);
	public List<Prestamo> listarPrestamosUsuario(String idUsuario);
	public List<Prestamo> listarAprobadosPorUsuario(String idUsuario);
	public ArrayList<Prestamo> listarPrestamosFiltro(Prestamo prestamo, String operador);
	public ArrayList<Prestamo> listarTodos();
	public boolean modificarPrestamo(Prestamo prestamo);
	public boolean rechazarPrestamo(String idPrestamo);
	public int getNextId();
	public ArrayList<Prestamo> listarTodosEstado(boolean estado);
}
