package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Cliente;
import entidad.Usuario;
import excepciones.ClienteRepetidoException;
import excepciones.PasswordException;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if(request.getParameter("InfoCliente")!= null) {

			ClienteNegocio negocioCli = new ClienteNegocioImpl();
			Cliente cliente = new Cliente();
			
			String session = request.getSession().getAttribute("Session_us").toString();

			cliente=negocioCli.ObtenerCliente(session);
			
			if(cliente != null) {
				request.setAttribute("usuario", cliente.getUsuario().getIdUsuario());
				request.setAttribute("dni", cliente.getDni());
				request.setAttribute("cuil", cliente.getCuil());
				request.setAttribute("nombre", cliente.getNombre());
				request.setAttribute("apellido", cliente.getApellido());
				request.setAttribute("sexo", cliente.getSexo());
				request.setAttribute("nacionalidad", cliente.getNacionalidad());
				request.setAttribute("fechanac", cliente.getFechaNacimiento());
				request.setAttribute("direccion", cliente.getDireccion());
				request.setAttribute("localidad", cliente.getLocalidad());
				request.setAttribute("provincia", cliente.getProvincia());
				request.setAttribute("correo", cliente.getEmail());
				request.setAttribute("telefono", cliente.getTelefono());
			} else {
				request.setAttribute("noExiste", true);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/InfoCliente.jsp");
			rd.forward(request, response);
			}
		
		
		if(request.getParameter("AgregarClientes")!= null) {
			
			UsuarioNegocio negocioUs = new UsuarioNegocioImpl();
			ClienteNegocio negocioCli = new ClienteNegocioImpl();
			Boolean existe = false;
			
			ArrayList<Usuario> listaUsuarios = negocioUs.obtenerUsuarios();
			ArrayList<Usuario> listaUsuariosFinal = new ArrayList<Usuario>();
			
			if(listaUsuarios!=null) {
				for(Usuario user : listaUsuarios){
					existe = negocioCli.leerCliente(user.getIdUsuario().toString());
					if (existe == false && user.getAdmin()==false ) {	
						listaUsuariosFinal.add(user); 
						}
				}
			}
			request.setAttribute("listaFinal", listaUsuariosFinal);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarClientes.jsp");
			rd.forward(request, response);
			}
		
			if(request.getParameter("btnfiltrar")!= null) {
			Cliente cliente = new Cliente();
			
			cliente.setDni(request.getParameter("dniFil"));
			cliente.getUsuario().setIdUsuario(request.getParameter("usuarioFill"));
			cliente.setNombre(request.getParameter("nombreFil"));
			
			switch(Integer.parseInt(request.getParameter("sexoFil"))) {
			case 1:
				cliente.setSexo("Femenino");
				break;
			case 2:
				cliente.setSexo("Masculino");
				break;
			default:
				cliente.setSexo(null);
				break;
			}
			
			switch(Integer.parseInt(request.getParameter("estadofiltro"))) 
			{
			case 1:
				cliente.setEstado(true);
				break;
			case 2:
				cliente.setEstado(false);
				break;
			}
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				ClienteNegocio negocio = new ClienteNegocioImpl();
				clientes = negocio.listarClientesFiltro(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("clientes", clientes);;	
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
					
			}
			
			
			if(request.getParameter("btnElimfiltro")!= null) {
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


	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 if(request.getParameter("btnRegistrarCliente")!=null)
			{
			 ClienteNegocio negocio = new ClienteNegocioImpl();
				
				String dni = request.getParameter("DNI");
				Boolean existe = false;
				
				try {

					existe=validarClienteExiste(dni);

					Cliente cliente = getCliente(request);
					
					if(existe==false && negocio.agregarCliente(cliente)) {
						
						request.setAttribute("success", "El cliente se agreg� correctamente");
						
						}
				}

				catch(ClienteRepetidoException e)
				{
					request.setAttribute("error", "El cliente ya se encuentra registrado!");
					e.printStackTrace();
				}
				 catch (ParseException e) {
					e.printStackTrace(); }
				 catch (IllegalStateException e) {
					e.printStackTrace(); }

				UsuarioNegocio negocioUs = new UsuarioNegocioImpl();
				ClienteNegocio negocioCli = new ClienteNegocioImpl();
				Boolean existe2 = false;
				
				ArrayList<Usuario> listaUsuarios = negocioUs.obtenerUsuarios();
				ArrayList<Usuario> listaUsuariosFinal = new ArrayList<Usuario>();
				
				if(listaUsuarios!=null) {
					for(Usuario user : listaUsuarios){
						existe2 = negocioCli.leerCliente(user.getIdUsuario().toString());
						if (existe2 == false  && user.getAdmin()==false ) 
						{	listaUsuariosFinal.add(user); }
					}
				}
				request.setAttribute("listaFinal", listaUsuariosFinal);

						RequestDispatcher rd;
						rd = request.getRequestDispatcher("/AgregarClientes.jsp");
						rd.forward(request, response);
				
			}
		 

			if(request.getParameter("btnEliminarCliente")!=null)
			{	
				Boolean estado = false;
				String idUsuario = request.getParameter("idUsuario");
				
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				try {
					ClienteNegocio negocio = new ClienteNegocioImpl();
					estado=negocio.eliminarCliente(idUsuario);
					clientes = negocio.listarClientes();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				if(estado==true) {
					request.setAttribute("success", "El cliente se desactiv� correctamente");
				} else {
					request.setAttribute("error", "El cliente no pudo ser eliminado");
				}
				
				request.setAttribute("clientes", clientes);;	
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ListarClientes.jsp");
				rd.forward(request, response);
			}
			
			
			if(request.getParameter("btnIrModificarPass")!=null)
			{	
				request.setAttribute("idUsuario", request.getParameter("idUsuario"));;
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ModificarPassword.jsp");
				rd.forward(request, response);
			}
			
			if(request.getParameter("btnModificarPass")!=null)
			{	
				String usuario = request.getParameter("usuario");
				request.setAttribute("idUsuario", usuario);;
				
				String nuevaPass = request.getParameter("txtPass");
				String nuevaPass2 = request.getParameter("txtPass2");
				
				boolean estado=false;
				UsuarioNegocio negocioUs = new UsuarioNegocioImpl();
				
				try 
				{
					estado=negocioUs.modificarClave(usuario, nuevaPass, nuevaPass2);
					if(estado) {
						request.setAttribute("success", "La clave se modifico correctamente");
					}
				}
				catch(PasswordException e) {
					e.printStackTrace();
					request.setAttribute("error", "Las claves no coinciden");
				}
				
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				try {

					ClienteNegocio negocio = new ClienteNegocioImpl();
					clientes = negocio.listarClientes();
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("clientes", clientes);;
				
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ModificarPassword.jsp");
				rd.forward(request, response);
			}
			
			

			
			if(request.getParameter("btnModificar")!=null)
			{	
				request.setAttribute("id", request.getParameter("idUsuario"));;
				request.setAttribute("dni", request.getParameter("dni"));;
				request.setAttribute("cuil", request.getParameter("cuil"));;
				request.setAttribute("nombre", request.getParameter("nombre"));;
				request.setAttribute("apellido", request.getParameter("apellido"));;
				request.setAttribute("sexo", request.getParameter("sexo"));;
				request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));;
				request.setAttribute("fecha", request.getParameter("fecha"));;
				request.setAttribute("direccion", request.getParameter("direccion"));;
				request.setAttribute("localidad", request.getParameter("localidad"));;
				request.setAttribute("provincia", request.getParameter("provincia"));;
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("telefono", request.getParameter("telefono"));;
				request.setAttribute("estado", request.getParameter("estado"));;
				
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ModificarCliente.jsp");
				rd.forward(request, response);
			}
			
			if(request.getParameter("btnModificarCliente")!=null)
			{	

				Cliente cliente = new Cliente();

				String idUsuario = request.getParameter("idUsuario");
				cliente.setDni(request.getParameter("dni"));
				cliente.setCuil(request.getParameter("cuil"));

				cliente.setNombre(request.getParameter("nombre"));
				cliente.setApellido(request.getParameter("apellido"));
				
				switch(Integer.parseInt(request.getParameter("sexo"))) 
				{
				case 1:
					cliente.setSexo("Femenino");
					break;
				case 2:
					cliente.setSexo("Masculino");
					break;
				}

				String Nacionalidad = getNac(Integer.parseInt(request.getParameter("nacionalidad")));                                                          
				cliente.setNacionalidad(Nacionalidad);
				
		        String str=request.getParameter("fecha");  
		        Date date=Date.valueOf(str);
		        cliente.setFechaNacimiento(date); 
				
				
				cliente.setDireccion(request.getParameter("direccion"));
				cliente.setLocalidad(request.getParameter("localidad"));
				cliente.setProvincia(request.getParameter("provincia"));
				cliente.setEmail(request.getParameter("email"));
				cliente.setTelefono(request.getParameter("telefono"));
				switch(Integer.parseInt(request.getParameter("estado"))) 
				{
				case 1:
					cliente.setEstado(true);
					break;
				case 2:
					cliente.setEstado(false);
					break;
				}
				
				Boolean estado = false;
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				try {
					ClienteNegocio negocio = new ClienteNegocioImpl();
					negocio.modificarCliente(cliente, idUsuario);
					estado=true;
					clientes = negocio.listarClientes();
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("clientes", clientes);;
				
				
				if(estado) {
					request.setAttribute("success", "El cliente se modificó correctamente");
				} else {
					request.setAttribute("error", "El cliente no pudo ser modificado");
				}
				
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ListarClientes.jsp");
				rd.forward(request, response);
			}
		 		 
		 
	}
	
	

	public static boolean validarClienteExiste (String dni) throws ClienteRepetidoException
	{

		ClienteNegocio negocio = new ClienteNegocioImpl();
		Boolean auxExiste = false;
		
		auxExiste = negocio.existeCliente(dni);
		   if (auxExiste)
		   {
				   auxExiste=true;
		   }
		   		   
		   if(auxExiste == true)
		   {
			   ClienteRepetidoException exc1  = new ClienteRepetidoException();
			   throw exc1;
			   //el cliente ya esta registrado
		   }
	
		   if(auxExiste)
		   {
			   return true;
		   } 
		   
		   return false;
	}
	
	
	private Cliente getCliente(HttpServletRequest request) throws ParseException {
		Cliente cliente = new Cliente();
		cliente.setDni(request.getParameter("DNI"));
		cliente.getUsuario().setIdUsuario(request.getParameter("usuario"));
		cliente.setCuil(request.getParameter("CUIL"));
		cliente.setNombre(request.getParameter("Nombre"));
		cliente.setApellido(request.getParameter("Apellido"));
		switch(Integer.parseInt(request.getParameter("Sexo"))) {
		case 1:
			cliente.setSexo("Femenino");
			break;
		case 2:
			cliente.setSexo("Masculino");
			break;
		}
		
		String Nacionalidad = getNac(Integer.parseInt(request.getParameter("Nacionalidad")));
		cliente.setNacionalidad(Nacionalidad);
		 
        String str=request.getParameter("FechaNac");  
        Date date=Date.valueOf(str);
        cliente.setFechaNacimiento(date); 
		
		cliente.setDireccion(request.getParameter("Direccion"));
		cliente.setLocalidad(request.getParameter("Localidad"));
		cliente.setProvincia(request.getParameter("Provincia"));
		cliente.setEmail(request.getParameter("Correo"));
		cliente.setTelefono(request.getParameter("Telefono"));
		
		return cliente;
	}
	

	public String getNac(int Nac) {
		String Nacionalidad="";
		
		switch(Nac) {
		case 1:Nacionalidad="Argentina";break;
		case 20:Nacionalidad="Afganistan";break;
		case 30:Nacionalidad="Albania";break;
		case 40:Nacionalidad="Alemania";break;
		case 50:Nacionalidad="Andorra";break;
		case 60:Nacionalidad="Angola";break;
		case 110:Nacionalidad="Arabia Saudita";break;
		case 120:Nacionalidad="Argelia";break;
		case 130:Nacionalidad="Armenia";break;
		case 140:Nacionalidad="Aruba";break;
		case 150:Nacionalidad="Australia";break;
		case 160:Nacionalidad="Austria";break;
		case 200:Nacionalidad="Bangladesh";break;
		case 220:Nacionalidad="Belgica";break;
		case 230:Nacionalidad="Belice";break;
		case 235:Nacionalidad="Bolivia";break;
		case 240:Nacionalidad="Brasil";break;
		case 370:Nacionalidad="Camerun";break;
		case 380:Nacionalidad="Canada";break;
		case 390:Nacionalidad="Chile";break;
		case 410:Nacionalidad="China";break;
		case 430:Nacionalidad="Colombia";break;
		case 460:Nacionalidad="Corea del Norte";break;
		case 470:Nacionalidad="Corea del Sur";break;
		case 510:Nacionalidad="Cuba";break;
		case 530:Nacionalidad="Dinamarca";break;
		case 560:Nacionalidad="Ecuador";break;
		case 570:Nacionalidad="Egipto";break;
		case 580:Nacionalidad="El Salvador";break;
		case 583:Nacionalidad="Espa�a";break;
		case 640:Nacionalidad="Estados Unidos";break;
		case 650:Nacionalidad="Estonia";break;
		case 690:Nacionalidad="Finlandia";break;
		case 700:Nacionalidad="Francia";break;
		case 780:Nacionalidad="Groenlandia";break;
		case 870:Nacionalidad="Haiti";break;
		case 880:Nacionalidad="Holanda";break;
		case 920:Nacionalidad="India";break;
		case 930:Nacionalidad="Indonesia";break;
		case 940:Nacionalidad="Irak";break;
		case 950:Nacionalidad="Iran";break;
		case 960:Nacionalidad="Irlanda";break;
		case 1070:Nacionalidad="Israel";break;
		case 1080:Nacionalidad="Italia";break;
		case 1090:Nacionalidad="Jamaica";break;
		case 1100:Nacionalidad="Japon";break;
		case 1380:Nacionalidad="Mexico";break;
		case 1490:Nacionalidad="Nicaragua";break;
		case 1510:Nacionalidad="Nigeria";break;
		case 1520:Nacionalidad="Noruega";break;
		case 1540:Nacionalidad="Nueva Zelandia";break;
		case 1600:Nacionalidad="Paraguay";break;
		case 1610:Nacionalidad="Peru";break;
		case 1650:Nacionalidad="Portugal";break;
		case 1660:Nacionalidad="Puerto Rico";break;
		case 1750:Nacionalidad="Rusia";break;
		case 1860:Nacionalidad="Senegal";break;
		case 1940:Nacionalidad="Sudafrica";break;
		case 1950:Nacionalidad="Sudan";break;
		case 1960:Nacionalidad="Suecia";break;
		case 1970:Nacionalidad="Suiza";break;
		case 2000:Nacionalidad="Taiwan";break;
		case 2001:Nacionalidad="Uruguay";break;
	}
		
	return Nacionalidad;
}
	

}
