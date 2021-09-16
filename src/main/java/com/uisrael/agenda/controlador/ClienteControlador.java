package com.uisrael.agenda.controlador;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cliente;

public interface ClienteControlador {
	public void insertarCliente(Cliente nuevoCliente);

	public void actualizarCliente(Cliente actualizarCliente);

	public void eliminarCliente(Cliente eliminaCiente);
	
	public Cliente buscarClienteId(int id);

	public List<Cliente> listarClientes();
}
