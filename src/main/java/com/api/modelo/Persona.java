package com.api.modelo;

import com.api.entitys.PersonaEntity;
import com.api.views.PersonaView;

public class Persona {
	private String documento;
	private String nombre;
	
	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre);
	} 

	public void save() {
		PersonaDAO.save(this);
	}

	public void delete() {
		PersonaDAO.delete(this);
	}
	
	public PersonaEntity toEntity() {
		PersonaEntity pe = new PersonaEntity();
		pe.setDocumento(this.documento);
		pe.setNombre(this.nombre);
		return pe;
	}
	
	public String toString() {
		return new String(this.documento + ": " + this.nombre);
	}
}
