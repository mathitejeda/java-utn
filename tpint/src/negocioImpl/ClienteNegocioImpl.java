package negocioImpl;

import java.util.ArrayList;

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	@Override
	public boolean agregarCliente(Cliente cliente) {

		ClienteDao cdao = new ClienteDaoImpl();

		boolean estado = false;
		if (cliente.getDni().trim().length() > 0 && cliente.getCuil().trim().length() > 0) {
			estado = cdao.agregarCliente(cliente);
		}
		return estado;
	}

	@Override
	public Cliente ObtenerCliente(String idUsuario) {
		ClienteDao cdao = new ClienteDaoImpl();
		Cliente cliente = new Cliente();		

		try {
			cliente = cdao.leerCliente(idUsuario);
		} catch (NullPointerException e) {
		}
		
		if(cliente==null) {
		}
		
		return cliente;
	}

	@Override
	public ArrayList<Cliente> listarClientes() {
		ClienteDao cdao = new ClienteDaoImpl();
		return cdao.listarClientes();
	}
	
	@Override
	public ArrayList<String> obtenerClientesDisponibles() {
		ClienteDao cdao = new ClienteDaoImpl();
		ArrayList<String> usuariosDisp = new ArrayList<String>();
		ArrayList<Cliente> listaUsuariosFinal = cdao.listarClientes();
		
		if (listaUsuariosFinal != null) {
			for (Cliente cliente : listaUsuariosFinal) {					
				usuariosDisp.add(cliente.getUsuario().getIdUsuario().toString());
			}
		}
		return usuariosDisp;
	}

	@Override
	public boolean eliminarCliente(String idUsuario) {
		ClienteDao cdao = new ClienteDaoImpl();

		boolean estado = false;
		estado = cdao.eliminarCliente(idUsuario);

		return estado;
	}

	@Override
	public boolean modificarCliente(Cliente cliente, String idUsuario) {
		ClienteDao cdao = new ClienteDaoImpl();

		boolean estado = false;
		estado = cdao.modificarCliente(cliente, idUsuario);

		return estado;
	}

	@Override
	public ArrayList<Cliente> listarClientesFiltro(Cliente cli) {
		ClienteDao cdao = new ClienteDaoImpl();
		return cdao.listarClientesFiltro(cli);
	}

	@Override
	public boolean leerCliente(String idUsuario) {
		ClienteDao cdao = new ClienteDaoImpl();
		Cliente cliente = new Cliente();

		Boolean existe = false;

		try {
			cliente = cdao.leerCliente(idUsuario);
			existe = true;
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if(cliente==null) {
			existe = false;
		}
		
		return existe;
	}
	
	
	@Override
	public boolean existeCliente(String dni) {
		ClienteDao cdao = new ClienteDaoImpl();

		Boolean existe = false;

		try {
			existe = cdao.existeCliente(dni);
		} catch (NullPointerException e) {
			existe = false;
		}
		
		return existe;
	}
	

}













