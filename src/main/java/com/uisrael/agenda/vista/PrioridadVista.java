package com.uisrael.agenda.vista;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.SerializationUtils;

import com.uisrael.agenda.controlador.PrioridadControlador;
import com.uisrael.agenda.controlador.impl.PrioridadControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;
import com.uisrael.agenda.util.MensajesFaces;

@Named
@ViewScoped
public class PrioridadVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private PrioridadControlador prioridadControlador;

	private List<Prioridad> prioridades;
	private Prioridad prioridad;
	private Cuenta cuenta;

	@PostConstruct
	public void init() {
		prioridadControlador = new PrioridadControladorImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		cuenta = (Cuenta) session.getAttribute("username");
		setPrioridad(new Prioridad());
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public List<Prioridad> getPrioridades() {
		if (prioridades == null) {
			prioridades = prioridadControlador.listarPorCuenta(cuenta);
		}
		return prioridades;
	}

	public void setPrioridades(List<Prioridad> prioridades) {
		this.prioridades = prioridades;
	}

	public void cmmdBtnPrepararItem(ActionEvent ae) {
		try {
			prioridad = Prioridad.class.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void cmmdBtnGuardar(ActionEvent ae) {
		try {
			if (prioridad.getIdPrioridad() == null) {
				prioridad.setCuenta(cuenta);
				prioridadControlador.guardar(prioridad);
			} else {
				prioridadControlador.actualizar(prioridad);
			}
			MensajesFaces.agregarMensaje("Su Información fue registrada exitosamente", FacesMessage.SEVERITY_INFO);
			if (!FacesContext.getCurrentInstance().isValidationFailed() && Objects.nonNull(prioridades)
					&& Objects.nonNull(prioridad)) {
				int index = prioridades.indexOf(prioridad);
				if (index >= 0) {
					prioridades.set(index, prioridad);
				} else {
					prioridades.add(prioridad);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

	public void cmmdBtnPreEditar(Prioridad prioridadTemp) {
		prioridad = SerializationUtils.clone(prioridadTemp);
	}

	public void cmmdBtnEliminar(ActionEvent ae) {
		try {
			if (Objects.nonNull(prioridad)) {
				if(prioridadControlador.eliminar(prioridad)) {
					MensajesFaces.agregarMensaje("Su Información fue eliminada exitosamente", FacesMessage.SEVERITY_INFO);
					if (!FacesContext.getCurrentInstance().isValidationFailed()) {
						this.prioridades.remove(prioridad);
						this.prioridad = null;
					}
				}else {
					MensajesFaces.agregarMensaje("Prioridad esta asociada a una tarea", FacesMessage.SEVERITY_FATAL);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

}
