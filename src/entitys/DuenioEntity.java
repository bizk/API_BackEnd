package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;

	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity duenio;
	
	public DuenioEntity() {
		
	}
	
	public DuenioEntity(PersonaEntity entity, UnidadEntity entity2) {
		this.duenio = entity;
		this.unidad= entity2;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public UnidadEntity getUnidad() {
		return this.unidad; 
	}
	
	public String toString() {
		return new String(id + " " + unidad + " " + duenio);
	}

	public PersonaEntity getPersona() {
		return this.duenio;
	}
}
