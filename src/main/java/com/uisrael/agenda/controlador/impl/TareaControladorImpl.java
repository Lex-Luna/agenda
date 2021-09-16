package com.uisrael.agenda.controlador.impl;

import java.util.List;

import com.uisrael.agenda.controlador.TareaControlador;
import com.uisrael.agenda.modelo.dao.TareaDao;
import com.uisrael.agenda.modelo.dao.impl.TareaDaoImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Tarea;

public class TareaControladorImpl implements TareaControlador {

	private TareaDao tareaDao;

	public TareaControladorImpl() {
		tareaDao = new TareaDaoImpl();
	}

	@Override
	public void guardar(Tarea tarea) {
		tareaDao.guardar(tarea);
	}

	@Override
	public void actualizar(Tarea tarea) {
		tareaDao.actualizar(tarea);
	}

	@Override
	public void eliminar(Tarea tarea) {
		tareaDao.eliminar(tarea);
	}

	@Override
	public Tarea buscarPorId(int id) {
		return tareaDao.buscarPorId(id);
	}

	@Override
	public List<Tarea> listar() {
		return tareaDao.listar();
	}

	@Override
	public List<Tarea> listarPorCuenta(Cuenta cuenta) {
		return tareaDao.listarPorCuenta(cuenta);
	}

}
