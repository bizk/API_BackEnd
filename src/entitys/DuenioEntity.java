package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private int unidad;

	
	@Column(name="documento")
	private String duenio;
	
	public DuenioEntity() {
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getUnidad() {
		return this.unidad; 
	}
	
	public String toString() {
		return new String(id + " " + unidad + " " + duenio);
	}
}
