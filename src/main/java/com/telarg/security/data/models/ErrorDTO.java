package com.telarg.security.data.models;

public class ErrorDTO {

	private String id;
	private String mensaje;
	
	public ErrorDTO(){ }
	
	public ErrorDTO(String id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
