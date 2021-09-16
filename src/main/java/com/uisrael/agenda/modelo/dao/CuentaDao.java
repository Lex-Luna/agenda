package com.uisrael.agenda.modelo.dao;

import java.util.List;

import com.uisrael.agenda.modelo.entidad.Cuenta;

public interface CuentaDao {
	public void guardar(Cuenta cuenta);

	public void actualizar(Cuenta cuenta);

	public void eliminar(Cuenta cuenta);

	public Cuenta buscarPorId(int id);

	public Cuenta buscarPorUsuario(String usuario);

	public List<Cuenta> listar();
}
