package negocio;

import java.util.ArrayList;
import entidad.Cliente;

public interface ClienteNegocio {
	public boolean agregarCliente(Cliente cliente);
	public boolean eliminarCliente(String idUsuario);
	public boolean modificarCliente(Cliente cliente, String idUsuario);
	public boolean leerCliente(String idUsuario);
	ArrayList<Cliente> listarClientesFiltro(Cliente cli);
	public ArrayList<Cliente> listarClientes();
	public ArrayList<String> obtenerClientesDisponibles();
	public boolean existeCliente(String dni);
	public Cliente ObtenerCliente(String idUsuario);
}
