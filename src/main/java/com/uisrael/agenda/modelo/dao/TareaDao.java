package com.uisrael.agenda.modelo.dao;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Tarea;

public interface TareaDao {

	public void guardar(Tarea tarea);

	public void actualizar(Tarea tarea);

	public void eliminar(Tarea tarea);

	public Tarea buscarPorId(int id);

	public List<Tarea> listar();

	public List<Tarea> listarPorCuenta(Cuenta cuenta);
}
