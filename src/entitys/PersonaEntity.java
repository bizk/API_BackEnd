package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="personas")
public class PersonaEntity {
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.AUTO)
=======
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
	@Column(name="documento")
	private String documento;
	@Column(name="nombre")
	private String nombre;
	
<<<<<<< HEAD
	public String toString() {
		return new String(this.documento + " " + this.nombre);
=======
	public PersonaEntity() {}
	
	public PersonaEntity(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
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
