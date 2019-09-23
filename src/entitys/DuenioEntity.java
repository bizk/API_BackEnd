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
	
	@ManyToMany
	private UnidadEntity unidad;

	@ManyToMany
	@Column(name="documento")
	private PersonaEntity duenio;
	
	public DuenioEntity() {
		
	}
	
	public DuenioEntity(UnidadEntity unidad, PersonaEntity duenio) {
		this.unidad = unidad;
		this.duenio = duenio;
	}

	public PersonaEntity getDuenio() {
		return this.duenio;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public UnidadEntity getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadEntity unidad) {
		this.unidad = unidad;
	}


	public void setDuenio(PersonaEntity duenio) {
		this.duenio = duenio;
	}
	
	public String toString() {
		return new String(id + " " + unidad.toString() + " " + duenio.toString());

	}
}
