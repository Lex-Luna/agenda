package com.uisrael.agenda.modelo.entidad;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", length = 3, nullable = false, unique = true)
	private Integer idCliente;
	@Column(name = "nombre_cliente", length = 75, nullable = false)
	private String nombreCliente;
	@Column(name = "apellido_cliente", length = 75, nullable = false)
	private String apellidoCliente;
	@Column(name = "mail_cliente", length = 75, nullable = false)
	private String emailCliente;
	
	@Column(name = "telefono_cliente", length = 75, nullable = false)
	private String telefonoCliente;
	
	@Column(name = "ci_cliente", length = 75, nullable = false)
	private Integer ciCliente;
	

	public void setCiCliente(Integer ciCliente) {
		this.ciCliente = ciCliente;
	}
	public Integer getCiCliente() {
		return ciCliente;
	}
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	



	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta", foreignKey = @ForeignKey(name = "FK_CUENTA_CLIENTE"))
	private Cuenta cuenta;

	public Cliente() {
		cuenta = new Cuenta();
	}



	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + "]";
	}

}
