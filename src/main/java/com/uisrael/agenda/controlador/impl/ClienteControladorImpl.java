package com.uisrael.agenda.controlador.impl;

import java.util.List;

import com.uisrael.agenda.controlador.ClienteControlador;
import com.uisrael.agenda.modelo.dao.ClienteDao;
import com.uisrael.agenda.modelo.dao.impl.ClienteDaoImpl;
import com.uisrael.agenda.modelo.entidad.Cliente;

public class ClienteControladorImpl implements ClienteControlador {

	private ClienteDao clienteDao;

	public ClienteControladorImpl() {
		clienteDao = new ClienteDaoImpl();
	}

	@Override
	public void insertarCliente(Cliente cliente) {
		clienteDao.guardar(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		clienteDao.actualizar(cliente);
	}

	@Override
	public void eliminarCliente(Cliente cliente) {
		clienteDao.eliminar(cliente);
	}

	@Override
	public Cliente buscarClienteId(int id) {
		return clienteDao.buscarPorId(id);
	}

	@Override
	public List<Cliente> listarClientes() {
		return clienteDao.listar();
	}

}
