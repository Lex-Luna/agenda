package com.uisrael.agenda.controlador;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;

public interface PrioridadControlador {

	public void guardar(Prioridad prioridad);

	public void actualizar(Prioridad prioridad);

	public Boolean eliminar(Prioridad prioridad);

	public Prioridad buscarPorId(int id);

	public List<Prioridad> listar();

	public List<Prioridad> listarPorCuenta(Cuenta cuenta);
}
