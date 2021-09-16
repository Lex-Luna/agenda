package com.uisrael.agenda.controlador.impl;

import java.util.List;

import com.uisrael.agenda.controlador.CuentaControlador;
import com.uisrael.agenda.modelo.dao.CuentaDao;
import com.uisrael.agenda.modelo.dao.impl.CuentaDaoImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;

public class CuentaControladorImpl implements CuentaControlador {
	private CuentaDao cuentaDao;

	public CuentaControladorImpl() {
		cuentaDao = new CuentaDaoImpl();
	}

	@Override
	public void insertarCuenta(Cuenta nuevoCuenta) {
		cuentaDao.guardar(nuevoCuenta);
	}

	@Override
	public void actualizarCuenta(Cuenta actualizarCuenta) {
		cuentaDao.actualizar(actualizarCuenta);
	}

	@Override
	public void eliminarCuenta(Cuenta eliminaCuenta) {
		cuentaDao.eliminar(eliminaCuenta);
	}

	@Override
	public List<Cuenta> listarCuentas() {
		return cuentaDao.listar();
	}

	@Override
	public Cuenta buscarCuentaId(int id) {
		Cuenta encontrado = new Cuenta();
		encontrado = null;
		try {
			encontrado = cuentaDao.buscarPorId(id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return encontrado;
	}

	@Override
	public Cuenta buscarCuentaUsu(String usuario) {
		return cuentaDao.buscarPorUsuario(usuario);
	}

}
