package entitys;

import java.util.List;

import entitys.EdificioEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

@Entity
@Table(name="unidades")
public class UnidadEntity {
	@Id
	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificio;
	@Transient
	private List<Persona> duenios;
	@Transient
	private List<Persona> inquilinos;

	public Unidad toUnidad() {
		//return new Unidad(this.id, this.piso, this.numero, this.edificio);
		return null;
	}
}
