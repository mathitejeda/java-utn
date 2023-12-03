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
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletCuenta
 */
@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("MisCuentas") != null) {
			PasarCuentas_a_Session(request);

			RequestDispatcher rd = request.getRequestDispatcher("/MisCuentas.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("AgregarCuentas") != null) {
			ClienteNegocio negocioCli = new ClienteNegocioImpl();
			ArrayList<String> users = negocioCli.obtenerClientesDisponibles();

			request.setAttribute("usuariosClientes", users);

			CuentaNegocio cd = new CuentaNegocioImpl();
			String nextCBU = cd.getNextCBU();

			request.setAttribute("nextCBU", nextCBU);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);

		}

		if (request.getParameter("ListarCuentas") != null) {
			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

			try {
				CuentaNegocio negocio = new CuentaNegocioImpl();
				cuentas = negocio.listarCuentas();
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuentas.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("btnfiltrar") != null) {
			
			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
			Cuenta cuenta = new Cuenta();

			cuenta.setNroCuenta(request.getParameter("nrocuentaFil").length() == 0 ? 0 : Integer.parseInt(request.getParameter("nrocuentaFil")));
			cuenta.setCbu(request.getParameter("cbuFil"));
			cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("TipoCuentaFil")));

			switch (Integer.parseInt(request.getParameter("estadofiltro"))) {
			case 1:
				cuenta.setEstado(true);
				break;
			case 2:
				cuenta.setEstado(false);
				break;
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
			cuenta.setSaldo(saldo);

			try {
				CuentaNegocio negocio = new CuentaNegocioImpl();
				
				cuentas = negocio.listarCuentasFiltro(cuenta, operador);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuentas.jsp");
			rd.forward(request, response);

		}

		if (request.getParameter("btnElimfiltro") != null) {
			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

			try {
				CuentaNegocio negocio = new CuentaNegocioImpl();
				cuentas = negocio.listarCuentas();
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuentas.jsp");
			rd.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnRegistrarCuenta") != null) {
			CuentaNegocio neg = new CuentaNegocioImpl();
			Usuario user = new Usuario();
			Cliente cli = new Cliente();
			Cuenta cuenta = new Cuenta();
			user.setIdUsuario(request.getParameter("usuario"));
			cli.setUsuario(user);
			cuenta.setCliente(cli);
			String cbu = request.getParameter("nextCBU");

			cuenta.setCbu(cbu);
			cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("TipoCuenta")));
			cuenta.setNroCuenta(Integer.parseInt(request.getParameter("txtcuenta")));
			cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("TipoCuenta")));

			BigDecimal saldo = new BigDecimal(request.getParameter("Saldo"));
			cuenta.setSaldo(saldo);

			neg.agregarCuenta(cuenta);
			
			
			ClienteNegocio negocioCli = new ClienteNegocioImpl();
			ArrayList<String> users = negocioCli.obtenerClientesDisponibles();

			request.setAttribute("usuariosClientes", users);

			CuentaNegocio cd = new CuentaNegocioImpl();
			String nextCBU = cd.getNextCBU();

			request.setAttribute("nextCBU", nextCBU);

			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);

		}

		if (request.getParameter("btnIrModificarCuenta") != null) {
			Cuenta cuenta = new Cuenta();

			try {
				CuentaNegocio negocio = new CuentaNegocioImpl();
				String cbu = request.getParameter("cbu");
				request.setAttribute("cbu", cbu);

				cuenta = negocio.leerCuenta(cbu);
				request.setAttribute("cuenta", cuenta);
			} catch (Exception e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/ModificarCuenta.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("btnModificarCuenta") != null) {
			Cuenta cuenta = new Cuenta();

			cuenta.setCbu(request.getParameter("cbu"));
			cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("IdTipoCuenta")));
			BigDecimal saldo = new BigDecimal(request.getParameter("Saldo"));
			cuenta.setSaldo(saldo);

			if (Integer.parseInt(request.getParameter("Estado")) == 1) {
				cuenta.setEstado(true);
			} else {
				cuenta.setEstado(false);
			}

			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

			try {
				CuentaNegocio negocio = new CuentaNegocioImpl();
				negocio.modificarCuenta(cuenta);
				cuentas = negocio.listarCuentas();
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuentas.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("btnEliminarCuenta") != null) {
			Boolean estado = false;
			String CBU = request.getParameter("cbuvalor");

			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

			try {
				CuentaNegocio cuNeg = new CuentaNegocioImpl();
				estado = cuNeg.eliminarCuenta(CBU);
				cuentas = cuNeg.listarCuentas();

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (estado == true) {
				request.setAttribute("success", "La cuenta se desactivo correctamente");
			} else {
				request.setAttribute("error", "La cuenta no pudo ser eliminada");
			}

			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuentas.jsp");
			rd.forward(request, response);
		}

	}

	private void PasarCuentas_a_Session(HttpServletRequest request) {
		ArrayList<Cuenta> cuentasCliente = null;

		CuentaNegocio negocio = new CuentaNegocioImpl();

		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("Session_us") != null) {
				String usuario = (String) session.getAttribute("Session_us");

				Usuario user = new Usuario();
				UsuarioNegocio userNegocio = new UsuarioNegocioImpl();

				user = userNegocio.ReadUser(usuario);
				cuentasCliente = negocio.listarCuentasCliente(user.getIdUsuario());
				session.setAttribute("cuentasCliente", cuentasCliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
