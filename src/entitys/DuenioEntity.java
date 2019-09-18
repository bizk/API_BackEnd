package entitys;

import entitys.UnidadEntity;
<<<<<<< HEAD
import modelo.Persona;
=======
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
import entitys.PersonaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	@Id
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;

<<<<<<< HEAD
	@ManyToOne
=======
	@ManyToMany
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
	@JoinColumn(name="documento")
	@Transient
	private PersonaEntity duenio;
	
<<<<<<< HEAD
	
	public Persona getDuenio() {
		return duenio.toPersona();
=======
	public DuenioEntity() {
		
	}
	
	public PersonaEntity getDuenio() {
		return duenio;
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
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
	}
}
