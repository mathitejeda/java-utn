package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguro;
import dominio.TipoSeguroDao;

/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int proxID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletSeguro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("op") != null) {
			RequestDispatcher rd;
			switch (request.getParameter("op")) {
				case "a":
					updateProxID();
					TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();
					ArrayList<TipoSeguro> listaTipoSeguros = tipoSeguroDao.obtenerTipoSeguros();
					request.setAttribute("listaTipoSeguros", listaTipoSeguros);
					request.setAttribute("proxID", proxID);
					if (request.getParameter("success") != null) {
						request.setAttribute("success", "Seguro agregado correctamente");
					} else if (request.getParameter("error") != null) {
						request.setAttribute("error", "Error al agregar seguro");
					}

					rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
					rd.forward(request, response);
					break;
				case "l":
					SeguroDao sDao = new SeguroDao();
					ArrayList<Seguro> lista= sDao.obtenerSeguros();
					
					request.setAttribute("listaS", lista);		
					
					rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
			        rd.forward(request, response);
					break;
				default:
					break;
			}
		}
		
		if(request.getParameter("btnFiltrar")!=null) {
			RequestDispatcher rd;
			SeguroDao seguroDao = new SeguroDao();			
			ArrayList<Seguro> listaSeguro = seguroDao.obtenerSeguros_X_Tipo(Integer.parseInt(request.getParameter("cbSeguro1")));
			
			request.setAttribute("listaS", listaSeguro);
			
			rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnAceptar") != null) {
			if (!request.getParameter("txtCostoContratacion").matches("[0-9]+(\\.[0-9]+)?") || !request.getParameter("txtCostoMax").matches("[0-9]+(\\.[0-9]+)?")) {
				response.sendRedirect(request.getContextPath() + "/servletSeguro?op=a&error=1");
			} else {
				Seguro seguro = new Seguro();
				TipoSeguro tipoSeguro = new TipoSeguro();
				SeguroDao seguroDao = new SeguroDao();
				seguro.setDescripcion(request.getParameter("txtDescripcion"));
				tipoSeguro.setIdTipo(Integer.parseInt(request.getParameter("tipoSeguro")));
				seguro.setTipoSeguro(tipoSeguro);
				seguro.setCostoContratacion(Float.parseFloat(request.getParameter("txtCostoContratacion")));
				seguro.setCostoAsegurado(Float.parseFloat(request.getParameter("txtCostoMax")));
				seguroDao.agregarSeguro(seguro);

				response.sendRedirect(request.getContextPath() + "/servletSeguro?op=a&success=1");
			}
		}
	}
	
	void updateProxID() {
		SeguroDao seguroDao = new SeguroDao();
		proxID = seguroDao.getNextID();
	}

}
