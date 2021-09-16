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
@Table(name = "tbl_prioridad")
public class Prioridad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prioridad", length = 3, nullable = false, unique = true)
	private Integer idPrioridad;
	@Column(name = "nombre_prioridad", length = 75, nullable = false)
	private String nombrePrioridad;
	@Column(name = "color_prioridad", length = 50, nullable = false)
	private String colorPrioridad;

	@ManyToOne
	@JoinColumn(name = "id_cuenta", foreignKey = @ForeignKey(name = "FK_CUENTA_PRIORIDAD"))
	private Cuenta cuenta;

	public Integer getIdPrioridad() {
		return idPrioridad;
	}

	public void setIdPrioridad(Integer idPrioridad) {
		this.idPrioridad = idPrioridad;
	}

	public String getNombrePrioridad() {
		return nombrePrioridad;
	}

	public void setNombrePrioridad(String nombrePrioridad) {
		this.nombrePrioridad = nombrePrioridad;
	}

	public String getColorPrioridad() {
		return colorPrioridad;
	}

	public void setColorPrioridad(String colorPrioridad) {
		this.colorPrioridad = colorPrioridad;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPrioridad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prioridad other = (Prioridad) obj;
		return Objects.equals(idPrioridad, other.idPrioridad);
	}

	@Override
	public String toString() {
		return "Prioridad [nombrePrioridad=" + nombrePrioridad + "]";
	}

}
