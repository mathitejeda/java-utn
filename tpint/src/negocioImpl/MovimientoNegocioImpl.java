package negocioImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	@Override
	public boolean insert(Movimiento movimiento) {
		boolean insertExitoso = false;
		
		MovimientoDao dao = new MovimientoDaoImpl();
		
		if(movimiento.getCuenta().getCbu().trim().length()>0) {
			insertExitoso = dao.agregarMovimiento(movimiento);
		}
		
		return insertExitoso;
	}

	@Override
	public ArrayList<Movimiento> listarMovimientosPorMes(int tipo, String usuario) {
		ArrayList<Movimiento> listFinalTotalPorMes = new ArrayList<>();		
		ArrayList<Movimiento> listMov = listarMovimientosTipo(tipo);

		if(listMov != null) {
			if(usuario != null) { 
				listMov = filtrarMovimientoUsuario(listMov, usuario); 
			}
			
			Map<String, ArrayList<Movimiento>> mapListMovsPorMes = mapListMovsPorMes(listMov);

			for (Map.Entry<String, ArrayList<Movimiento>> mapEntryListMovsPorMes : mapListMovsPorMes.entrySet()) {
	            Movimiento movTotalPorMes = new Movimiento();
	            ArrayList<Movimiento> listMovPorMes = mapEntryListMovsPorMes.getValue();
	            StringBuilder sb = new StringBuilder();
	            java.sql.Date fecha = java.sql.Date.valueOf(sb.append(mapEntryListMovsPorMes.getKey()).append("-01").toString());				
	            int año = Integer.parseInt(mapEntryListMovsPorMes.getKey().substring(0,4));				
	            int cantidad=0;

	            for(Movimiento mov : listMovPorMes) {	
	            	movTotalPorMes.setFechaOperacion(fecha);
	            	movTotalPorMes.getFechaOperacion().setYear(año);
	            	
			    	if(usuario != null) { 
			    		movTotalPorMes.setCuenta(mov.getCuenta());
			    		movTotalPorMes.getCuenta().setCliente(mov.getCuenta().getCliente()); 
			    	} else {
			    		movTotalPorMes.setCuenta(mov.getCuenta());
			    		movTotalPorMes.getCuenta().setCliente(null);
			    	}
	            	
	            	if(tipo != 0) {
	            		for(TipoMovimiento tipoMov : TipoMovimiento.values()) {
	            			if(tipoMov.getIdTipoMovimiento() == tipo) {
	            				movTotalPorMes.setTipoMovimiento(tipoMov);
	            			}
	            		}	            		
	            	} else {
	            		movTotalPorMes.setTipoMovimiento(null);
	            	}	            	
	            	cantidad++;	            	
	            }
	            movTotalPorMes.setIdMovimiento(cantidad);
	            listFinalTotalPorMes.add(movTotalPorMes);    
			}
		}
		Collections.sort(listFinalTotalPorMes, Comparator.comparing(Movimiento::getFechaOperacion));
		
		return listFinalTotalPorMes;
	}
	
	private Map<String, ArrayList<Movimiento>> mapListMovsPorMes(ArrayList<Movimiento> listMovDao){
		Map<String, ArrayList<Movimiento>> mappedList = new HashMap<>();

		for(Movimiento movimientoDao : listMovDao){
			String mes = movimientoDao.getMesAño();		
			mappedList.computeIfAbsent(mes, movs -> new ArrayList<>()).add(movimientoDao);			
		}
		return mappedList;
	}
	
	private ArrayList<Movimiento> listarMovimientosTipo(int tipo) {
		MovimientoDao movDao = new MovimientoDaoImpl();
		ArrayList<Movimiento> prestamosList = new ArrayList<>();
	
		switch (tipo) {
		case 0:
			prestamosList = movDao.listarTodos();		
			break;
		default:
			prestamosList = movDao.listarMovimientosTipo(tipo);		
		} 	

		return prestamosList;
	}	

	@Override
	public ArrayList<Movimiento> listarMovimientosCuenta(Cuenta cuenta) {
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		MovimientoDao dao = new MovimientoDaoImpl();
		
		if(cuenta.getCbu().trim().length()>0) {
			movimientos = dao.listarMovimientosCuenta(cuenta);
		}else {
			movimientos = null;
		}
		return movimientos;
		
	}
	
	@Override
	public int totalMovimientos(ArrayList<Movimiento> listMovimientos) {
		int totalMovs = 0;
		
		for(Movimiento movimiento : listMovimientos) {
			totalMovs += movimiento.getIdMovimiento();
		}
		return totalMovs;
	}

	private ArrayList<Movimiento> filtrarMovimientoUsuario(ArrayList<Movimiento> listaMovs, String usuario) {		
		ArrayList<Movimiento> movsFiltrados = new ArrayList<>();

        for (Movimiento movimiento : listaMovs) {
            if (movimiento.getCuenta().getCliente().getUsuario().getIdUsuario().equals(usuario)) {
                movsFiltrados.add(movimiento);
            }
        }         
        return movsFiltrados;
	}
}
