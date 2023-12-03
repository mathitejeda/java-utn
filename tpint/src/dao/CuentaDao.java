package dao;

import java.util.ArrayList;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;

public interface CuentaDao {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean transferencia(Cuenta cuenta, Movimiento movimiento);
	public Cuenta leerCuenta(String cbu);
	public ArrayList<Cuenta> listarCuentasCliente(String idUsuario);
	public ArrayList<Cuenta> listarCuentas();
	public ArrayList<Cuenta> listarCuentasFiltro(Cuenta cuenta, String operador);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(String cbu);
	public int getNextID ();
	public String getNextCBU();
}
