package com.uisrael.agenda.modelo.dao;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;

public interface PrioridadDao {

	public void guardar(Prioridad prioridad);

	public void actualizar(Prioridad prioridad);

	public boolean eliminar(Prioridad prioridad);

	public Prioridad buscarPorId(int id);

	public List<Prioridad> listar();

	public List<Prioridad> listarPorCuenta(Cuenta cuenta);
}
