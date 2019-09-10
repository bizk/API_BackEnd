package entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity duenio;
}
