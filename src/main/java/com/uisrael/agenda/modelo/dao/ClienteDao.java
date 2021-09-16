package com.uisrael.agenda.modelo.dao;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cliente;

public interface ClienteDao {

	void guardar(Cliente cliente);

	public void actualizar(Cliente cliente);

	public void eliminar(Cliente cliente);

	public Cliente buscarPorId(int id);

	List<Cliente> listar();

}
