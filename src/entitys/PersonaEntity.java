package entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personas")
public class PersonaEntity {
	@Id
	private String documento;
	private String nombre;
	
	
	public String toString() {
		return new String(this.documento + " " + this.nombre);
	}
}
