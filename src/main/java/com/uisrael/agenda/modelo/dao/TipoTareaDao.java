package com.uisrael.agenda.modelo.dao;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.TipoTarea;

public interface TipoTareaDao {

	public void guardar(TipoTarea tipoTarea);

	public void actualizar(TipoTarea tipoTarea);

	public Boolean eliminar(TipoTarea tipoTarea);

	public TipoTarea buscarPorId(int id);

	public List<TipoTarea> listar();

	public List<TipoTarea> listarPorCuenta(Cuenta cuenta);
}
