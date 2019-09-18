package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import entitys.DuenioEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import views.PersonaView;


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
		
	}

	public void delete() {
		
	}
	
	public PersonaEntity toEntity() {
		PersonaEntity pe = new PersonaEntity();
		pe.setDocumento(this.documento);
		pe.setNombre(this.nombre);
		return pe;
	}
	
	public DuenioEntity toDuenioEntity(Unidad unidad) {
		UnidadEntity unidadEntity = new UnidadEntity();
		unidadEntity.setIdentificador(unidad.getId());
		unidadEntity.setNumero(unidad.getNumero());
		unidadEntity.setPiso(unidad.getPiso());
		DuenioEntity de = new DuenioEntity();
		de.setUnidad(unidadEntity);
		de.setDuenio(toEntity());
		System.out.println(de.toString());
		return de;
	}
	
	public String toString() {
		return new String(this.documento + ": " + this.nombre);
	}
}
