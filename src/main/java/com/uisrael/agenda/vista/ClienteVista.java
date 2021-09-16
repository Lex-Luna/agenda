package com.uisrael.agenda.vista;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.uisrael.agenda.controlador.ClienteControlador;
import com.uisrael.agenda.controlador.CuentaControlador;
import com.uisrael.agenda.controlador.impl.ClienteControladorImpl;
import com.uisrael.agenda.controlador.impl.CuentaControladorImpl;
import com.uisrael.agenda.modelo.entidad.Cliente;
import com.uisrael.agenda.util.MensajesFaces;

@Named(value = "clienteVista")
@ViewScoped
public class ClienteVista implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteControlador clienteControlador;
	private CuentaControlador cuentaControlador;
	private Cliente cliente;

	private List<Cliente> listaClientes;

	@PostConstruct
	public void init() {
		clienteControlador = new ClienteControladorImpl();
		cuentaControlador = new CuentaControladorImpl();
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void cmmdBtnGuardar() {
		try {
			if (!verificarUsuario(cliente.getCuenta().getUsuarioCuenta())) {
				cliente.getCuenta().setCliente(cliente);
				clienteControlador.insertarCliente(cliente);
				MensajesFaces.agregarMensaje("Su Información fue registrada exitosamente", FacesMessage.SEVERITY_INFO);
				if (!FacesContext.getCurrentInstance().isValidationFailed()) {
					cliente = new Cliente();
					PrimeFaces.current().resetInputs("form-crearCuenta:outputPanel");
				}
			}else {
				MensajesFaces.agregarMensaje("Usuario ya se encuentra registrado", FacesMessage.SEVERITY_WARN);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MensajesFaces.agregarMensaje("Ocurrio un error al guardar su información", FacesMessage.SEVERITY_FATAL);
		}
	}

	public void listarCliente() {
		listaClientes = clienteControlador.listarClientes();
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	private Boolean verificarUsuario(String usuarioCuenta) {
		return Objects.nonNull(cuentaControlador.buscarCuentaUsu(usuarioCuenta));
	}
}
