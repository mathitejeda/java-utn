package negocioImpl;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import excepciones.ClienteRepetidoException;
import excepciones.PasswordException;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	UsuarioDao udao = new UsuarioDaoImpl();
	
	@Override
	public boolean insert(Usuario usuario, String pass2) throws PasswordException{

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean estado=false;
		
		if(usuario.getIdUsuario().trim().length()>0 && usuario.getPass().trim().length()>0) { 
			Usuario userBuscado = new Usuario();
			userBuscado = udao.ReadUser(usuario.getIdUsuario().trim());
			if(userBuscado == null) {
				if(validarContraseña(pass2, usuario.getPass())) {
					estado=udao.insert(usuario);
				}else {
					PasswordException exc1  = new PasswordException();
					throw exc1;
				}
				 
			}
		} 		
		return estado;
	}
	
	@Override
	public boolean modificarClave(String usuario, String pass, String pass2) throws PasswordException{

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean estado=false;
		
		if(usuario.trim().length()>0 && pass.trim().length()>0 && pass2.trim().length()>0) { 
			
			if(validarContraseña(pass, pass2)) {
				estado=udao.modificarClave(usuario, pass);
				estado = true;
			}
			
			if (estado==false) {
				PasswordException exc1  = new PasswordException();
				 throw exc1;
			}

		} 		
		return estado;
	}
	

	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario ReadUser(String user) {

		Usuario userBuscado = new Usuario();
		if(user.trim().length()>0) {
			userBuscado = udao.ReadUser(user);
		}else {
			userBuscado = null;
		}
		return userBuscado;
	}
	
	@Override
	public ArrayList<Usuario> obtenerUsuarios() {
		
		ArrayList<Usuario> listaUsuarios = udao.obtenerUsuarios();
		return listaUsuarios;
	}

	@Override
	public ArrayList<Usuario> obtenerUsuariosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}
		
	private boolean validarContraseña(String pass2, String pass) {
		return pass2.equals(pass);		
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
	
}







