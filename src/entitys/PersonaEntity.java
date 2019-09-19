package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="personas")
public class PersonaEntity {
	@Id
	@Column(name="documento")
	private String documento;
	@Column(name="nombre")
	private String nombre;
	public PersonaEntity() {}
	
	public PersonaEntity(String documento, String nombre) {
		super();
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		return new String(this.documento + " " + this.nombre);
	}
	
}
