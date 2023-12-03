package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Usuario;
import excepciones.PasswordException;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("CerrarSession")!= null) {
			request.getSession().removeAttribute("Session_us");
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}	

		if(request.getParameter("AgregarUsuario")!= null) {

			UsuarioNegocio negocio = new UsuarioNegocioImpl();

			Usuario usAdmin = new Usuario();
			Boolean esAdmin = false;
			String session = request.getSession().getAttribute("Session_us").toString();			
			
			usAdmin=negocio.ReadUser(session);
			
			if(usAdmin.getAdmin()==true) { esAdmin=true; } else { esAdmin=false; } 
			
			request.setAttribute("esAdmin", esAdmin);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
			rd.forward(request, response);
		}
					
		if(request.getParameter("ListarClientes")!= null) {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				ClienteNegocio negocio = new ClienteNegocioImpl();
				clientes = negocio.listarClientes();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("clientes", clientes);;	
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnRegistrarUsuario") != null) {
			Usuario usuario = new Usuario();
			UsuarioNegocio negocio = new UsuarioNegocioImpl();
			
			usuario.setIdUsuario(request.getParameter("txtUsuario"));
			usuario.setPass(request.getParameter("txtPass"));
			String txtPass2 = request.getParameter("txtPass2");
			usuario.setAdmin(Boolean.parseBoolean(request.getParameter("TipoUsuario")));
			usuario.setEstado(Boolean.parseBoolean(request.getParameter("Estado")));
			
			try 
			{
				negocio.insert(usuario, txtPass2);
				request.setAttribute("success", "Usuario agregado correctamente");
			}
			catch(PasswordException e) {
				e.printStackTrace();
				request.setAttribute("error", "Las claves no coinciden");
			}
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
			rd.forward(request, response);
		}		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnIngresar")!=null)
		{			
			RequestDispatcher rd;
			HttpSession session = request.getSession();			
		
			if(request.getParameter("txtUsuario")!=null)
			{
				String usuario=request.getParameter("txtUsuario");
				session.setAttribute("Session_us", usuario);
			}
			
			Usuario user = new Usuario();
			UsuarioNegocio negocio = new UsuarioNegocioImpl();
			
			user=negocio.ReadUser(request.getParameter("txtUsuario"));
			
			if(user == null) {
				request.setAttribute("error", "El Usuario ingresado no es correcto");	
				rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			} else {
				boolean esAdmin = false;
				esAdmin=user.getAdmin();
				String id=user.getIdUsuario();
				String pass=user.getPass();
				String identrante=request.getParameter("txtUsuario");
				String passentrante=request.getParameter("txContraseña");
				
				if(id.equals(identrante) && pass.equals(passentrante)) {				
					if(esAdmin == true) {
						rd = request.getRequestDispatcher("/HomeAdmin.jsp");
						rd.forward(request, response);
					} else {
						rd = request.getRequestDispatcher("/HomeCliente.jsp");
						rd.forward(request, response);
					}					
				} else {
					request.setAttribute("error", "Contraseña incorrecta");	
					rd = request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}
			}
		}
	}
	
}

