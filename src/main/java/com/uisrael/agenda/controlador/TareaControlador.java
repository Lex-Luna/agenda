package com.uisrael.agenda.controlador;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Tarea;

public interface TareaControlador {

	public void guardar(Tarea tarea);

	public void actualizar(Tarea tarea);

	public void eliminar(Tarea tarea);

	public Tarea buscarPorId(int id);

	public List<Tarea> listar();

	public List<Tarea> listarPorCuenta(Cuenta cuenta);
}
