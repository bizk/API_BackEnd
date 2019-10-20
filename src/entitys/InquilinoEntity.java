
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

import modelo.Persona;
import modelo.Unidad;

@Entity
@Table(name="inquilinos")
public class InquilinoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;

	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity inquilino;

	public InquilinoEntity() {
		
	}

	public InquilinoEntity(PersonaEntity p, UnidadEntity u) {
		this.inquilino = p;
		this.unidad= u;
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

	public PersonaEntity getPersona() {
		return this.inquilino;
	}	
	
}
