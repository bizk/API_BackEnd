package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.Persona;

@Entity
@Table(name="personas")
public class PersonaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="documento")
	private String documento;
	@Column(name="nombre")
	private String nombre;
	
	public PersonaEntity(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}
	
	public Persona toPersona() {
		return new Persona(this.documento,this.nombre);
	}
	
}
