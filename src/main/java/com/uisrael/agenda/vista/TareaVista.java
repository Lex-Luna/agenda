package com.uisrael.agenda.vista;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.uisrael.agenda.controlador.PrioridadControlador;
import com.uisrael.agenda.controlador.TareaControlador;
import com.uisrael.agenda.controlador.TipoTareaControlador;
import com.uisrael.agenda.controlador.impl.PrioridadControladorImpl;
import com.uisrael.agenda.controlador.impl.TareaControladorImpl;
import com.uisrael.agenda.controlador.impl.TipoTareaControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;
import com.uisrael.agenda.modelo.entidad.Tarea;
import com.uisrael.agenda.modelo.entidad.TipoTarea;
import com.uisrael.agenda.util.MensajesFaces;

@Named
@ViewScoped
public class TareaVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private TareaControlador tareaControlador;
	private PrioridadControlador prioridadControlador;
	private TipoTareaControlador tipoTareaControlador;

	private Cuenta cuenta;
	private Prioridad prioridad;
	private TipoTarea tipoTarea;

	private ScheduleEvent<?> tarea = new DefaultScheduleEvent<>();
	private ScheduleModel calendario;

	@PostConstruct
	public void init() {
		tareaControlador = new TareaControladorImpl();
		prioridadControlador = new PrioridadControladorImpl();
		tipoTareaControlador = new TipoTareaControladorImpl();

		calendario = new DefaultScheduleModel();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		cuenta = (Cuenta) session.getAttribute("username");
		listarTareaPorCuenta();
	}

	public ScheduleModel getCalendario() {
		return calendario;
	}

	public void setCalendario(ScheduleModel calendario) {
		this.calendario = calendario;
	}

	public ScheduleEvent<?> getTarea() {
		return tarea;
	}

	public void setTarea(ScheduleEvent<?> tarea) {
		this.tarea = tarea;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public TipoTarea getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public void cmmdBtnGuardar() {
		tarea = DefaultScheduleEvent.builder().id(tarea.getId()).title(tarea.getTitle()).startDate(tarea.getStartDate())
				.endDate(tarea.getEndDate()).description(tarea.getDescription())
				.backgroundColor("#".concat(prioridad.getColorPrioridad())).build();
		if (tarea.isAllDay()) {
			if (tarea.getStartDate().toLocalDate().equals(tarea.getEndDate().toLocalDate())) {
				tarea.setEndDate(tarea.getEndDate().plusDays(1));
			}
		}
		if (tarea.getId() == null) {
			tarea.setId(String.valueOf(guardarTarea(tarea)));
			if (!FacesContext.getCurrentInstance().isValidationFailed()) {
				calendario.addEvent(tarea);
			}
		} else {
			guardarTarea(tarea);
			if (!FacesContext.getCurrentInstance().isValidationFailed()) {
				calendario.updateEvent(tarea);
			}
		}

		tarea = new DefaultScheduleEvent<>();
	}

	public void cmmdBtnEliminar() {
		if (tarea.getId() != null) {
			eliminarTarea(tarea);
			if (!FacesContext.getCurrentInstance().isValidationFailed()) {
				calendario.deleteEvent(tarea);
			}
		}

		tarea = new DefaultScheduleEvent<>();
	}

	public void evtSeleccionarFecha(SelectEvent<LocalDateTime> selectEvent) {
		tarea = DefaultScheduleEvent.builder().startDate(selectEvent.getObject())
				.endDate(selectEvent.getObject().plusHours(1)).build();
	}

	public void evtSeleccionarTarea(SelectEvent<ScheduleEvent<?>> selectEvent) {
		tarea = selectEvent.getObject();
		if (tarea.getId() != null) {
			Tarea tareaTemp = tareaControlador.buscarPorId(Integer.parseInt(tarea.getId()));
			prioridad = prioridadControlador.buscarPorId(tareaTemp.getPrioridad().getIdPrioridad());
			tipoTarea = tipoTareaControlador.buscarPorId(tareaTemp.getTipoTarea().getIdTipoTarea());
		}

	}

	private int guardarTarea(ScheduleEvent<?> tarea) {
		Tarea tareaTemp = new Tarea();
		tareaTemp.setTituloTarea(tarea.getTitle());
		tareaTemp.setFechaInicioTarea(tarea.getStartDate());
		tareaTemp.setFechaFinTarea(tarea.getEndDate());
		tareaTemp.setDescripcionTarea(tarea.getDescription());
		tareaTemp.setTodoDiaTarea(tarea.isAllDay());
		tareaTemp.setPrioridad(prioridad);
		tareaTemp.setTipoTarea(tipoTarea);
		tareaTemp.setCuenta(cuenta);
		try {
			if (tarea.getId() == null) {
				tareaControlador.guardar(tareaTemp);
			} else {
				tareaTemp.setIdTarea(Integer.parseInt(tarea.getId()));
				tareaControlador.actualizar(tareaTemp);
			}

			MensajesFaces.agregarMensaje("Su evento fue guardado exitosamente", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
		return tareaTemp.getIdTarea();
	}

	private void eliminarTarea(ScheduleEvent<?> tarea) {
		Tarea tareaTemp = new Tarea();
		tareaTemp.setTituloTarea(tarea.getTitle());
		tareaTemp.setFechaInicioTarea(tarea.getStartDate());
		tareaTemp.setFechaFinTarea(tarea.getEndDate());
		tareaTemp.setDescripcionTarea(tarea.getDescription());
		tareaTemp.setTodoDiaTarea(tarea.isAllDay());
		tareaTemp.setCuenta(cuenta);
		try {
			tareaTemp.setIdTarea(Integer.parseInt(tarea.getId()));
			tareaControlador.eliminar(tareaTemp);
			MensajesFaces.agregarMensaje("Su evento fue eliminado exitosamente", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

	private List<Tarea> listarTareaPorCuenta() {
		List<Tarea> tareas = tareaControlador.listarPorCuenta(cuenta);
		tareas.forEach(tarea -> {
			String backgroundColor = tarea.getPrioridad() == null ? "orange"
					: "#".concat(tarea.getPrioridad().getColorPrioridad());
			DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder().id(String.valueOf(tarea.getIdTarea()))
					.title(tarea.getTituloTarea()).startDate(tarea.getFechaInicioTarea())
					.endDate(tarea.getFechaFinTarea()).description(tarea.getDescripcionTarea())
					.backgroundColor(backgroundColor).build();
			calendario.addEvent(event);
		});
		return tareas;
	}
}
