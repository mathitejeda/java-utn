package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Movimiento;
import entidad.Prestamo;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletCuenta
 */
@WebServlet("/ServletInformes")
public class ServletInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletInformes() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("ListarMovimientosPorMes") != null) {
			ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
			MovimientoNegocio negocio = new MovimientoNegocioImpl();

			try {
				movimientos = negocio.listarMovimientosPorMes(0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int cantTotal = negocio.totalMovimientos(movimientos);

			request.setAttribute("movimientosPorMes", movimientos);
			request.setAttribute("cantTotal", cantTotal);

			RequestDispatcher rd = request.getRequestDispatcher("/InformeMovimientosPeriodo.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("ListarPrestamosPorMes") != null) {
			ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
			PrestamoNegocio negocio = new PrestamoNegocioImpl();

			try {
				prestamos = negocio.listarTodosPorMes(0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			BigDecimal montoTotal = negocio.totalMonto(prestamos);

			request.setAttribute("prestamosPorMes", prestamos);
			request.setAttribute("montoTotal", montoTotal);

			RequestDispatcher rd = request.getRequestDispatcher("/InformePrestamosPeriodo.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("btnFiltrarPrest") != null) {
			
			int estadoFiltro = Integer.parseInt(request.getParameter("estado"));
			String usuarioFiltro = request.getParameter("usuarioFiltro").length() == 0 ? null : request.getParameter("usuarioFiltro");
			
			ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
			PrestamoNegocio negocio = new PrestamoNegocioImpl();
			
			try 
			{
				PrestamoNegocio negocioPres = new PrestamoNegocioImpl();
				prestamos = negocioPres.listarTodosPorMes(estadoFiltro, usuarioFiltro);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			BigDecimal montoTotal = negocio.totalMonto(prestamos);
			
			request.setAttribute("prestamosPorMes", prestamos);
			request.setAttribute("montoTotal", montoTotal);

			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/InformePrestamosPeriodo.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnFiltrarMovs") != null) {
			
			int tipoMov = Integer.parseInt(request.getParameter("tipoMov"));
			String usuarioFiltro = request.getParameter("usuarioFiltro").length() == 0 ? null : request.getParameter("usuarioFiltro");
			
			ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
			MovimientoNegocio negocio = new MovimientoNegocioImpl();
			
			try 
			{
				movimientos = negocio.listarMovimientosPorMes(tipoMov, usuarioFiltro);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			int cantTotal = negocio.totalMovimientos(movimientos);
			
			request.setAttribute("movimientosPorMes", movimientos);
			request.setAttribute("cantTotal", cantTotal);

			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/InformeMovimientosPeriodo.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
