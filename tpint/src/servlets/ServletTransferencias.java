package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;


@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletTransferencias() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Transfer")!=null) {
			
			PasarCuentas_a_Session(request);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaCBU.jsp");
			rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("buscarCbu")!=null) {
			Cuenta destino = null;
			
			CuentaNegocio negocio = new CuentaNegocioImpl();
			try {
				destino = negocio.leerCuenta(request.getParameter("CBU"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(destino != null) {
				request.setAttribute("cuentaDestino", destino);
				RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaCBU.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("success", "El CBU ingresado no es válido, por favor ingrese un CBU válido");
				RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaCBU.jsp");
				rd.forward(request, response);
			}
		}
		if(request.getParameter("montoTransfer")!=null) {
			Cuenta cuentaOrigen = null;
			Cuenta cuentaDestino = null;
			CuentaNegocio negocio = new CuentaNegocioImpl();
			try {
				cuentaOrigen = negocio.leerCuenta(request.getParameter("cuenta"));
				cuentaDestino = negocio.leerCuenta(request.getParameter("CBUDestino"));
				if(cuentaOrigen.getSaldo().compareTo(new BigDecimal(request.getParameter("Monto"))) >= 0) {
					
					cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(new BigDecimal(request.getParameter("Monto"))));
					cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(new BigDecimal(request.getParameter("Monto"))));
					
					if(Transferir(cuentaOrigen, cuentaDestino, negocio, new BigDecimal( request.getParameter("Monto")) ) )
						request.setAttribute("success", "Transferencia exitosa");
					else
						request.setAttribute("success", "Error en la transferencia");
					
					PasarCuentas_a_Session(request);
					RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaCBU.jsp");
					rd.forward(request, response);
				}
				else {
					request.setAttribute("success", "No posee suficiente saldo para realizar esta transferencia");
					RequestDispatcher rd = request.getRequestDispatcher("/TransferenciaCBU.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void PasarCuentas_a_Session(HttpServletRequest request){
		ArrayList<Cuenta> cuentasCliente = null;
		
		CuentaNegocio negocio = new CuentaNegocioImpl();
		
		HttpSession session = request.getSession();
		
		try {
			if(session.getAttribute("Session_us")!=null) {
				String usuario = (String)session.getAttribute("Session_us");
				
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
	
	private boolean Transferir(Cuenta origen, Cuenta destino, CuentaNegocio negocio, BigDecimal monto) {
		
		boolean transExitosa = false;
		
		Movimiento envio = new Movimiento();
		
		envio.setConcepto("Transferencia realizada a CBU: " + destino.getCbu());
		envio.setCuenta(origen);
		envio.setImporte(monto.negate());
		envio.setTipoMovimiento(TipoMovimiento.TRANSFERENCIA);
		
		java.util.Date utilDate =  new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		envio.setFechaOperacion(sqlDate);
		
		
		if(negocio.transferencia(origen, envio)) {
			Movimiento deposito = new Movimiento();
			
			deposito.setConcepto("Transferencia recibida de CBU: " + origen.getCbu());
			deposito.setCuenta(destino);
			deposito.setImporte(monto);
			deposito.setTipoMovimiento(TipoMovimiento.TRANSFERENCIA);
			
			deposito.setFechaOperacion(sqlDate);
			
			if(negocio.transferencia(destino, deposito)) {
				transExitosa = true;
				return transExitosa;
			}
		}
		return transExitosa;
	}

}
