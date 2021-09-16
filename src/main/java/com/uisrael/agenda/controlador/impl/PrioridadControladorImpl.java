package com.uisrael.agenda.controlador.impl;

import java.util.List;

import com.uisrael.agenda.controlador.PrioridadControlador;
import com.uisrael.agenda.modelo.dao.PrioridadDao;
import com.uisrael.agenda.modelo.dao.impl.PrioridadDaoImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;

public class PrioridadControladorImpl implements PrioridadControlador {

	private PrioridadDao prioridadDao;

	public PrioridadControladorImpl() {
		prioridadDao = new PrioridadDaoImpl();
	}

	@Override
	public void guardar(Prioridad prioridad) {
		prioridadDao.guardar(prioridad);
	}

	@Override
	public void actualizar(Prioridad prioridad) {
		prioridadDao.actualizar(prioridad);
	}

	@Override
	public Boolean eliminar(Prioridad prioridad) {
		return prioridadDao.eliminar(prioridad);
	}

	@Override
	public Prioridad buscarPorId(int id) {
		return prioridadDao.buscarPorId(id);
	}

	@Override
	public List<Prioridad> listar() {
		return prioridadDao.listar();
	}

	@Override
	public List<Prioridad> listarPorCuenta(Cuenta cuenta) {
		return prioridadDao.listarPorCuenta(cuenta);
	}

}
