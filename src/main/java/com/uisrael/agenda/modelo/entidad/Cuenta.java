package com.uisrael.agenda.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cuenta")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta", length = 3, nullable = false, unique = true)
	private int idCuenta;
	@Column(name = "usuario_cuenta", length = 50, nullable = false)
	private String usuarioCuenta;
	@Column(name = "contrasenia_cuenta", length = 30, nullable = false)
	private String contraseniaCuenta;
	@Column(name = "estado_cuenta", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'TRUE'", insertable = false)
	private boolean estadoCuenta;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_CLIENTE_CUENTA"))
	private Cliente cliente;
	
	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
	private List<Tarea> listaTarea = new ArrayList<Tarea>();

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getUsuarioCuenta() {
		return usuarioCuenta;
	}

	public void setUsuarioCuenta(String usuarioCuenta) {
		this.usuarioCuenta = usuarioCuenta;
	}

	public String getContraseniaCuenta() {
		return contraseniaCuenta;
	}

	public void setContraseniaCuenta(String contraseniaCuenta) {
		this.contraseniaCuenta = contraseniaCuenta;
	}

	public boolean isEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Tarea> getListaTarea() {
		return listaTarea;
	}

	public void setListaTarea(List<Tarea> listaTarea) {
		this.listaTarea = listaTarea;
	}


	@Override
	public String toString() {
		return "Cuenta [usuarioCuenta=" + usuarioCuenta + "]";
	}

}
