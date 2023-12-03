package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.PagoPrestamo;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.PagoPrestamoNegocio;
import negocio.PrestamoNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PagoPrestamoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPrestamos() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("Session_us");
		if (request.getParameter("op") != null) {
			RequestDispatcher rd;
			switch (request.getParameter("op")) {
			case "mp": {
				PrestamoNegocio pretamoNegocio = new PrestamoNegocioImpl();
				ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) pretamoNegocio.listarAprobadosPorUsuario(user);
				request.setAttribute("prestamos", prestamos);
				rd = request.getRequestDispatcher("/MisPrestamos.jsp");
				rd.forward(request, response);
				break;
			}
			case "l": {
				PrestamoNegocio pretamoNegocio = new PrestamoNegocioImpl();
				ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) pretamoNegocio.listarTodos();

				ArrayList<Prestamo> prestamosFinal = new ArrayList<Prestamo>();
				
				if(prestamos!=null) 
				{
					for(Prestamo prest :  prestamos)
					{
						if (prest.isPendiente()==false)
						{
							prestamosFinal.add(prest);
						}
					}
				}
				
				request.setAttribute("prestamos", prestamosFinal);
				rd = request.getRequestDispatcher("/ListarPrestamos.jsp");
				rd.forward(request, response);
				break;
			}
			case "p": {
				PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
				PagoPrestamoNegocio pagoPrestamoNegocio = new PagoPrestamoNegocioImpl();

				Prestamo prestamo = prestamoNegocio.getPrestamo(request.getParameter("idPrestamo"));
				CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
				UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
				Usuario usr = usuarioNegocio.ReadUser(user);
				ArrayList<Cuenta> cuentasCte = cuentaNegocio.listarCuentasCliente(usr.getIdUsuario());
				ArrayList<PagoPrestamo> pagos = (ArrayList<PagoPrestamo>) pagoPrestamoNegocio
						.listarPagos(request.getParameter("idPrestamo"));
				BigDecimal montoCuota = prestamo.getMontoCuota();
				BigDecimal plazoPago = BigDecimal.valueOf(prestamo.getPlazoPago());

				BigDecimal totalPago = BigDecimal.valueOf(pagos.size());

				BigDecimal montoTotal = montoCuota.multiply(plazoPago);
				BigDecimal montoRestante = montoTotal.subtract(totalPago.multiply(montoCuota));
				ArrayList<Integer> cuotasAPagar = new ArrayList<Integer>();
				for (int i = 1; i <= prestamo.getPlazoPago(); i++) {
					cuotasAPagar.add(i);
				}
				for (PagoPrestamo pagoPrestamo : pagos) {
					cuotasAPagar.remove((Integer) pagoPrestamo.getNroPago());
				}
				request.setAttribute("cuotasAPagar", cuotasAPagar);
				request.setAttribute("prestamo", prestamo);
				request.setAttribute("cuentasCte", cuentasCte);
				request.setAttribute("montoTotal", montoTotal);
				request.setAttribute("montoRestante", montoRestante);
				request.setAttribute("montoCuota", montoCuota);

				if (request.getParameter("success") != null) {
					request.setAttribute("success", "Operacion realizada con exito");
				}
				if (request.getParameter("error") != null) {
					if (request.getParameter("error").equals("1")) {
						request.setAttribute("error", "No se pudo realizar la operacion");
					} else if (request.getParameter("error").equals("2")) {
						request.setAttribute("error",
								"La cuenta no posee el saldo suficiente para realizar la operacion");
					}
				}
				rd = request.getRequestDispatcher("/PagarPrestamo.jsp");
				rd.forward(request, response);
				break;
			}
			case "s": {
				PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
				int id = prestamoNegocio.getNextId();
				UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
				Usuario usr = usuarioNegocio.ReadUser(user);
				CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
				ArrayList<Cuenta> cuentasCte = cuentaNegocio.listarCuentasCliente(usr.getIdUsuario());
				request.setAttribute("id", id);
				request.setAttribute("cuentasCte", cuentasCte);
				if (request.getParameter("success") != null) {
					request.setAttribute("success", "Se ha solicitado el prestamo correctamente");
				}
				if (request.getParameter("error") != null) {
					request.setAttribute("error", "No se ha podido solicitar el prestamo");
				}
				rd = request.getRequestDispatcher("/SolicitudDePrestamo.jsp");
				rd.forward(request, response);
				break;
			}
			case "ap": {
				PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
				ArrayList<Prestamo> prestamosPend = (ArrayList<Prestamo>) prestamoNegocio.listarPendAprobacion();
				ArrayList<Prestamo> prestamosPendFinal = new ArrayList<Prestamo>();
				
				if(prestamosPend!=null) 
				{
					for(Prestamo prest :  prestamosPend)
					{
						if (prest.isPendiente()==true)
						{
							prestamosPendFinal.add(prest);
						}
					}
				}
				
				CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
				ArrayList<Cuenta> cuentasCte = cuentaNegocio.listarCuentasCliente(user);
				request.setAttribute("cuentasCte", cuentasCte);
				request.setAttribute("prestamosPend", prestamosPendFinal);
				if (request.getParameter("success") != null) {
					request.setAttribute("success", "Operacion realizada con exito");
				}
				if (request.getParameter("error") != null) {
					request.setAttribute("error", "No se pudo realizar la operacion");
				}
				rd = request.getRequestDispatcher("/AutorizarPrestamos.jsp");
				rd.forward(request, response);
				break;
			}
			case "dsol": {
				PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
				Prestamo prestamo = prestamoNegocio.getPrestamo(request.getParameter("idPrestamo"));
				CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
				Cuenta cuentaSolicitud = cuentaNegocio.leerCuenta(prestamo.getCbu());
				request.setAttribute("prestamo", prestamo);
				request.setAttribute("cuentaSolicitud", cuentaSolicitud);
				rd = request.getRequestDispatcher("/DetalleSolicitudPrestamo.jsp");
				rd.forward(request, response);
				break;
			}
			default: {
				System.out.println("404");
				break;
			}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (request.getParameter("btnfiltrar") != null) {

			Prestamo prestamo = new Prestamo();
			prestamo.setCbu(request.getParameter("cbuFil"));
			
			System.out.println("Fecha sin cargar :" + request.getParameter("FechaSolicitud"));
			if(request.getParameter("FechaSolicitud") != "") {
				 	String str=request.getParameter("FechaSolicitud");  
			        Date date=Date.valueOf(str);
					prestamo.setFechaSolicitud(date);
					System.out.println("tiene info:");
			} 
	       
			
			String operador = "";
			if (request.getParameter("opFil").equals("1")) {
				operador = "=";
			} else if (request.getParameter("opFil").equals("2")) {
				operador = ">";
			} else if (request.getParameter("opFil").equals("3")) {
				operador = "<";
			}
			
			
			BigDecimal saldo;
			if(request.getParameter("saldoFil").length() != 0) {
				saldo = new BigDecimal(request.getParameter("saldoFil"));
			} else {
				saldo = BigDecimal.ZERO;
			}			
			prestamo.setMontoTotal(saldo);
			
			switch (Integer.parseInt(request.getParameter("estadofiltro"))) {
			case 1:
				prestamo.setAprobado(true);
				break;
			case 2:
				prestamo.setAprobado(false);
				break;
			}
			
			System.out.println("Fecha: " + prestamo.getFechaSolicitud() +  "cbuget: " + prestamo.getCbu() + "operador: " + operador + "importe:  " + prestamo.getMontoTotal() + "estado: " + prestamo.isAprobado());
			
			PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
			ArrayList<Prestamo> ListaPrestamo = new ArrayList<Prestamo>();
			try {
				ListaPrestamo = prestamoNegocio.listarPrestamosFiltro(prestamo, operador);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("prestamos", ListaPrestamo);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");
			rd.forward(request, response);

		}

		if (request.getParameter("btnElimfiltro") != null) {
			PrestamoNegocio pretamoNegocio = new PrestamoNegocioImpl();
			ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) pretamoNegocio.listarTodos();
			request.setAttribute("prestamos", prestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");
			rd.forward(request, response);
		}
		
		
		
		if (request.getParameter("btnSolicitarPrestamo") != null) {
			PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
			UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

			Usuario usr = usuarioNegocio.ReadUser((String) session.getAttribute("Session_us"));
			Prestamo prestamo = new Prestamo();
			Cliente cte = new Cliente();
			cte.setUsuario(usr);
			prestamo.setCliente(cte);
			prestamo.setCbu(request.getParameter("Cuenta"));
			BigDecimal monto = new BigDecimal(request.getParameter("MontoPrestamo"));
			prestamo.setMontoTotal(monto);
			int cuotas = Integer.parseInt(request.getParameter("Cuotas"));
			prestamo.setPlazoPago(cuotas);
			BigDecimal interesDecimal = new BigDecimal(request.getParameter("interes")).divide(new BigDecimal(100))
					.add(BigDecimal.ONE);
			BigDecimal montoCuota = monto.multiply(interesDecimal).divide(new BigDecimal(cuotas), 2,
					RoundingMode.HALF_UP);
			prestamo.setMontoCuota(montoCuota);
			prestamo.setFechaSolicitud(new java.sql.Date(new java.util.Date().getTime()));
			prestamo.setAprobado(false);
			prestamo.setPendiente(true);
			boolean agregado = prestamoNegocio.agregarPrestamo(prestamo);

			response.sendRedirect(request.getContextPath()
					+ String.format("/ServletPrestamos?op=s&" + (agregado ? "success=1" : "error=1")));
		}
        if (request.getParameter("btnAprobarPrestamo") != null) {
            PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
            Prestamo prestamo = prestamoNegocio.getPrestamo(request.getParameter("idPrestamo"));
            CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
            MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
            Cuenta cuentaSolicitud = cuentaNegocio.leerCuenta(prestamo.getCbu());
 
            BigDecimal saldo = cuentaSolicitud.getSaldo().add(prestamo.getMontoTotal());
 
            prestamo.setAprobado(true);
            //prestamo.setPendiente(false);
            cuentaSolicitud.setSaldo(saldo);
            Movimiento movimiento = new Movimiento();
            movimiento.setConcepto("Aprobacion de prestamo");
            movimiento.setCuenta(cuentaSolicitud);
            movimiento.setFechaOperacion(new java.sql.Date(new java.util.Date().getTime()));
            movimiento.setImporte(saldo);
            movimiento.setTipoMovimiento(2);
            boolean aprobado = prestamoNegocio.modificarPrestamo(prestamo)
                    && cuentaNegocio.modificarCuenta(cuentaSolicitud) && movimientoNegocio.insert(movimiento);
            response.sendRedirect(request.getContextPath()
                    + String.format("/ServletPrestamos?op=ap&" + (aprobado ? "success=1" : "error=1")));
        }

		if (request.getParameter("btnRechazarPrestamo") != null) {
			PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
			Prestamo prestamo = prestamoNegocio.getPrestamo(request.getParameter("idPrestamo"));
			System.out.println(prestamo);
			prestamo.setAprobado(false);
            prestamo.setPendiente(false);
			boolean rechazado = prestamoNegocio.rechazarPrestamo(request.getParameter("idPrestamo"));
			response.sendRedirect(request.getContextPath()
					+ String.format("/ServletPrestamos?op=ap&" + (rechazado ? "success=1" : "error=1")));
		}
		
        if (request.getParameter("btnPagarPrestamo") != null) {
            PagoPrestamoNegocio pagoPrestamoNegocio = new PagoPrestamoNegocioImpl();
            CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
            Cuenta cuentaSolicitud = cuentaNegocio.leerCuenta(request.getParameter("Cuenta"));
            MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
 
            String res;
            BigDecimal montoCuota = BigDecimal.valueOf(Double.parseDouble(request.getParameter("montoCuota")));
 
            if (cuentaSolicitud.getSaldo().compareTo(montoCuota) < 0) {
                res = String
                        .format("/ServletPrestamos?op=p&idPrestamo=" + request.getParameter("idPrestamo") + "&error=2");
                response.sendRedirect(request.getContextPath() + res);
            } else {
                BigDecimal saldo = cuentaSolicitud.getSaldo().subtract(montoCuota);
                cuentaSolicitud.setSaldo(saldo);
                Movimiento movimiento = new Movimiento();
                movimiento.setConcepto("Pago de prestamo");
                movimiento.setCuenta(cuentaSolicitud);
                movimiento.setFechaOperacion(new java.sql.Date(new java.util.Date().getTime()));
                movimiento.setImporte(montoCuota);
                movimiento.setTipoMovimiento(3);
                boolean pagado = pagoPrestamoNegocio.agregarPagoPrestamo(request.getParameter("idPrestamo"),
                        Integer.parseInt(request.getParameter("Cuotas")))
                        && cuentaNegocio.modificarCuenta(cuentaSolicitud) && movimientoNegocio.insert(movimiento);
                response.sendRedirect(request.getContextPath() + String.format("/ServletPrestamos?op=p&idPrestamo="
                        + request.getParameter("idPrestamo") + (pagado ? "&success=1" : "&error=1")));
            }
        }
	}
}
