package dao;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteDao {
	public boolean agregarCliente(Cliente cliente);
	public boolean eliminarCliente(String idUsuario);
	public boolean modificarCliente(Cliente cliente, String idUsuario);
	public Cliente leerCliente(String idUsuario);
	public boolean existeCliente(String dni);
	public ArrayList<Cliente> listarClientes();
	public ArrayList<Cliente> listarClientesFiltro(Cliente cliente);
}
