package com.uisrael.agenda.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensajesFaces {

	public static void agregarMensaje(String detalle, FacesMessage.Severity gravedad) {
		String titulo = "";
		try {
			if (gravedad == FacesMessage.SEVERITY_INFO) {
				titulo = "Información";
			}
			if (gravedad == FacesMessage.SEVERITY_WARN) {
				titulo = "Advertencia";
			}
			if (gravedad == FacesMessage.SEVERITY_ERROR) {
				titulo = "Error";
				FacesContext.getCurrentInstance().validationFailed();
			}
			if (gravedad == FacesMessage.SEVERITY_FATAL) {
				titulo = "Error";
				FacesContext.getCurrentInstance().validationFailed();
			}
			FacesMessage facesMensaje = new FacesMessage(gravedad, titulo, detalle);
			FacesContext.getCurrentInstance().addMessage(null, facesMensaje);
		} catch (Exception exception) {
			System.out.println("Error: " + exception.getMessage());
		}
	}

}
