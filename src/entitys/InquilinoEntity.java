
package entitys;

import javax.persistence.*;

@Entity
@Table(name="inquilinos")
public class InquilinoEntity {
	@Id
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;

	@ManyToMany
	@JoinColumn(name="documento")
	private PersonaEntity inquilino;

	public InquilinoEntity() {
	}

	public InquilinoEntity(int id, UnidadEntity unidad, PersonaEntity inquilino) {
		super();
		this.id = id;
		this.unidad = unidad;
		this.inquilino = inquilino;
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

	public PersonaEntity getInquilino() {
		return inquilino;
	}

	public void setInquilino(PersonaEntity inquilino) {
		this.inquilino = inquilino;
	}
	
	
}
