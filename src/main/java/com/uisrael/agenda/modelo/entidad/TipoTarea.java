package com.uisrael.agenda.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;

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
@Table(name = "tbl_tipo_tarea")
public class TipoTarea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_tarea", length = 3, nullable = false, unique = true)
	private Integer idTipoTarea;
	@Column(name = "nombre_tipo_tarea", length = 75, nullable = false)
	private String nombreTipoTarea;

	@ManyToOne
	@JoinColumn(name = "id_cuenta", foreignKey = @ForeignKey(name = "FK_CUENTA_TIPO_TAREA"))
	private Cuenta cuenta;

	public Integer getIdTipoTarea() {
		return idTipoTarea;
	}

	public void setIdTipoTarea(Integer idTipoTarea) {
		this.idTipoTarea = idTipoTarea;
	}

	public String getNombreTipoTarea() {
		return nombreTipoTarea;
	}

	public void setNombreTipoTarea(String nombreTipoTarea) {
		this.nombreTipoTarea = nombreTipoTarea;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoTarea);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoTarea other = (TipoTarea) obj;
		return Objects.equals(idTipoTarea, other.idTipoTarea);
	}

	@Override
	public String toString() {
		return "TipoTarea [nombreTipoTarea=" + nombreTipoTarea + "]";
	}

}
