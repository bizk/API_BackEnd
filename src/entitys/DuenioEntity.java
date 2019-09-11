package entitys;

import entitys.UnidadEntity;
import modelo.Persona;
import entitys.PersonaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity duenio;
	
	
	public Persona getDuenio() {
		return duenio.toPersona();
	}
}
