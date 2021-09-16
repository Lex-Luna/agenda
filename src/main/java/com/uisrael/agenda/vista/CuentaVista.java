package com.uisrael.agenda.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.uisrael.agenda.controlador.CuentaControlador;
import com.uisrael.agenda.controlador.impl.CuentaControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;

@Named
@ViewScoped
public class CuentaVista implements Serializable {
	private static final long serialVersionUID = 1L;
	private String usuario, contraseña;
	private CuentaControlador cuentaControlador;
	private Cuenta nuevoCuenta, cuentaSeleccionado;

	private List<Cuenta> listaCuentas;

	@PostConstruct
	public void init() {
		listaCuentas = new ArrayList<Cuenta>();
		cuentaControlador = new CuentaControladorImpl();
		cuentaSeleccionado = new Cuenta();
		listarCuenta();
	}

	public void insertarCuenta() {
		nuevoCuenta = new Cuenta();
		;
		try {
			cuentaControlador.insertarCuenta(nuevoCuenta);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Registarda"));
			listarCuenta();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR AL REGISTRAR Cuenta"));
		}
		PrimeFaces.current().executeScript("PF('cuentaDialogo').hide()");// desaparezca el dalogo
		PrimeFaces.current().ajax().update("frmCuenta:mensaje", "frmCuenta:dtcuenta");// actualizar tabla clinte
	}

	public void listarCuenta() {
		listaCuentas = cuentaControlador.listarCuentas();
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Cuenta", "Se Inserto correctamente..");
	}

	public void showInfoDelete() {
		addMessage(FacesMessage.SEVERITY_INFO, "Cuenta", "Se Elimino correctamente..");
	}

	public void deleteCuenta() {
		// this.products.remove(this.selectedProduct);

		cuentaControlador.eliminarCuenta(cuentaSeleccionado);
		this.cuentaSeleccionado = null;
		listarCuenta();
		showInfoDelete();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public CuentaControlador getCuentaControlador() {
		return cuentaControlador;
	}

	public void setCuentaControlador(CuentaControlador cuentaControlador) {
		this.cuentaControlador = cuentaControlador;
	}

	public Cuenta getNuevoCuenta() {
		return nuevoCuenta;
	}

	public void setNuevoCuenta(Cuenta nuevoCuenta) {
		this.nuevoCuenta = nuevoCuenta;
	}

	public Cuenta getCuentaSeleccionado() {
		return cuentaSeleccionado;
	}

	public void setCuentaSeleccionado(Cuenta cuentaSeleccionado) {
		this.cuentaSeleccionado = cuentaSeleccionado;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

}
