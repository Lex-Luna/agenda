package com.uisrael.agenda.vista;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.uisrael.agenda.controlador.CuentaControlador;
import com.uisrael.agenda.controlador.impl.CuentaControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.util.MensajesFaces;

@Named
@SessionScoped
public class LoginVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private CuentaControlador cuentaControlador;
	private Cuenta cuenta;

	@PostConstruct
	public void init() {
		cuenta = new Cuenta();
		cuentaControlador = new CuentaControladorImpl();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String cmmdBtnIngresar() {
		Cuenta cuentaTmp = cuentaControlador.buscarCuentaUsu(cuenta.getUsuarioCuenta());
		if (Objects.nonNull(cuentaTmp) && cuentaTmp.getContraseniaCuenta().equals(cuenta.getContraseniaCuenta())) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("username", cuentaTmp);
			return "/administracion/tarea/calendario";
		} else {
			MensajesFaces.agregarMensaje("Usuario y/o Clave son inconrrecto", FacesMessage.SEVERITY_ERROR);
			return "login";
		}
	}

	public String cmmdBtnSalir() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "/login";
	}

}
