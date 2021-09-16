package com.uisrael.agenda.modelo.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_tarea")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarea", length = 3, nullable = false, unique = true)
	private int idTarea;
	@Column(name = "titulo_tarea", length = 75, nullable = false)
	private String tituloTarea;
	@Column(name = "descripcion_tarea", columnDefinition = "TEXT", nullable = false)
	private String descripcionTarea;
	@Column(name = "fecha_inicio_tarea")
	private LocalDateTime fechaInicioTarea;
	@Column(name = "fecha_fin_tarea")
	private LocalDateTime fechaFinTarea;
	@Column(name = "todo_dia_tarea", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'TRUE'", insertable = false)
	private boolean todoDiaTarea;

	@ManyToOne
	@JoinColumn(name = "id_cuenta", foreignKey = @ForeignKey(name = "FK_CUENTA_TAREA"))
	private Cuenta cuenta;

	@ManyToOne
	@JoinColumn(name = "id_prioridad", foreignKey = @ForeignKey(name = "FK_PRIORIDAD_TAREA"), nullable = true)
	private Prioridad prioridad;

	@ManyToOne
	@JoinColumn(name = "id_tipo_tarea", foreignKey = @ForeignKey(name = "FK_TIPO_TAREA"), nullable = true)
	private TipoTarea tipoTarea;

	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public String getTituloTarea() {
		return tituloTarea;
	}

	public void setTituloTarea(String tituloTarea) {
		this.tituloTarea = tituloTarea;
	}

	public String getDescripcionTarea() {
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public LocalDateTime getFechaInicioTarea() {
		return fechaInicioTarea;
	}

	public void setFechaInicioTarea(LocalDateTime fechaInicioTarea) {
		this.fechaInicioTarea = fechaInicioTarea;
	}

	public LocalDateTime getFechaFinTarea() {
		return fechaFinTarea;
	}

	public void setFechaFinTarea(LocalDateTime fechaFinTarea) {
		this.fechaFinTarea = fechaFinTarea;
	}

	public boolean isTodoDiaTarea() {
		return todoDiaTarea;
	}

	public void setTodoDiaTarea(boolean todoDiaTarea) {
		this.todoDiaTarea = todoDiaTarea;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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

}