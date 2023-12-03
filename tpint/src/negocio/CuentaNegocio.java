package negocio;

import java.util.ArrayList;
import entidad.Cuenta;
import entidad.Movimiento;

public interface CuentaNegocio {
	public boolean agregarCuenta(Cuenta cuenta);
	public Cuenta leerCuenta(String cbu);
	public ArrayList<Cuenta> listarCuentasCliente(String idUsuario);
	public ArrayList<Cuenta> listarCuentas();
	public ArrayList<Cuenta> listarCuentasFiltro(Cuenta cuenta, String operador);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean transferencia(Cuenta cuenta, Movimiento movimiento);
	public boolean eliminarCuenta(String cbu);
	public String getNextCBU();
}
