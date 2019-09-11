package entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entitys.EdificioEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

@Entity
@Table(name="unidades")
public class UnidadEntity { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="identificador")
	private int identificador;
	@Column(name="piso")
	private String piso;
	@Column(name="numero")
	private String numero;
	@Column(name="habitado")
	private boolean habitado;
	
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificio;
	
	@OneToMany
	@JoinColumn(name="identificador")
	private List<DuenioEntity> duenios;
	
	@Transient
	private List<PersonaEntity> inquilinos;

	public Unidad toUnidad() {
		Unidad unidad = new Unidad(this.identificador, this.piso, this.numero, this.edificio.toEdificio());
		unidad.setDuenios(this.duenios.stream().map(x->x.getDuenio())
				.collect(Collectors.toCollection(ArrayList<Persona>::new)));
		return unidad;
	}
	
	public List<Persona> getDuenios(){
		return this.duenios.stream().map(x -> x.getDuenio()).collect(Collectors.toCollection(ArrayList<Persona>::new));
	}
}
