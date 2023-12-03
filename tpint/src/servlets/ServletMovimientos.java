package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Movimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletMovimientos() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("MovimientoCuenta") != null) {
			String cbu = request.getParameter("MovimientoCuenta");
			ArrayList<Movimiento> movimientos = null;
			
			CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
			MovimientoNegocio negocio = new MovimientoNegocioImpl();
			
			movimientos = negocio.listarMovimientosCuenta(cuentaNeg.leerCuenta(cbu));
			
			for(Movimiento movimiento : movimientos)
				System.out.println(movimiento.getIdMovimiento());
			
			request.setAttribute("movimientos", movimientos);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarMovimientos.jsp");
			rd.forward(request, response);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
