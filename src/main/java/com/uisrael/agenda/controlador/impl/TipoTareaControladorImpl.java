package com.uisrael.agenda.controlador.impl;

import java.util.List;

import com.uisrael.agenda.controlador.TipoTareaControlador;
import com.uisrael.agenda.modelo.dao.TipoTareaDao;
import com.uisrael.agenda.modelo.dao.impl.TipoTareaDaoImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.TipoTarea;

public class TipoTareaControladorImpl implements TipoTareaControlador {

	private TipoTareaDao tipoTareaDao;

	public TipoTareaControladorImpl() {
		tipoTareaDao = new TipoTareaDaoImpl();
	}

	@Override
	public void guardar(TipoTarea tipoTarea) {
		tipoTareaDao.guardar(tipoTarea);
	}

	@Override
	public void actualizar(TipoTarea tipoTarea) {
		tipoTareaDao.actualizar(tipoTarea);
	}

	@Override
	public Boolean eliminar(TipoTarea tipoTarea) {
		return tipoTareaDao.eliminar(tipoTarea);
	}

	@Override
	public TipoTarea buscarPorId(int id) {
		return tipoTareaDao.buscarPorId(id);
	}

	@Override
	public List<TipoTarea> listar() {
		return tipoTareaDao.listar();
	}

	@Override
	public List<TipoTarea> listarPorCuenta(Cuenta cuenta) {
		return tipoTareaDao.listarPorCuenta(cuenta);
	}

}
