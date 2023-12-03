package negocioImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import dao.CuentaDao;
import dao.MovimientoDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio   {
	String CONCEPTO_MOV_INICIAL = "Deposito inicial";
	BigDecimal IMPORTE_INICIAL = new BigDecimal("10000.00");

	@Override
	public boolean agregarCuenta(Cuenta cuenta) {		
		CuentaDao cuentaDao = new CuentaDaoImpl();
		String idUsuario = cuenta.getCliente().getUsuario().getIdUsuario();
		boolean	agrego = false;
		
		//VALIDO QUE EL CLIENTE TENGA MENOS DE 3 CUENTAS:
		if(cuentaDao.listarCuentasCliente(idUsuario).size() < 3) {
			agrego = cuentaDao.agregarCuenta(cuenta);
		}
		
		//AGREGO MOVIMIENTO X ALTA CUENTA:
		if(agrego) {
			MovimientoDao movimientoDao = new MovimientoDaoImpl();
			Movimiento movimiento = new Movimiento();
			//LocalDate fechaHoy = LocalDate.now();
			
			movimiento.setIdMovimiento(movimientoDao.getNextID());
			movimiento.setCuenta(cuenta);
			//Probar este casteo:
			//Date date = (Date) Date.from(fechaHoy.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date date = new Date(System.currentTimeMillis());
			java.sql.Date mFechasql = new java.sql.Date(date.getTime());
			
			movimiento.setFechaOperacion(mFechasql);
			movimiento.setConcepto(CONCEPTO_MOV_INICIAL);
			movimiento.setImporte(IMPORTE_INICIAL);
			movimiento.setTipoMovimiento(TipoMovimiento.ALTA_CUENTA);
			
			agrego = movimientoDao.agregarMovimiento(movimiento);
		}		
		return agrego;
	}
	
	@Override
	public Cuenta leerCuenta(String cbu) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		Cuenta cuenta = new Cuenta();

		if(cbu != null && !cbu.isEmpty()) {
			cuenta = cuentaDao.leerCuenta(cbu);
			
			if(cuenta != null) {//cambio
				if (cuenta.getIdTipoCuenta() == 1) {
					cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
				} else if (cuenta.getIdTipoCuenta() == 2) {
					cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
				}
			}
		}		
		return cuenta;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasCliente(String idUsuario) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		ArrayList<Cuenta> listaCuentasCliente = cuentaDao.listarCuentasCliente(idUsuario);
		
		for (Cuenta cuenta : listaCuentasCliente) {
			if (cuenta.getIdTipoCuenta() == 1) {
				cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
			} else if (cuenta.getIdTipoCuenta() == 2) {
				cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
			}
		}
		
		return listaCuentasCliente;
	}

	@Override
	public ArrayList<Cuenta> listarCuentas() {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		ArrayList<Cuenta> listaCuentas = cuentaDao.listarCuentas();
		
		for (Cuenta cuenta : listaCuentas) {
			if (cuenta.getIdTipoCuenta() == 1) {
				cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
			} else if (cuenta.getIdTipoCuenta() == 2) {
				cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
			}
		}
		
		return listaCuentas;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		boolean modifico = false;
		
		if(cuenta != null && cuenta.getSaldo().signum()>=0) {
			modifico = cuentaDao.modificarCuenta(cuenta);
			System.out.println("modifico??" + modifico);
			
			if(!modifico) { return false; }
		}
		
		if(cuenta != null && cuenta.getEstado() == false) {
			modifico = cuentaDao.eliminarCuenta(cuenta.getCbu());
		}		
		return modifico;
	}

	@Override
	public boolean eliminarCuenta(String cbu) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		boolean agrego = false;
		
		if(cbu != null && !cbu.isEmpty()) {
			agrego = cuentaDao.eliminarCuenta(cbu);
		}		
		return agrego;
	}

	@Override
	public String getNextCBU() {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		String cbu = cuentaDao.getNextCBU();
		return cbu;
	}

	@Override
	public boolean transferencia(Cuenta cuenta, Movimiento movimiento) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		boolean modifico = false;
		
		if(cuenta != null && movimiento != null) {
			modifico = cuentaDao.transferencia(cuenta, movimiento);
			System.out.println("transfirió??" + modifico);
			
			if(!modifico) { return false; }
		}
		
		if(cuenta != null && cuenta.getEstado() == false) {
			modifico = cuentaDao.eliminarCuenta(cuenta.getCbu());
		}		
		return modifico;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasFiltro(Cuenta cuenta, String operador) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		ArrayList<Cuenta> listaCuentasCliente = cuentaDao.listarCuentasFiltro(cuenta, operador);
		
		for (Cuenta cuenta2 : listaCuentasCliente) {
			if (cuenta2.getIdTipoCuenta() == 2) {
				cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
			} else if (cuenta2.getIdTipoCuenta() == 1) {
				cuenta2.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
			}
		}

		return listaCuentasCliente;
		
	}
}
