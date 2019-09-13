package entitys;

import entitys.UnidadEntity;
import modelo.Persona;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inquilinos")
public class InquilinoEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity inquilino;
	
	public Persona getInquilino() {
		return inquilino.toPersona();
	}
}
