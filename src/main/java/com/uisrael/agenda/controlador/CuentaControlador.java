package com.uisrael.agenda.controlador;

import java.util.List;
import com.uisrael.agenda.modelo.entidad.Cuenta;

public interface CuentaControlador {
	
	public void insertarCuenta(Cuenta cuenta);

	public void actualizarCuenta(Cuenta cuenta);

	public void eliminarCuenta(Cuenta cuenta);

	public Cuenta buscarCuentaId(int id);

	public Cuenta buscarCuentaUsu(String usuario);

	public List<Cuenta> listarCuentas();
}
