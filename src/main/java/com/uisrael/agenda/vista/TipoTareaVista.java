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

import com.uisrael.agenda.controlador.TipoTareaControlador;
import com.uisrael.agenda.controlador.impl.TipoTareaControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.TipoTarea;
import com.uisrael.agenda.util.MensajesFaces;

@Named
@ViewScoped
public class TipoTareaVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoTareaControlador tipoTareaControlador;

	private List<TipoTarea> tipoTareas;
	private TipoTarea tipoTarea;
	private Cuenta cuenta;

	@PostConstruct
	public void init() {
		tipoTareaControlador = new TipoTareaControladorImpl();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		cuenta = (Cuenta) session.getAttribute("username");
		setTipoTarea(new TipoTarea());
	}

	public TipoTarea getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public List<TipoTarea> getTipoTareas() {
		if (tipoTareas == null) {
			tipoTareas = tipoTareaControlador.listarPorCuenta(cuenta);
		}
		return tipoTareas;
	}

	public void setTipoTareas(List<TipoTarea> tipoTareas) {
		this.tipoTareas = tipoTareas;
	}

	public void cmmdBtnPrepararItem(ActionEvent ae) {
		try {
			tipoTarea = TipoTarea.class.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void cmmdBtnGuardar(ActionEvent ae) {
		try {
			if (tipoTarea.getIdTipoTarea() == null) {
				tipoTarea.setCuenta(cuenta);
				tipoTareaControlador.guardar(tipoTarea);
			} else {
				tipoTareaControlador.actualizar(tipoTarea);
			}
			MensajesFaces.agregarMensaje("Su Información fue registrada exitosamente", FacesMessage.SEVERITY_INFO);
			if (!FacesContext.getCurrentInstance().isValidationFailed() && Objects.nonNull(tipoTareas)
					&& Objects.nonNull(tipoTarea)) {
				int index = tipoTareas.indexOf(tipoTarea);
				if (index >= 0) {
					tipoTareas.set(index, tipoTarea);
				} else {
					tipoTareas.add(tipoTarea);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

	public void cmmdBtnPreEditar(TipoTarea tipoTareaTemp) {
		tipoTarea = SerializationUtils.clone(tipoTareaTemp);
	}

	public void cmmdBtnEliminar(ActionEvent ae) {
		try {
			if (Objects.nonNull(tipoTarea)) {
				if (tipoTareaControlador.eliminar(tipoTarea)) {
					MensajesFaces.agregarMensaje("Su Información fue eliminada exitosamente",
							FacesMessage.SEVERITY_INFO);
					if (!FacesContext.getCurrentInstance().isValidationFailed()) {
						this.tipoTareas.remove(tipoTarea);
						this.tipoTarea = null;
					}
				} else {
					MensajesFaces.agregarMensaje("Tipo Tarea esta asociada a una tarea", FacesMessage.SEVERITY_FATAL);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

}
