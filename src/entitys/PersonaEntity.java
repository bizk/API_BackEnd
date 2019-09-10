package entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.Persona;

@Entity
@Table(name="personas")
public class PersonaEntity {
	@Id
	private String documento;
	private String nombre;
	
	public PersonaEntity(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}
	
	public Persona toPersona() {
		return new Persona(this.documento,this.nombre);
	}
	
}
