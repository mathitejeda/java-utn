package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidad.Prestamo;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		boolean estado = prestamoDao.agregarPrestamo(prestamo);
		return estado;
	}

	@Override
	public List<Prestamo> listarPrestamosUsuario(String idUsuario) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.listarPrestamosUsuario(idUsuario);
	}

	@Override
	public List<Prestamo> listarTodos() {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.listarTodos();
	}

	@Override
	public boolean modificarPrestamo(Prestamo prestamo) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		boolean estado = prestamoDao.modificarPrestamo(prestamo);
		return estado;
	}

	@Override
	public boolean rechazarPrestamo(String idPrestamo) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		boolean estado = prestamoDao.rechazarPrestamo(idPrestamo);
		return estado;
	}

	@Override
	public int getNextId() {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.getNextId();
	}

	@Override
	public List<Prestamo> listarAprobadosPorUsuario(String idUsuario) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.listarAprobadosPorUsuario(idUsuario);
	}

	@Override
	public List<Prestamo> listarPendAprobacion() {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.listarTodosEstado(false);
	}

	@Override
	public Prestamo getPrestamo(String idPrestamo) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.getPrestamo(idPrestamo);
	}
	
	@Override
	public ArrayList<Prestamo> listarTodosPorMes(int estado, String usuario) {
		ArrayList<Prestamo> listFinalTotalPorMes = new ArrayList<>();		
		ArrayList<Prestamo> listPres = listarPrestamosEstado(estado);

		if(listPres != null) {
			if(usuario != null) { 
				listPres = filtrarPrestamoUsuario(listPres, usuario); 
			}
			Map<String, ArrayList<Prestamo>> mapListPresPorMes = mapListPresPorMes(listPres);
			listFinalTotalPorMes = processMap(mapListPresPorMes, usuario);
		}
		
		Collections.sort(listFinalTotalPorMes, Comparator.comparing(Prestamo::getFechaSolicitud));
		return listFinalTotalPorMes;
	}
	
	@Override
	public BigDecimal totalMonto(ArrayList<Prestamo> listPrestamos) {
		BigDecimal totalMovs = BigDecimal.ZERO;
		
		for(Prestamo prestamo : listPrestamos) {
			totalMovs = totalMovs.add(prestamo.getMontoTotal());
		}
		return totalMovs;
	}
	
	private ArrayList<Prestamo> listarPrestamosEstado(int estado) {
		PrestamoDao daoPres = new PrestamoDaoImpl();
		ArrayList<Prestamo> prestamosList = new ArrayList<>();
	
		switch (estado) {
		case 1:
			prestamosList = daoPres.listarTodosEstado(true);
			
			break;
		case 2:
			prestamosList = daoPres.listarTodosEstado(false);
			break;
		default:
			prestamosList = daoPres.listarTodos();
		} 	

		return prestamosList;
	}	
	
	private ArrayList<Prestamo> filtrarPrestamoUsuario(ArrayList<Prestamo> listaPrestamos, String usuario) {		
		ArrayList<Prestamo> prestamosFiltrados = new ArrayList<>();

        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getCliente().getUsuario().getIdUsuario().equals(usuario)) {
                prestamosFiltrados.add(prestamo);
            }
        }         
        return prestamosFiltrados;
	}

	private Map<String, ArrayList<Prestamo>> mapListPresPorMes(ArrayList<Prestamo> listPresDao){
		Map<String, ArrayList<Prestamo>> mappedList = new HashMap<>();
		System.out.println(listPresDao);
		for(Prestamo prestamoDao : listPresDao){
			String mes = prestamoDao.getMesAño();
			mappedList.computeIfAbsent(mes, movs -> new ArrayList<>()).add(prestamoDao);
		}
		return mappedList;
	}
	
	private ArrayList<Prestamo> processMap(Map<String, ArrayList<Prestamo>> mapListPresPorMes, String usuario) {
		ArrayList<Prestamo> listFinalTotalPorMes = new ArrayList<>();	
		
		for (Map.Entry<String, ArrayList<Prestamo>> mapEntryListPresPorMes : mapListPresPorMes.entrySet()) {
			Prestamo presTotalPorMes = new Prestamo();
		    StringBuilder sb = new StringBuilder();
		    
		    ArrayList<Prestamo> listPresPorMes = mapEntryListPresPorMes.getValue();
		    
		    java.sql.Date fecha = java.sql.Date.valueOf(sb.append(mapEntryListPresPorMes.getKey()).append("-01").toString());				
		    int año = Integer.parseInt(mapEntryListPresPorMes.getKey().substring(0,4));				
		    BigDecimal montoTotal = BigDecimal.ZERO;

		    for(Prestamo pres : listPresPorMes) {	
		    	presTotalPorMes.setFechaSolicitud(fecha);
		    	presTotalPorMes.getFechaSolicitud().setYear(año);
		    	if(usuario != null) { presTotalPorMes.setCliente(pres.getCliente()); }
		    	else { presTotalPorMes.setCliente(null); }
		    	
		    	montoTotal = montoTotal.add(pres.getMontoTotal());
		    }
		    presTotalPorMes.setMontoTotal(montoTotal);
		    listFinalTotalPorMes.add(presTotalPorMes);            
		}
		return listFinalTotalPorMes;
	}
	
	@Override
	public ArrayList<Prestamo> listarPrestamosFiltro(Prestamo prestamo, String operador) {
		PrestamoDao prestamoDao = new PrestamoDaoImpl();
		return prestamoDao.listarPrestamosFiltro(prestamo, operador);
	}
}
