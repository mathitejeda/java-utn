package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {
	public boolean insert(Movimiento movimiento);
	public ArrayList<Movimiento> listarMovimientosPorMes(int tipo, String usuario);
	public ArrayList<Movimiento> listarMovimientosCuenta(Cuenta cuenta);
	public int totalMovimientos(ArrayList<Movimiento> listMovimientos);
}
