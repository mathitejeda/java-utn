package dao;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoDao {
	public boolean agregarMovimiento(Movimiento movimiento);
	public ArrayList<Movimiento> listarMovimientosUsuario(String idUsuario);
	public ArrayList<Movimiento> listarMovimientosCuenta(Cuenta cuenta);
	public ArrayList<Movimiento> listarTodos();
	public int getNextID ();
	public ArrayList<Movimiento> listarMovimientosTipo(int tipo);
}
